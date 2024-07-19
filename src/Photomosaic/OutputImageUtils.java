package Photomosaic;

import java.awt.image.BufferedImage;

public interface OutputImageUtils {

    TileImage getMatchingTile(TileImage source);
    void createMatchingList();
    double getDistance(TileImage source, TileImage mosaic);
    BufferedImage createResult();
}
