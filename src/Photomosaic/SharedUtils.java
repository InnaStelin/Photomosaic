package Photomosaic;

import java.awt.image.BufferedImage;
interface SharedUtils {
     static BufferedImage readImage(String path) {
          return null;
     }

     static BufferedImage scaleImage(BufferedImage img,
                                     int scaleWidth,
                                     int scaleHeight) { return null; }

     static void displayImage(BufferedImage img,
                              int x,
                              int y) {}

     public static BufferedImage scaleForPerformance(BufferedImage img) {
          return null;
     }

     public static void screenImageWidthHeight(BufferedImage source) {}
}
