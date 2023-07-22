package monster;

import entity.Entity;
import main.GamePanel;
import object.*;

import java.util.Random;

public class Spider extends Entity {

    GamePanel gp;

    public Spider(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_monster;
        name = "Spider";
        speed = 1;
        direction = "left";
        maxLife = 1;
        life = maxLife;
        attack = 5;
        defense = 0;
        solidArea.x = 6;
        solidArea.y = 6;
        solidArea.width = 30;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();
    }
    public void getImage() {
        up1 = setup("/monster/spider_up1", gp.tileSize-10, gp.tileSize-10);
        up2 = setup("/monster/spider_up2", gp.tileSize-10, gp.tileSize-10);
        down1 = setup("/monster/spider_down1", gp.tileSize-10, gp.tileSize-10);
        down2 = setup("/monster/spider_down2", gp.tileSize-10, gp.tileSize-10);
        left1 = setup("/monster/spider_left1", gp.tileSize-10, gp.tileSize-10);
        left2 = setup("/monster/spider_left2", gp.tileSize-10, gp.tileSize-10);
        right1 = setup("/monster/spider_right1", gp.tileSize-10, gp.tileSize-10);
        right2 = setup("/monster/spider_right2", gp.tileSize-10, gp.tileSize-10);
    }
    public void setAction() {

        actionLockCounter++;

        if(actionLockCounter>=300) {

            Random random = new Random();
            int i = random.nextInt(100)+1;

            if(i<=40) {direction = "right";}
            if (i>40 && i<=80) {direction = "left";}
            if(i>80 && i<=90) {direction = "up";}
            if(i>90 && i<=100) {direction = "down";}
            actionLockCounter = 0;
        }
        if(worldX<50) {direction = "right";}
        if(worldX>1148) {direction = "left";}
        if(worldY > 335) {direction = "up";}
        if(worldY < 280) {direction = "down";}
    }
    public void checkDrop() {

        Random random = new Random();
        int i = random.nextInt(100)+1;

        if (i<=10) {dropItem(new OBJ_Heart(gp));} //10%
        if(i>10 && i<=25) { dropItem(new OBJ_Star(gp));} //15%
        if(i>25 && i<=40) {dropItem(new OBJ_Armor(gp));} //15%
        if(i>40 && i<=55) {dropItem(new OBJ_Extra_Heart(gp));} //15%
        if(i>55 && i<=70) {dropItem(new OBJ_Time(gp));} //15%
        if(i>70 && i<=100) {} //30%
    }
}
