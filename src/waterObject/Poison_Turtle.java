package waterObject;

import entity.WaterObject;
import main.GamePanel;

public class Poison_Turtle extends WaterObject {
    public Poison_Turtle (GamePanel gp,int worldX, int worldY,String direction) {
        super(gp);
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
        left1 = setup("/waterObject/turtle__poison_left", gp.tileSize * 6, gp.tileSize);
        right1 = setup("/waterObject/turtle__poison_right", gp.tileSize * 6, gp.tileSize);
    }
    public void update () {
        boolean contactPlayer = gp.cChecker.checkPlayer(this);
        //if waterObject hit player
        if(contactPlayer) {
            switch (direction) {
                case "left" -> gp.player.worldX -= speed;
                case "right" -> gp.player.worldX += speed;
            }
            gp.player.life-=1;
            gp.player.setDefaultPositions();
        }
        switch (direction) {
            case "left" -> worldX -= speed;
            case "right" -> worldX += speed;
        }
        if(direction.equals("right")) {
            if(worldX >= 1395) { //tile size 29
                worldX = -290; //tile size -2
            }
        }
        if(direction.equals("left")) {
            if(worldX <= -195) { //tile size -4
                worldX = 1344; //tile size 28
            }
        }
        spriteNum = 1;
    }
}
