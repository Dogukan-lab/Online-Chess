package data;

import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import javax.json.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.Buffer;

public class JSONparser {

    private JsonObject jsonObject;

    public JSONparser(File file) {

        try {
            InputStream inputStream = new FileInputStream(file);
            JsonReader jsonReader = Json.createReader(inputStream);
            this.jsonObject = jsonReader.readObject();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getMapWidth() {
        return jsonObject.getInt("width");
    }

    public int getMapHeight() {
        return jsonObject.getInt("height");
    }

    public int getTileWidth() {
        return jsonObject.getInt("tilewidth");
    }

    public int getTileHeight() {
        return jsonObject.getInt("tileheight");
    }

    public JsonObject getHoleFile() {
        return this.jsonObject;
    }

    public JsonArray getLayers() {
        return this.jsonObject.getJsonArray("layers");
    }

    public JsonObject getLayer(String name) {
        JsonArray array = getLayers();

        for (int i = 0; i < array.size(); i++) {
            String layerName = array.getJsonObject(i).getString("name");
            if (name.equals(layerName)) {
                return array.getJsonObject(i);
            }
        }

        return null;
    }

    public JsonObject getWhiteSidePlaces() {
        JsonObject whiteLayer = getLayer("tilesPlacesWhite");
        return whiteLayer;
    }

    public JsonObject getBlackSidePlaces() {
        JsonObject blackLayer = getLayer("tilesPlacesBlack");
        return blackLayer;
    }


    public BufferedImage[] sourceImages(){
        BufferedImage[] images = new BufferedImage[5];

        JsonArray tileLayer = this.jsonObject.getJsonArray("tilesets");

        for (int i = 0; i < tileLayer.size(); i++) {
            JsonObject object = tileLayer.getJsonObject(i);
            try {
                BufferedImage image = ImageIO.read(new File("resources/" + object.getString("image")));
                images[i] = image;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return images;
    }

    public JsonObject getLayer(int layer) {
        return getLayers().getJsonObject(layer);
    }

    public int getTileData(int layer, int xPos, int yPos) {

        JsonObject layerObject = getLayer(layer);
        int tileID = getTileID(xPos, yPos);
        int data = layerObject.getJsonArray("data").getInt(tileID) - 1;

        return data;
    }

    public int getTileID(int x, int y) {

        return (y * getMapWidth()) + x;
    }


    public JsonArray getobjectLayerWhitePlaces(){
        JsonObject whitePlaces = getWhiteSidePlaces();

        return whitePlaces.getJsonArray("objects");
    }


}
