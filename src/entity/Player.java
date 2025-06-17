package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler kh;

    public Player(GamePanel gp, KeyHandler kh){
        this.gp = gp;
        this.kh = kh;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 4;
        direction = "idle";
        lastDirection = "down";
    }

    public void getPlayerImage(){
        try {

            up1 = ImageIO.read(getClass().getResourceAsStream("/player/cat/character06.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/cat/character07.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/cat/character02.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/cat/character03.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/cat/character10.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/cat/character11.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/cat/character14.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/cat/character15.png"));
            idleDown = ImageIO.read(getClass().getResourceAsStream("/player/cat/character00.png"));
            idleUp = ImageIO.read(getClass().getResourceAsStream("/player/cat/character04.png"));            idleLeft = ImageIO.read(getClass().getResourceAsStream("/player/cat/character08.png"));
            idleRight = ImageIO.read(getClass().getResourceAsStream("/player/cat/character12.png"));

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update(){ // Remember this is called 60 times per second.

        if(kh.upPressed || kh.downPressed || kh.leftPressed || kh.rightPressed){
            if (kh.upPressed){
                direction = "up";
                y -= speed;
            }
            else if (kh.downPressed){
                direction = "down";
                y += speed;
            }
            else if (kh.leftPressed){
                direction = "left";
                x -= speed;
            }
            else if (kh.rightPressed){
                direction = "right";
                x += speed
                ;
            }

            lastDirection = direction;

            spriteCounter++;
            if(spriteCounter > 5){
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

        }

        else {
            direction = "idle";
        }


    }
    public void draw(Graphics2D g2){

//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage img = null;

        switch (direction){
            case "up":
                if(spriteNum == 1){
                    img = up1;
                }
                if(spriteNum == 2){
                    img = up2;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    img = down1;
                }
                if(spriteNum == 2){
                    img = down2;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    img = left1;
                }
                if(spriteNum == 2){
                    img = left2;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    img = right1;
                }
                if(spriteNum == 2){
                    img = right2;
                }
                break;
            case "idle":
                if(lastDirection.equals("up")){
                    img = idleUp;

                }
                else if(lastDirection.equals("down")){
                    img = idleDown;
                }
                else if(lastDirection.equals("left")){
                    img = idleLeft;
                }
                else if(lastDirection.equals("right")){
                    img = idleRight;
                }

        }

        g2.drawImage(img, x, y, gp.tileSize, gp.tileSize, null);

    }
}
