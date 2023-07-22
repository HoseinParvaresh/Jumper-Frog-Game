package waterObject;

import entity.WaterObject;
import main.GamePanel;

public class Turtle extends WaterObject {
    public Turtle(GamePanel gp, int worldX, int worldY, String direction) {
        super(gp);
        type = type_monster;
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.gp = gp;
        solidArea.x = 20;
        solidArea.y = 22;
        solidArea.width = 248;
        solidArea.height = 4;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        speed = 2;
        getImage();
    }
    public void getImage() {
        left1 = setup("/waterObject/turtle_left", gp.tileSize * 6, gp.tileSize);
        right1 = setup("/waterObject/turtle_right", gp.tileSize * 6, gp.tileSize);
    }
    public void damagePlayer(int attack) {
        gp.player.life -= 1;
        gp.player.invincible = true;
    }
}
