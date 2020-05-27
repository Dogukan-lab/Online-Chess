package ui;

import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;

public class UIStartScreen {


    private ResizableCanvas canvas;
    private ArrayList<UIbutton> buttons;
    private BufferedImage background;

    public UIStartScreen(ResizableCanvas canvas) {

        this.canvas = canvas;
        this.buttons = new ArrayList<>();

        try {
            this.background = ImageIO.read(new File("resources/chess-background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        UIbutton createLobby = new UIbutton("Create Lobby", 0, 0, 1, 0, canvas.getWidth(), canvas.getHeight()/2, 200,100, Color.black);
        UIbutton joinLobby = new UIbutton("Join Lobby", 0, 0, 1, 0, canvas.getWidth(), canvas.getHeight() + 50, 200, 100, Color.black);
        this.buttons.add(createLobby);
        this.buttons.add(joinLobby);

        canvas.setOnMouseClicked(e -> {
            double mosX = e.getX();
            double mosY = e.getY();

            for(UIbutton button : this.buttons){
                if(mosX > button.getRectangle().getMinX() && mosX < button.getRectangle().getMaxX()){
                    if(mosY > button.getRectangle().getMinY() && mosY < button.getRectangle().getMaxY()){
                        if(button.getTitleName().equals("Create Lobby")){
                            UIManager.toMap = true;
                        }
                    }
                }
            }
        });
    }

    public void draw(FXGraphics2D graphics){

        if(this.background != null){
            graphics.drawImage(this.background, 0,0, (int)this.canvas.getWidth(), (int)this.canvas.getHeight(), null);
        }

        for(UIbutton button : this.buttons){
            button.draw(graphics);
        }

    }

    public void update(double deltaTime){
        for (UIbutton button : this.buttons) {
            button.update(deltaTime);
            if(button.getTitleName().equals("Create Lobby")){
                button.setPosition(this.canvas.getWidth()/2,  this.canvas.getHeight()/2);
            }
            if(button.getTitleName().equals("Join Lobby")){
                button.setPosition(this.canvas.getWidth()/2, this.canvas.getHeight()/2 + 150);
            }
        }
    }




}
