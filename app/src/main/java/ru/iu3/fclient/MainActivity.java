package ru.iu3.fclient;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;


import ru.iu3.fclient.databinding.ActivityMainBinding;



import java.nio.charset.StandardCharsets;
public class MainActivity extends AppCompatActivity {

    // Used to load the 'fclient' library on application startup.
    static {
        System.loadLibrary("fclient");
        System.loadLibrary("mbedcrypto");
    }

    private ActivityMainBinding binding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int res = initRng();
        byte[] str = "Menya zovut Larkin Egor".getBytes(StandardCharsets.UTF_16);
        byte[] random = randomBytes(16);
        byte[] encrypted = encrypt(random, str);
        byte[] decrypted = decrypt(random, encrypted);
        // Example of a call to a native method
        TextView tv = binding.sampleText;
        tv.setText(stringFromJNI());

        TextView name = binding.name;
        name.setText("Secure text: " + new String(str, StandardCharsets.UTF_16));

        TextView rand = binding.randomBytes;
        rand.setText("Random string: " + new String(random, StandardCharsets.UTF_16));

        TextView encr = binding.encrypted;
        encr.setText("Encrypted text: " + new String(encrypted, StandardCharsets.UTF_16));

        TextView decr = binding.decrypted;
        decr.setText("Decrypted text: " + new String(decrypted, StandardCharsets.UTF_16));


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
}