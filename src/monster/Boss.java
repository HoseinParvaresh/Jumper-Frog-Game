package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_FinalStar;

import java.util.Random;

public class Boss extends Entity {

    GamePanel gp;

    public Boss(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_monster;
        name = "Spider";
        boss = true;
        speed = 3;
        direction = "left";
        maxLife = 10;
        life = maxLife;
        attack = 5;
        defense = 0;
        solidArea.x = 10;
        solidArea.y = 10;
        solidArea.width = 124;
        solidArea.height = 124;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();
    }
    public void getImage() {
        up1 = setup("/monster/spider_up1", gp.tileSize*3, gp.tileSize*3);
        up2 = setup("/monster/spider_up2", gp.tileSize*3, gp.tileSize*3);
        down1 = setup("/monster/spider_down1", gp.tileSize*3, gp.tileSize*3);
        down2 = setup("/monster/spider_down2", gp.tileSize*3, gp.tileSize*3);
        left1 = setup("/monster/spider_left1", gp.tileSize*3, gp.tileSize*3);
        left2 = setup("/monster/spider_left2", gp.tileSize*3, gp.tileSize*3);
        right1 = setup("/monster/spider_right1", gp.tileSize*3, gp.tileSize*3);
        right2 = setup("/monster/spider_right2", gp.tileSize*3, gp.tileSize*3);
    }
    public void setAction() {

        actionLockCounter++;

        if(actionLockCounter>=100) {

            Random random = new Random();
            int i = random.nextInt(100)+1;

            if(i<=40) {direction = "right";}
            if (i>40 && i<=80) {direction = "left";}
            if(i>80 && i<=90) {direction = "up";}
            if(i>90 && i<=100) {direction = "down";}
            actionLockCounter = 0;
        }
        if(worldX<=340) {direction = "right";}
        if(worldX>=855) {direction = "left";}
        if(worldY>=650) {direction = "up";}
        if(worldY<=100) {direction = "down";}
    }
    public void damageReaction() {
        actionLockCounter = 0;

        if(gp.player.direction.equals("up")) {direction = "down";}
        if(gp.player.direction.equals("down")) {direction = "up";}
        if(gp.player.direction.equals("left")) {direction = "right";}
        if(gp.player.direction.equals("right")) {direction = "left";}

    }
    public void checkDrop() {
        dropItem(new OBJ_FinalStar(gp));
    }
}
