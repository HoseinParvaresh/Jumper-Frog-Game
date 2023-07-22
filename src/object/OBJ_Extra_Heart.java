package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Extra_Heart extends Entity {

    public OBJ_Extra_Heart(GamePanel gp) {
        super(gp);
        name = "Extra Heart";
        down1 = setup("/objects/extraHeart",gp.tileSize,gp.tileSize);
    }
}
