package logic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SpriteSheetReader {

    private BufferedImage sourceImage;
    private ArrayList<BufferedImage> images;

    public SpriteSheetReader(String fileName) {
        this.images = new ArrayList<>();
        try {
            this.sourceImage = ImageIO.read(new File(fileName));
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        readAll();
    }
    
    
    public void readAll(){
        for(int y = 0; y < 2; y++){
            for (int x = 0; x < 6; x++) {
                BufferedImage image = this.sourceImage.getSubimage(x*100, y*100, 100, 100);
                this.images.add(image);
            }
        }
    }


    public ArrayList<BufferedImage> getImages() {
        return images;
    }
}
