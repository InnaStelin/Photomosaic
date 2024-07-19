About This Project
	 This project converts  an image into its tiled version. The image is 
	 divided into sections and each section is replaced with a tile image 
	 that closely matches replaced section in color. This is achieved by 
	 dividing image into small 50x50 pixels sections and then matching 
	 each section to the tile image (also 50x50 pixels) from the tiles 
	 collection.Matched tile is found by comparing average color in the 
	 image section to the average color of the tile. Tile average color 
	 is found by adding up R,G,B components of each pixel and then diving 
	 each component by image area which is (image width)x(image height). 
	 Closest tile image would have the smallest color distance from original image.
	 Distance is determined by: 
			-Distance = sq rt(sq(r2-r1) + sq(g2-g1) + sq(b2-b1)))
				where r1,g1,b1 is average color of the image section
			    r2, g2, b2 is average color of the tile	
			-Average color is found by adding up each pixel's r, g, b and dividing it by 
				image area 
	 Tiles are randomly picked small images, preferably with one dominating color, 
	 for example a picture of a big white flower works very well as a tile. The more 
	 images with varied colors are used, the more accurate resulting tiled image is. 
	 Both input image and tiles images are selected from UserInput dialog.
	 Tile images are converted to tiles by scaling them to the 50x50 pixels.
	 This project comes with 25 images already scaled that reside in /mosaic_images directory.
	 Option to convert new set of images to mosaic tiles is available with "Convert mosaic images"
	 check mark UserInput dialog.If it is selected, images in the directory will be scaled  first. 
	 Maximum area of Input images are scaled to is 9,000,000 since bigger image take too long to tile.
	 Input image is first scaled to fit this area. After tiling both input and result (tiled)  
	 image and are scaled to properly fit screen display. 

Built With
	Java and Java Swing library
	  
Usage
     Initial input images are included and  reside in ./resources directory, for example:
			./resources/Jellyfish.jpg
     Initial tile images are also included and  reside in 
			./resources/mosaic_images directory.
	 Project was developed with IntelliJ but any other java DE can be used to compile and build it.
	 