package Photomosaic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TileFileMgr implements TileFile {
    private List<TileImage> mosaicImages;
    public List<TileImage> getImageList() {
        return mosaicImages;
    }

    public void convertToMosaics(String mosaicsPath)  {

        //Scale tile images in selected directory to be mosaic-ready.
        //If error occurs here error message will be displayed and
        //input dialog will stay visible to let user make another selection

        File path = new File(mosaicsPath + "/");
        String outFile = "";
        File[] files = path.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].isFile()) {
                    BufferedImage img = SharedUtilsMgr.readImage(files[i].getPath());
                    if (img != null) {
                        int maxArea = TileImage.getWidth() * TileImage.getHeight();
                        BufferedImage imageScaled = SharedUtilsMgr.scaleImage(img, maxArea);

                        try {
                            outFile = mosaicsPath + "/mosaic_images/" + i + ".png";
                            File outputfile = new File(outFile);
                            ImageIO.write(imageScaled, "png", outputfile);

                        } catch (IOException e) {

                            SharedUtilsMgr.showError("Error", "Error writing output image " + outFile);
                        }
                    }
                    else {

                        SharedUtilsMgr.showError("Error", "Error reading image from " + path);
                    }
                }
            }
        } else {

            SharedUtilsMgr.showError("Error", "Error reading images directory: " + path);
        }
    }
    public boolean loadFromFiles(String mosaicDir)  {
        mosaicImages = new ArrayList<>();
        File path = new File(mosaicDir);
        if (!path.isDirectory()) {

            SharedUtilsMgr.showError("Error", "Error reading images: path " + path + " is not a directory");
            return false;
        }
        File[] files = path.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].isFile()) {
                    BufferedImage img = SharedUtilsMgr.readImage(files[i].getPath());
                    if (img != null) {
                        TileImage smallImage = new TileImage();
                        smallImage.setPixels(img, 0, 0);
                        smallImage.setAvgColor();
                        mosaicImages.add(smallImage);
                    } else {
                        SharedUtilsMgr.showError("Error", "Error reading image from " + path);
                    }
                }
            }
        } else {
            SharedUtilsMgr.showError("Error", "Error reading images: directory " + path + " is empty");
            return false;
        }
        if (mosaicImages.size() ==  0) return false;  //We could not find a single image file
        return true;
    }
}


