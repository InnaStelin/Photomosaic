package Photomosaic;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class SharedUtilsMgr implements SharedUtils {
    public static int displayWidth;
    public static int displayHeight;
    public static int screenWidth;
    public static int screenHeight;
    public static int imageMaxArea = 9000000; //Bigger images take too long to complete
    public static int gapImages = 20;

    public static double portraitWidth = 0.36;
    public static double portraitHeight = 0.91;
    public static double landscapeWidth = 0.6;
    public static double landscapeHeight= 0.91;

    public static BufferedImage readImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println("Could not read file " + path);
            showError("Error", "Could not read file " + path);
            return null;
        }
    }
    public static BufferedImage scaleImage(BufferedImage source, double targetArea)
    {
        //Formula to resize image and maintain aspect ratio:
        //new_width = square_root((original_width / original_height) × target_area)
        //new_height = target_area / new_width
        double aRatio = ((double)source.getWidth())/((double)source.getHeight());
        double scaledWidthDouble = Math.sqrt(aRatio*targetArea);
        double scaledHeightDouble = targetArea/scaledWidthDouble;
        int scaleWidth = ((int) (scaledWidthDouble/50)) * 50;
        int scaleHeight = ((int) (scaledHeightDouble/50)) * 50;

        BufferedImage scaleImg = new BufferedImage(scaleWidth,
                scaleHeight,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = scaleImg.createGraphics();
        g.drawImage(source,0,0,scaleWidth,scaleHeight,null);
        g.dispose();
        return scaleImg;
    }
    public static void displayImage(BufferedImage img, int xPosition, int yPosition)  {
        //Image is scaled to
        BufferedImage imageDisplay = scaleImage(img, displayWidth*displayHeight);
        showImageInFrame(imageDisplay, xPosition, yPosition);
    }
    public static void showImageInFrame(BufferedImage img, int xPosition, int yPosition) {
        ImageIcon icon = new ImageIcon(img);
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(displayWidth,  displayHeight);
        JLabel lbl = new JLabel();
        lbl.setIcon(icon);

        frame.add(lbl);
        frame.setLocation(xPosition, yPosition);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public static BufferedImage scaleForPerformance(BufferedImage img) {
        return scaleImage(img, imageMaxArea);
    }

    public static void screenImageWidthHeight(BufferedImage source) {
        double aRatio = ((double)source.getWidth())/((double)source.getHeight());
        if (aRatio < 1) {
            //Portrait orientation
            displayWidth = (int) (screenWidth * portraitWidth);
            displayHeight = (int) (screenHeight * portraitHeight);
        }
        else {
            //Landscape orientation
            displayWidth = (int) (screenWidth * landscapeWidth);
            displayHeight = (int) (screenHeight * landscapeHeight);
        }
    }
    public static void getScreenSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = (int) screenSize.getWidth();
        screenHeight = (int) screenSize.getHeight();
    }
    public static void showError(String title, String error) {
        JOptionPane.showMessageDialog(null,
                error,
                title,
                JOptionPane.WARNING_MESSAGE);
    }
}
