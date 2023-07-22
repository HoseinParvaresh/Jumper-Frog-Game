package monster;

import entity.Entity;
import main.GamePanel;
import object.*;

import java.util.Random;

public class Snake extends Entity {

    GamePanel gp;

    public Snake(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_monster;
        name = "Snake";
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
        projectile = new OBJ_Fireball(gp);
        getImage();
    }
    public void getImage() {
        up1 = setup("/monster/snake_up1", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/snake_up2", gp.tileSize, gp.tileSize);
        down1 = setup("/monster/snake_down1", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/snake_down2", gp.tileSize, gp.tileSize);
        left1 = setup("/monster/snake_left1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/snake_left2", gp.tileSize, gp.tileSize);
        right1 = setup("/monster/snake_right1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/snake_right2", gp.tileSize, gp.tileSize);
    }
    public void setAction() {

        actionLockCounter++;

        if (actionLockCounter >= 300) {

            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 40) {direction = "right";}
            if (i > 40 && i <= 80) {direction = "left";}
            if (i > 80 && i <= 90) {direction = "up";}
            if (i > 90 && i <= 100) {direction = "down";}
            actionLockCounter = 0;
        }
        if (worldX < 50) {direction = "right";}
        if (worldX > 1148) {direction = "left";}
        if (worldY > 335) {direction = "up";}
        if (worldY < 280) {direction = "down";}

//        if ( !projectile.alive && shotAvailableCounter == 60) {
//            projectile.set(worldX, worldY, "down", true, this);
//            gp.projectileList.add(projectile);
//            shotAvailableCounter = 0;
//        }
    }
    public void damageReaction() {
        actionLockCounter = 0;

        if(gp.player.direction.equals("up")) {direction = "down";}
        if(gp.player.direction.equals("down")) {direction = "up";}
        if(gp.player.direction.equals("left")) {direction = "right";}
        if(gp.player.direction.equals("right")) {direction = "left";}

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
