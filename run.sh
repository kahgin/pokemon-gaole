#!/bin/bash

# Create a bin directory if it doesn't exist
if [ ! -d "bin" ]; then
	mkdir bin
fi

# Compile the source code
javac -d bin src/*.java

# Check if compilation was successful
if [ $? -ne 0 ]; then
    echo "Compilation failed. Exiting."
    exit 1
fi

# Run the game
java -cp bin RunGame
