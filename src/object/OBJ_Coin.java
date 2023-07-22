package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Coin extends Entity {
    public OBJ_Coin(GamePanel gp) {
        super(gp);
        name = "Coin";
        down1 = setup("/objects/1", gp.tileSize, gp.tileSize);
    }
}
