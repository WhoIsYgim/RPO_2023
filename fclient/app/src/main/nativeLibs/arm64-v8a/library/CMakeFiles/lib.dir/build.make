# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.22

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:

#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:

# Disable VCS-based implicit rules.
% : %,v

# Disable VCS-based implicit rules.
% : RCS/%

# Disable VCS-based implicit rules.
% : RCS/%,v

# Disable VCS-based implicit rules.
% : SCCS/s.%

# Disable VCS-based implicit rules.
% : s.%

.SUFFIXES: .hpux_make_needs_suffix_list

# Command-line flag to silence nested $(MAKE).
$(VERBOSE)MAKESILENT = -s

#Suppress display of executed commands.
$(VERBOSE).SILENT:

# A target that is always out of date.
cmake_force:
.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /home/egornulllet/Android/Sdk/cmake/3.22.1/bin/cmake

# The command to remove a file.
RM = /home/egornulllet/Android/Sdk/cmake/3.22.1/bin/cmake -E rm -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /home/egornulllet/AndroidStudioProjects/libs/mbedtls/mbedtls

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /home/egornulllet/AndroidStudioProjects/libs/mbedtls/build/arm64-v8a

# Utility rule file for lib.

# Include any custom commands dependencies for this target.
include library/CMakeFiles/lib.dir/compiler_depend.make

# Include the progress variables for this target.
include library/CMakeFiles/lib.dir/progress.make

library/CMakeFiles/lib: library/libmbedcrypto.so
library/CMakeFiles/lib: library/libmbedx509.so
library/CMakeFiles/lib: library/libmbedtls.so

lib: library/CMakeFiles/lib
lib: library/CMakeFiles/lib.dir/build.make
.PHONY : lib

# Rule to build all files generated by this target.
library/CMakeFiles/lib.dir/build: lib
.PHONY : library/CMakeFiles/lib.dir/build

library/CMakeFiles/lib.dir/clean:
	cd /home/egornulllet/AndroidStudioProjects/libs/mbedtls/build/arm64-v8a/library && $(CMAKE_COMMAND) -P CMakeFiles/lib.dir/cmake_clean.cmake
.PHONY : library/CMakeFiles/lib.dir/clean

library/CMakeFiles/lib.dir/depend:
	cd /home/egornulllet/AndroidStudioProjects/libs/mbedtls/build/arm64-v8a && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/egornulllet/AndroidStudioProjects/libs/mbedtls/mbedtls /home/egornulllet/AndroidStudioProjects/libs/mbedtls/mbedtls/library /home/egornulllet/AndroidStudioProjects/libs/mbedtls/build/arm64-v8a /home/egornulllet/AndroidStudioProjects/libs/mbedtls/build/arm64-v8a/library /home/egornulllet/AndroidStudioProjects/libs/mbedtls/build/arm64-v8a/library/CMakeFiles/lib.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : library/CMakeFiles/lib.dir/depend

