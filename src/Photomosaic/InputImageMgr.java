package Photomosaic;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class InputImageMgr implements InputImage {
    private final List<TileImage> sourceImgPartitions;
    public List<TileImage> getImageList() {
        return sourceImgPartitions;
    }
    private BufferedImage sourceImg;
    private  int width;
    private  int height;
    public  int getWidthSource() {
        return width;
    }
    public  int getHeightSource() {
        return height;
    }
    public BufferedImage getImage() { return sourceImg; }
    private String filePath;

    public InputImageMgr(String sourceFilePath) {
        sourceImgPartitions = new ArrayList<>();
        filePath = sourceFilePath;
    }
    public void prepareSourceImage()  {

        BufferedImage source = SharedUtilsMgr.readImage(filePath);
        if (source != null) {
            sourceImg = SharedUtilsMgr.scaleForPerformance(source);
            //Set width and height after scaling
            width = sourceImg.getWidth();
            height = sourceImg.getHeight();
            SharedUtilsMgr.screenImageWidthHeight(source);
            partitionSourceImage();
        } else {
            SharedUtilsMgr.showError("Error", "Error reading image from: " + filePath);
        }
    }
    public void partitionSourceImage() {
        int smallImageWidth = TileImage.getWidth();
        int smallImageHeight = TileImage.getHeight();

        int maxWidth = width - smallImageWidth;
        int maxHeight = height - smallImageHeight;

        for (int row = 0; row <= maxHeight; row+=smallImageHeight) {
            for (int col = 0; col <= maxWidth; col+=smallImageWidth) {
                TileImage smallImage = new TileImage();
                smallImage.setPixels(sourceImg, col, row);
                smallImage.setAvgColor();
                //smallImage.setAllToAvgColor();
                sourceImgPartitions.add(smallImage);
            }
        }
    }
}
