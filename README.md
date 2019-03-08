# Brief

This software uses the kNN algorithm to determine what digit is written on an image.
More infos : https://en.wikipedia.org/wiki/K-nearest_neighbors_algorithm

# Download

Download the directory you wish to use.

## Eclipse

Download the corresponding directory, open Eclipse and import the directory as a new project.
More infos : https://stackoverflow.com/questions/8459395/eclipse-import-an-existing-project

## Linux

Make sure you have CMake and Java installed on your computer. Download the corresponding directory. Create a build directory in the Linux folder. Open a terminal and run the following commands.

Create the CMake dependencies.
```bash
cmake ..
```

Compile the project.
```bash
make m
```

Run the project.
```bash
make r
```

Clean the build directory.
```bash
make c
```

# How to use

After running the project you will be prompted a loading window, this can last up to one minute depending on your computer performances.

## Upload an image

If you click the upload button, you will be able to upload your own image. Please consider that this software does NOT ask for permissions, therefore before selecting a file, make sure it is accessible without privilege.

## Draw image

If you click the draw button, you will be able to draw your own digit. At the bottom of the screen you will be able to change the color of the pencil and its size.

## Random image

If you click the random image button, a random image of a digit from the MNIST site will be loaded. The images are 28 * 28 pixels, therefore the result is very blocky when displayed. Please note that those images are provided by the same site that the algorithm uses to train itself, therefore the confidence will be very high when the image will be uploaded.

## Submit image

If you click the submit button after having chosen or drawn an image, the algorithm will compute what digit is drawn and display the results as a diagram below. The algorithm used here is not very precise so do not expect extremely precise results if you drew or uploaded the image.

## Save image

If you click the save image button, the current displayed image will be saved in the folder 'images', it will be given and random name that avoids collisions.

# Important notes

If you have any problems or questions, please contact me : st20147420@outlook.cardiffmet.ac.uk  
