package roadObjects;

import entity.RoadManager;
import main.GamePanel;

public class Blue_Car extends RoadManager {
    public Blue_Car(GamePanel gp, int worldX, int worldY,String direction) {
        super(gp);
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.gp = gp;
        speed = 3;
        getImage();
    }
    public void getImage() {

        left1 = setup("/roadObjects/BlueCar_left", gp.tileSize * 2, gp.tileSize);
        left2 = setup("/roadObjects/BlueCar_left", gp.tileSize * 2, gp.tileSize);
        left3 = setup("/roadObjects/BlueCar_left", gp.tileSize * 2, gp.tileSize);
        right1 = setup("/roadObjects/BlueCar_right", gp.tileSize * 2, gp.tileSize);
        right2 = setup("/roadObjects/BlueCar_right", gp.tileSize * 2, gp.tileSize);
        right3 = setup("/roadObjects/BlueCar_right", gp.tileSize * 2, gp.tileSize);
    }
}
