package Photomosaic;

import java.awt.image.BufferedImage;

public class MosaicDemo {
    public void runDemo(String sourceFilePath, String mosaicDir)  {
        //If error occurs anywhere in this function due to bad inputs we
        //will display error message and let user re-try and re-enter input
        SharedUtilsMgr.getScreenSize();

        InputImageMgr source = new InputImageMgr(sourceFilePath);
        source.prepareSourceImage();
        TileFileMgr mosaics = new TileFileMgr();
        if (mosaics.loadFromFiles(mosaicDir) == true) {
            SharedUtilsMgr.displayImage(source.getImage(), 0, 0);

            OutputImage output = new OutputImage(source, mosaics);
            BufferedImage resultImage = output.createResult();

            int resultPosition = SharedUtilsMgr.displayWidth + SharedUtilsMgr.gapImages;
            SharedUtilsMgr.displayImage(resultImage, resultPosition, 0);
        }
    }
}

