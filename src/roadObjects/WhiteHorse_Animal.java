package roadObjects;

import entity.RoadManager;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WhiteHorse_Animal extends RoadManager {
    public WhiteHorse_Animal(GamePanel gp, int worldX, int worldY,String direction) {
        super(gp);
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.gp = gp;
        solidArea.x = 12;
        solidArea.y = 20;
        solidArea.width = 24;
        solidArea.height = 8;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        speed = 3;
        getImage();
    }
    public void getImage() {

        left1 = setup("/roadObjects/horse_white_left1", gp.tileSize * 3, gp.tileSize+22);
        left2 = setup("/roadObjects/horse_white_left2", gp.tileSize * 3, gp.tileSize+22);
        left3 = setup("/roadObjects/horse_white_left3", gp.tileSize * 3, gp.tileSize+22);
        left4 = setup("/roadObjects/horse_white_left4", gp.tileSize * 3, gp.tileSize+22);
        right1 = setup("/roadObjects/horse_white_right1", gp.tileSize * 3, gp.tileSize+22);
        right2 = setup("/roadObjects/horse_white_right2", gp.tileSize * 3, gp.tileSize+22);
        right3 = setup("/roadObjects/horse_white_right3", gp.tileSize * 3, gp.tileSize+22);
        right4 = setup("/roadObjects/horse_white_right4", gp.tileSize * 3, gp.tileSize+22);
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
            else if (spriteNum == 3) {spriteNum = 4;}
            else if (spriteNum == 4) {spriteNum = 1;}
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
                if (spriteNum == 4) {image = left4;}
            }
            case "right" -> {
                if (spriteNum == 1) {image = right1;}
                if (spriteNum == 2) {image = right2;}
                if (spriteNum == 3) {image = right3;}
                if (spriteNum == 4) {image = right4;}
            }
        }
        g2.drawImage(image, worldX, worldY, null);

    }
}
