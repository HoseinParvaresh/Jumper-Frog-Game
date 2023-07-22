package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_FinalStar extends Entity {

    public OBJ_FinalStar(GamePanel gp) {
        super(gp);
        name = "FinalStar";
        solidArea.width = 120;
        solidArea.height = 120 ;
        down1 = setup("/objects/starfinal",gp.tileSize*3,gp.tileSize*3);
    }
}
