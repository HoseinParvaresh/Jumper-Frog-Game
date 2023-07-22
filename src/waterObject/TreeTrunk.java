package waterObject;

import entity.WaterObject;
import main.GamePanel;

public class TreeTrunk extends WaterObject {
    public TreeTrunk (GamePanel gp, int worldX, int worldY,String direction) {
        super(gp);
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.gp = gp;
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 192;
        solidArea.height = 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        speed = 3;
        getImage();
    }
    public void getImage() {
        left1 = setup("/waterObject/treetruck_left", gp.tileSize * 4, gp.tileSize);
        right1 = setup("/waterObject/treetruck_right", gp.tileSize * 4, gp.tileSize);
    }
}
