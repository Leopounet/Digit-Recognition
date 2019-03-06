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
set(MESSAGE "com/message/")

# Custom command to create directories when cmake .. is used
execute_process(
COMMAND mkdir ${EXT}
COMMAND mkdir ${DATASET}
COMMAND mkdir ${FIELD}
COMMAND mkdir ${LABELDISTANCE}
COMMAND mkdir ${WINDOW}
COMMAND mkdir ${MESSAGE})

# Make the project (m for make)
add_custom_target(m
COMMAND javac ../${DATASET}*.java
../${FIELD}*.java
../${LABELDISTANCE}*.java
../${WINDOW}*.java
../${MESSAGE}*.java
../${MAIN}*.java
COMMAND mv ../${DATASET}*.class ${DATASET}
COMMAND mv ../${FIELD}*.class ${FIELD}
COMMAND mv ../${LABELDISTANCE}*.class ${LABELDISTANCE}
COMMAND mv ../${WINDOW}*.class ${WINDOW}
COMMAND mv ../${MESSAGE}*.class ${MESSAGE}
COMMAND mv ../${MAIN}.class .)

# Run the project (r for run)
add_custom_target(r COMMAND java ${MAIN})

# Cleans the build directory (c for clean)
add_custom_target(c
COMMAND rm ${DATASET}*.class
COMMAND rm ${FIELD}*.class
COMMAND rm ${LABELDISTANCE}*.class
COMMAND rm ${WINDOW}*.class
COMMAND rm ${MESSAGE}*.class
COMMAND rm ${MAIN}.class)
