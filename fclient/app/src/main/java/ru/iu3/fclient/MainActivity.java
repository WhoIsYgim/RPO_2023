package ru.iu3.fclient;



import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.IOUtils;


import ru.iu3.fclient.databinding.ActivityMainBinding;


import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity  implements TransactionEvents{

    // Used to load the 'fclient' library on application startup.
    static {
        System.loadLibrary("fclient");
        System.loadLibrary("mbedcrypto");
    }

    private ActivityMainBinding binding;
    ActivityResultLauncher activityResultLauncher;
    private String pin;

    @Override
    public String enterPin(int ptc, String amount) {
        pin = "";
        Intent it = new Intent(MainActivity.this, PinpadActivity.class);
        it.putExtra("ptc", ptc);
        it.putExtra("amount", amount);
        synchronized (MainActivity.this) {
            activityResultLauncher.launch(it);
            try {
                MainActivity.this.wait();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return pin;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);

        int res = initRng();

//        byte[] str = "Menya zovut Larkin Egor".getBytes(StandardCharsets.UTF_16);
//        byte[] random = randomBytes(16);
//        byte[] encrypted = encrypt(random, str);
//        byte[] decrypted = decrypt(random, encrypted);
//        // Example of a call to a native method
//        TextView tv = binding.sampleText;
//        tv.setText(stringFromJNI());
//
//        TextView name = binding.name;
//        name.setText("Secure text: " + new String(str, StandardCharsets.UTF_16));
//
//        TextView rand = binding.randomBytes;
//        rand.setText("Random string: " + new String(random, StandardCharsets.UTF_16));
//
//        TextView encr = binding.encrypted;
//        encr.setText("Encrypted text: " + new String(encrypted, StandardCharsets.UTF_16));
//
//        TextView decr = binding.decrypted;
//        decr.setText("Decrypted text: " + new String(decrypted, StandardCharsets.UTF_16));


        activityResultLauncher  = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            // обработка результата
                            // String pin = data.getStringExtra("pin");
                            // Toast.makeText(MainActivity.this, pin, Toast.LENGTH_SHORT).show();
                            pin = data.getStringExtra("pin");
                            synchronized (MainActivity.this) {
                                MainActivity.this.notifyAll();
                            }
                        }
                    }
                });
    }

    public static byte[] stringToHex(String s)
    {
        byte[] hex;
        try
        {
            hex = Hex.decodeHex(s.toCharArray());
        }
        catch (DecoderException ex)
        {
            hex = null;
        }
        return hex;
    }


    public void onButtonClick(View v)
    {

        testHttpClient();
//        new Thread(() -> {
//            try {
//                byte[] trd = stringToHex("9F0206000000000100");
//                boolean ok = transaction(trd);
//            } catch (Exception ex){
//
//            }
//        }).start();

    }


    @Override
    public void transactionResult(boolean result) {
        runOnUiThread(()-> {
            Toast.makeText(MainActivity.this, result ? "ok" : "failed", Toast.LENGTH_SHORT).show();
        });
    }


    protected void testHttpClient()
    {
        new Thread(() -> {
            try {
                HttpURLConnection uc = (HttpURLConnection)
                        (new URL("https://www.wikipedia.org").openConnection());
                InputStream inputStream = uc.getInputStream();
                String html = IOUtils.toString(inputStream);
                String title = getPageTitle(html);
                runOnUiThread(() ->
                {
                    Toast.makeText(this, title, Toast.LENGTH_LONG).show();
                });

            } catch (Exception ex) {
                Log.e("fapptag", "Http client fails", ex);
            }
        }).start();
    }

    private String getPageTitle(String html) {
        int pos = html.indexOf("<title");
        String p="not found";
        if (pos >= 0)
        {
            int pos2 = html.indexOf("<", pos + 1);
            if (pos >= 0)
                p = html.substring(pos + 7, pos2);
        }
        return p;
    }


    /**
     * A native method that is implemented by the 'fclient' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
    public static native int initRng();
    public static native byte[] randomBytes(int no);

    public static native byte[] encrypt(byte[] key, byte[] data);

    public static native byte[] decrypt(byte[] key, byte[] data);


    public native boolean transaction(byte[] trd);
}