package entity;

import java.awt.image.BufferedImage;

public class Entity {

    public int x, y;
    public int speed;

    //It describes an Image with an accessible buffer of image data.
    // We use this to store out image files.
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2,
            idleDown, idleUp, idleRight, idleLeft;
    public String direction;
    public String lastDirection;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int idleCounter = 0;
    public int idleNum = 1;
}
