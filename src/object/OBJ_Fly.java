package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Fly extends Entity {
    public OBJ_Fly(GamePanel gp) {
        super(gp);
        name = "Fly";
        down1 = setup("/objects/fly",gp.tileSize,gp.tileSize);
    }
}
