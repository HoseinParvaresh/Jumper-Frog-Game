package roadObjects;

import entity.RoadManager;
import main.GamePanel;

public class Red_Car1 extends RoadManager {
    public Red_Car1(GamePanel gp,int worldX, int worldY,String direction) {
        super(gp);
        this.gp = gp;
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        speed = 3;
        getImage();
    }
    public void getImage() {
        left1 = setup("/roadObjects/RedCar1_left", gp.tileSize * 2, gp.tileSize);
        left2 = setup("/roadObjects/RedCar1_left", gp.tileSize * 2, gp.tileSize);
        left3 = setup("/roadObjects/RedCar1_left", gp.tileSize * 2, gp.tileSize);
        right1 = setup("/roadObjects/RedCar1_right", gp.tileSize * 2, gp.tileSize);
        right2 = setup("/roadObjects/RedCar1_right", gp.tileSize * 2, gp.tileSize);
        right3 = setup("/roadObjects/RedCar1_right", gp.tileSize * 2, gp.tileSize);
    }
}
