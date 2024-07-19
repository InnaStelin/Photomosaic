package Photomosaic;

public interface TileFile {
    boolean loadFromFiles(String tilesDir);
    void convertToMosaics(String tilesPath);
}
