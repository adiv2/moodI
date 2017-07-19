# imageOperatorsDemo

This applications reads images from a directory- converts,compresses and resize the images and writes to output directory.

Before running this application, set the input and output directories in properties.xml 
Other properties like toFileType (set to jpg to convert all images to jpg),
compressionRatio and scale are optional and can be set in properties.xml.

## Result
Original image 4.3MB 4096x2304
![alt text](https://github.com/DataTorrent/moodI/blob/master/operators/contrib/image/src/test/resources/TestImages/TestImage.jpg"Original image")

Compressed image 1.05 MB 2048x1152
![alt text](https://raw.githubusercontent.com/DataTorrent/moodI/blob/master/operators/contrib/image/src/test/resources/TestImages/CompressedTestImage.jpg)

