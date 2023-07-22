package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Time extends Entity {
    public OBJ_Time(GamePanel gp) {
        super(gp);
        name = "Time";
        down1 = setup("/objects/time",gp.tileSize,gp.tileSize);
    }
}
