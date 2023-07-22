package roadObjects;

import entity.RoadManager;
import main.GamePanel;

public class Chicken_Animal extends RoadManager {
    public Chicken_Animal(GamePanel gp, int worldX, int worldY,String direction) {
        super(gp);
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.gp = gp;
        solidArea.x = 12;
        solidArea.y = 12;
        solidArea.width = 24;
        solidArea.height = 24;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        speed = 3;
        getImage();
    }
    public void getImage() {

        left1 = setup("/roadObjects/chicken_left1", gp.tileSize  , gp.tileSize);
        left2 = setup("/roadObjects/chicken_left2", gp.tileSize  , gp.tileSize);
        left3 = setup("/roadObjects/chicken_left3", gp.tileSize  , gp.tileSize);
        right1 = setup("/roadObjects/chicken_right1", gp.tileSize  , gp.tileSize);
        right2 = setup("/roadObjects/chicken_right2", gp.tileSize  , gp.tileSize);
        right3 = setup("/roadObjects/chicken_right3", gp.tileSize  , gp.tileSize);
    }
}
