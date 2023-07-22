package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Star extends Entity {
    public OBJ_Star(GamePanel gp) {
        super(gp);
        name = "Star";
        down1 = setup("/objects/star",gp.tileSize,gp.tileSize);
    }
}