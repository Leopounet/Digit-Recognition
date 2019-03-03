# CMAKE params
PROJECT("Digit Recognition")
cmake_minimum_required(VERSION 2.6)
set(CMAKE_VERBOSE_MAKEFILE OFF)

# Main file
set(MAIN "DigitRec")

# Make the project (m for make)
add_custom_target(m COMMAND javac *.java COMMAND mv *.class .)

# Run the project (r for run)
add_custom_target(r COMMAND java MAIN)

# Cleans the build directory (c for clean)
add_custom_target(c COMMAND rm *.class)
