package Photomosaic;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OutputImage implements OutputImageUtils {
    private final List<TileImage> sourceList;
    private final List<TileImage> tilesList;
    private List<TileImage> resultList;
    private final int sourceWidth;
    private final int sourceHeight;
    public OutputImage(InputImageMgr source, TileFileMgr mosaics) {

        sourceWidth = source.getWidthSource();
        sourceHeight =  source.getHeightSource();

        sourceList = source.getImageList();
        tilesList = mosaics.getImageList();

    }
    public void createMatchingList() {

        Iterator<TileImage> imgIterator = sourceList.iterator();
        resultList = new ArrayList<>();

        while (imgIterator.hasNext()) {
            TileImage source = imgIterator.next();
            TileImage match = getMatchingTile(source);
            resultList.add(match);
        }
    }
    public TileImage getMatchingTile(TileImage source) {

        TileImage match = null;
        double minDistance = 0;
        int i = 0;
        for (TileImage tileImg : tilesList) {
            double distance = getDistance(source, tileImg);
            if (i == 0) {
                minDistance = distance;
                match = tileImg;
            } else {
                if (distance < minDistance) {
                    minDistance = distance;
                    match = tileImg;
                }
            }
            i++;
        }
        return match;
    }
    public double getDistance(TileImage source, TileImage mosaic) {

        Color one = source.getAvgColor();
        Color two = mosaic.getAvgColor();
        return  (Math.sqrt(Math.pow(two.getRed() - one.getRed(), 2) +
                                    Math.pow(two.getGreen() - one.getGreen(), 2) +
                                    Math.pow(two.getBlue() - one.getBlue(), 2)
                                    ));
    }
    public BufferedImage createResult()
    {
        createMatchingList();
        int width = sourceWidth;
        int height = sourceHeight;

        BufferedImage resultImage = new BufferedImage(width,
                height,
                BufferedImage.TYPE_INT_ARGB);
        int startX = 0;
        int startY = 0;
        int smallImageWidth = TileImage.getWidth();
        int smallImageHeight = TileImage.getHeight();

        for (TileImage img : resultList) {
            resultImage.setRGB(startX, startY,
                    smallImageWidth, smallImageHeight,
                    img.getPixels(),
                    0,
                    smallImageWidth);

            startX += smallImageWidth;
            if ((startX + smallImageWidth) > width) {
                startX = 0;
                startY += smallImageHeight;
            }
            if ((startY + smallImageHeight) > height) {
                break;
            }
        }
        return resultImage;
    }
}
