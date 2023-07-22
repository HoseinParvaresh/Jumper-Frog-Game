package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Leaf extends Entity {
    public OBJ_Leaf(GamePanel gp) {
        super(gp);
        name = "Leaf";
        down1 = setup("/objects/leaf",gp.tileSize,gp.tileSize);
    }
}
