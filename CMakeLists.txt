# CMAKE params
PROJECT("Digit Recognition")
cmake_minimum_required(VERSION 2.6)
set(CMAKE_VERBOSE_MAKEFILE OFF)

# Main file
set(MAIN "DigitRec")

# Different packages
set(EXT "com/")
set(DATASET "com/dataset/")
set(FIELD "com/field/")
set(LABELDISTANCE "com/labeldistance/")
set(WINDOW "com/window/")

# Custom command to create directories when cmake .. is used
add_custom_command(OUTPUT dir.log
COMMAND mkdir ${EXT}
COMMAND mkdir ${DATASET}
COMMAND mkdir ${FIELD}
COMMAND mkdir ${LABELDISTANCE}
COMMAND mkdir ${WINDOW})

# Make the project (m for make)
add_custom_target(m
COMMAND javac ../${DATASET}*.java ../${FIELD}*.java ../${LABELDISTANCE}*.java ../${WINDOW}*.java ../${MAIN}.java
COMMAND mv ../${DATASET}*.class ${DATASET}
COMMAND mv ../${FIELD}*.class ${FIELD}
COMMAND mv ../${LABELDISTANCE}*.class ${LABELDISTANCE}
COMMAND mv ../${WINDOW}*.class ${WINDOW}
COMMAND mv ../${MAIN}.class .)

# Run the project (r for run)
add_custom_target(r COMMAND java ${MAIN})

# Cleans the build directory (c for clean)
add_custom_target(c
COMMAND rm ${DATASET}*.class
COMMAND rm ${FIELD}*.class
COMMAND rm ${LABELDISTANCE}*.class
COMMAND rm ${WINDOW}*.class
COMMAND rm ${MAIN}.class)
