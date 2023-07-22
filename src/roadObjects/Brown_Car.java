package roadObjects;

import entity.RoadManager;
import main.GamePanel;

public class Brown_Car extends RoadManager {
    public Brown_Car(GamePanel gp,int worldX, int worldY,String direction) {
        super(gp);
        this.gp = gp;
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        speed = 3;
        getImage();
    }
    public void getImage() {

        left1 = setup("/roadObjects/BrownCar_left", gp.tileSize * 2, gp.tileSize);
        left2 = setup("/roadObjects/BrownCar_left", gp.tileSize * 2, gp.tileSize);
        left3 = setup("/roadObjects/BrownCar_left", gp.tileSize * 2, gp.tileSize);
        right1 = setup("/roadObjects/BrownCar_right", gp.tileSize * 2, gp.tileSize);
        right2 = setup("/roadObjects/BrownCar_right", gp.tileSize * 2, gp.tileSize);
        right3 = setup("/roadObjects/BrownCar_right", gp.tileSize * 2, gp.tileSize);
    }
}
