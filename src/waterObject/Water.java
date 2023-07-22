package waterObject;

import entity.WaterObject;
import main.GamePanel;

public class Water extends WaterObject {
    public Water(GamePanel gp, int worldX, int worldY, String direction) {
        super(gp);
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.gp = gp;
        solidArea.x = 8;
        solidArea.y = 8;
        solidArea.width = 176;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        speed = 2;
        getImage();
    }
    public void update() {
        boolean contactPlayer = gp.cChecker.checkPlayer(this);
        //if waterObject hit player
        if(contactPlayer && !gp.player.leafObject) {
            gp.player.collisionOn = false;
            gp.player.invincible = true;
            gp.player.setDefaultValues();
        }
        switch (direction) {
            case "left" -> worldX -= speed;
            case "right" -> worldX += speed;
        }
        if(direction.equals("right")) {if(worldX >= 1395) {worldX = -290;}}
        if(direction.equals("left")) {if(worldX <= -195) {worldX = 1344;}}
        spriteNum = 1;
    }
    public void getImage() {
        left1 = setup("/tiles/water00", gp.tileSize * 4  , gp.tileSize);
        right1 = setup("/tiles/water00", gp.tileSize * 4, gp.tileSize);
    }
}
