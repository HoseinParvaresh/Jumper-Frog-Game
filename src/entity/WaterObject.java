package entity;

import main.GamePanel;

public class WaterObject extends Entity {
    public WaterObject(GamePanel gp) {super(gp);}

    public void update() {
        boolean contactPlayer = gp.cChecker.checkPlayer(this);
        //if waterObject hit player
        if(contactPlayer) {
            switch (direction) {
                case "left" -> gp.player.worldX -= speed;
                case "right" -> gp.player.worldX += speed;
            }
        }
        switch (direction) {
            case "left" -> worldX -= speed;
            case "right" -> worldX += speed;
        }
        if(direction.equals("right")) {if(worldX >= 1395) {worldX = -290;}}
        if(direction.equals("left")) {if(worldX <= -195) {worldX = 1344;}}
        spriteNum = 1;
    }
}