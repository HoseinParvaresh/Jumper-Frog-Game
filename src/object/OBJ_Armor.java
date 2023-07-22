package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Armor extends Entity {
    public OBJ_Armor(GamePanel gp) {
        super(gp);
        name = "Armor";
        down1 = setup("/objects/Armor",gp.tileSize +20,gp.tileSize);
    }
}
