package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RoadManager extends Entity {
    public RoadManager(GamePanel gp) {
        super(gp);
        solidArea.x = 12;
        solidArea.y = 12;
        solidArea.width = 72;
        solidArea.height = 24;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
    public void update() {

        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        //if car hit player
        if (contactPlayer) {
            if (!gp.player.invincible && !gp.player.Cheat) {
                gp.player.setDefaultPositions();
                gp.player.life -= 2;
                gp.player.invincible = true;
            }
        }
        switch (direction) {
            case "left" -> worldX -= speed;
            case "right" -> worldX += speed;
        }
        if(direction.equals("right") && worldX >= 1392) {worldX = -48;}
        if(direction.equals("left") && worldX <= -96) {worldX = 1344;}

        spriteCounter++;
        if (spriteCounter > 7) {

            if (spriteNum == 1) {spriteNum = 2;}
            else if (spriteNum == 2) {spriteNum = 3;}
            else if (spriteNum == 3) {spriteNum = 1;}
            spriteCounter = 0;
        }
    }
    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        g2.drawImage(image, worldX, worldY, null);

        switch (direction) {
            case "left" -> {
                if (spriteNum == 1) {image = left1;}
                if (spriteNum == 2) {image = left2;}
                if (spriteNum == 3) {image = left3;}
            }
            case "right" -> {
                if (spriteNum == 1) {image = right1;}
                if (spriteNum == 2) {image = right2;}
                if (spriteNum == 3) {image = right3;}
            }
        }
        g2.drawImage(image, worldX, worldY, null);

    }
}
