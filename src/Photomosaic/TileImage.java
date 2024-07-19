package Photomosaic;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TileImage implements TileImageAvgColor {
    private static int tileHeigh = 50;
    private static int tileWidth = 50;
    private final int[] pixels;
    private Color avgColor;
    public TileImage() { pixels = new int[tileWidth * tileHeigh]; }
    public static int getWidth() {
        return tileWidth;
    }
    public static int getHeight() {
        return tileHeigh;
    }
    public int[] getPixels() {
        return pixels;
    }
    public Color getAvgColor() {
        return avgColor;
    }
    public void setPixels(BufferedImage img,  int colSource,  int rowSource) {

        //Get and store RGB for each pixel of tile image.
        //img.getRGB(col, row) - Returns an integer pixel in the default RGB color model (TYPE_INT_ARGB)
        //There are only 8-bits of precision for each color component in the returned data when using this method.
        int pIndex = 0;
        for (int row = rowSource; row < rowSource+tileHeigh; row++) {
            for (int col = colSource; col < colSource+tileWidth; col++) {
                pixels[pIndex++] = img.getRGB(col, row);
            }
        }

    }
    public void setAvgColor() {

        //Calculate average R, G, B and create Color object for it
        int red = 0;
        int green = 0;
        int blue = 0;
        for (int i = 0; i < tileWidth * tileHeigh; i++) {
            Color c = new Color(pixels[i]);
            red += c.getRed();
            green += c.getGreen();
            blue += c.getBlue();
        }
        avgColor = new Color(red / (tileWidth * tileHeigh),
                            green / (tileWidth * tileHeigh),
                             blue / (tileWidth * tileHeigh));

    }

    //Below methods were used in testing.They are not part of  Mosaic demo.
    public void setAllToAvgColor() {
        for (int i = 0; i < tileWidth * tileHeigh; i++) {
            pixels[i] = avgColor.getRGB();
        }
    }
    public void printPixes() {
        for (int i = 0; i < 250; i++) {
            Color c = new Color(pixels[i]);
            System.out.println("printing pixels " + c.getRed() + " " + c.getGreen() + " " + c.getBlue());
        }
    }
    public void printAvgColor() {
        System.out.println("printing avg color red" + avgColor.getRed() + " green"
                                                     + avgColor.getGreen() + " blue"
                                                     + avgColor.getBlue());
    }
}


