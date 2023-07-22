package roadObjects;

import entity.RoadManager;
import main.GamePanel;

public class Bus_Car extends RoadManager {
    public Bus_Car(GamePanel gp,int worldX, int worldY,String direction) {
        super(gp);
        this.gp = gp;
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        speed = 3;
        solidArea.x = 12;
        solidArea.y = 12;
        solidArea.width = 160;
        solidArea.height = 24;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();
    }
    public void getImage() {
        left1 = setup("/roadObjects/BusCar_left", gp.tileSize * 4, gp.tileSize);
        left2 = setup("/roadObjects/BusCar_left", gp.tileSize * 4, gp.tileSize);
        left3 = setup("/roadObjects/BusCar_left", gp.tileSize * 4, gp.tileSize);
        right1 = setup("/roadObjects/BusCar_right", gp.tileSize * 4, gp.tileSize);
        right2 = setup("/roadObjects/BusCar_right", gp.tileSize * 4, gp.tileSize);
        right3 = setup("/roadObjects/BusCar_right", gp.tileSize * 4, gp.tileSize);
    }
}
