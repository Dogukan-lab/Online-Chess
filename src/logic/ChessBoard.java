package logic;

import data.JSONparser;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import javax.json.JsonObject;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

public class ChessBoard {

    private JSONparser jsonParser;

    private int mapWidth;
    private int mapHeight;

    private int tileWidth;
    private int tileHeight;

    private BufferedImage mapImage;
    private BufferedImage[] sourceImages;

    private Tile[][] tiles;


    public ChessBoard(String fileName, ResizableCanvas canvas) {
        this.jsonParser = new JSONparser(new File(fileName));

        this.mapWidth = this.jsonParser.getMapWidth();
        this.mapHeight = this.jsonParser.getMapHeight();

        this.tileWidth = this.jsonParser.getTileWidth();
        this.tileHeight = this.jsonParser.getTileHeight();

        this.sourceImages = this.jsonParser.sourceImages();
        this.tiles = new Tile[8][8];


        this.mapImage = new BufferedImage(this.mapWidth * this.tileWidth, this.mapHeight * this.tileHeight, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics imageGraphics = this.mapImage.getGraphics();

        for (int layer = 0; layer < this.jsonParser.getLayers().size(); layer++) {
            for (int y = 0; y < this.mapHeight; y++) {
                for (int x = 0; x < this.mapWidth; x++) {

                    if (this.jsonParser.getLayers().getJsonObject(layer).getJsonArray("data") != null) {
                        if (!((this.jsonParser.getLayers().getJsonObject(layer).getJsonString("name").equals("tilesPlacesWhite")) ||
                                (this.jsonParser.getLayers().getJsonObject(layer).getJsonString("name").equals("tilesPlacesBlack")) ||
                                (this.jsonParser.getLayers().getJsonObject(layer).getJsonString("name").equals("numbers&letters"))
                        )) {

                            int ID = this.jsonParser.getTileData(layer, x, y);
                            if (ID != -1) {
                                imageGraphics.drawImage(this.sourceImages[ID], x * this.tileWidth, y * this.tileHeight, null);
                            }
                        }

                    }

                }
            }
        }

        int x = 0;
        int y = 0;
        for (int i = 0; i < this.jsonParser.getobjectLayerWhitePlaces().size(); i++) {

            JsonObject object = this.jsonParser.getobjectLayerWhitePlaces().getJsonObject(i);
            int x_place = object.getInt("x");
            int y_place = object.getInt("y");
            int width = object.getInt("width");
            int height = object.getInt("height");

            JsonObject object2 = object.getJsonArray("properties").getJsonObject(1);
            String namePlace = object2.getString("value");

            Tile tile = new Tile(x_place, y_place, width, height, namePlace);
            this.tiles[x][y] = tile;

            x++;
            if (x % 8 == 0) {
                y++;
                x = 0;
            }
        }

        for(int yC = 0; yC < 8; yC++){
            for(int xC = 0; xC < 8; xC++){
                System.out.println(tiles[xC][yC].toString());
            }
        }


    }

    public void draw(FXGraphics2D graphics) {
        graphics.setTransform(getTransform());
        graphics.drawImage(this.mapImage, 0, 0, null);
    }


    public AffineTransform getTransform() {
        AffineTransform tx = new AffineTransform();
        tx.translate(400, 50);
        tx.scale(0.3, 0.3);
        return tx;
    }


    public Tile[][] getTiles() {
        return tiles;
    }
}
