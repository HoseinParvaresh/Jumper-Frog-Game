package roadObjects;

import entity.RoadManager;
import main.GamePanel;

public class BearBrown_Animal extends RoadManager {
    public BearBrown_Animal(GamePanel gp, int worldX, int worldY, String direction) {
        super(gp);
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.gp = gp;
        speed = 3;
        getImage();
    }
    public void getImage() {

        left1 = setup("/roadObjects/bear_left1", gp.tileSize * 2, gp.tileSize);
        left2 = setup("/roadObjects/bear_left2", gp.tileSize * 2, gp.tileSize);
        left3 = setup("/roadObjects/bear_left3", gp.tileSize * 2, gp.tileSize);
        right1 = setup("/roadObjects/bear_right1", gp.tileSize * 2, gp.tileSize);
        right2 = setup("/roadObjects/bear_right2", gp.tileSize * 2, gp.tileSize);
        right3 = setup("/roadObjects/bear_right2", gp.tileSize * 2, gp.tileSize);
    }
}
