package entity;

import main.GamePanel;
import main.KeyHandler;
import object.OBJ_Fireball;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    KeyHandler keyH;
    int standCounter = 0;
    public boolean moving = false;
    int pixelCounter = 0;

    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);
        this.keyH = keyH;
        solidArea = new Rectangle(1, 1, 46, 46);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDefaultValues();
    }
    public void setDefaultValues() {
        getPlayerImage();
        worldX = gp.tileSize * 12;
        worldY = gp.tileSize * 15;
        speed = 3; //سرعت کاراکتر
        direction = "up";
        maxLife = 6;
        life = maxLife;
        projectile = new OBJ_Fireball(gp);
    }
    public void setDefaultPositions() {
        worldX = gp.tileSize * 12;
        worldY = gp.tileSize * 15;
        direction = "up";
    }
    public void getPlayerImage() {
        up1 = setup("/Player/frog_up1", gp.tileSize, gp.tileSize);
        up2 = setup("/Player/frog_up3", gp.tileSize, gp.tileSize);
        down1 = setup("/Player/frog_down1", gp.tileSize, gp.tileSize);
        down2 = setup("/Player/frog_down3", gp.tileSize, gp.tileSize);
        right1 = setup("/Player/frog_right1", gp.tileSize, gp.tileSize);
        right2 = setup("/Player/frog_right3", gp.tileSize, gp.tileSize);
        left1 = setup("/Player/frog_left1", gp.tileSize, gp.tileSize);
        left2 = setup("/Player/frog_left3", gp.tileSize, gp.tileSize);
    }
    public void getPlayerImage1() {
        up1 = setup("/Player1/frog_up1", gp.tileSize, gp.tileSize);
        up2 = setup("/Player1/frog_up2", gp.tileSize, gp.tileSize);
        down1 = setup("/Player1/frog_down1", gp.tileSize, gp.tileSize);
        down2 = setup("/Player1/frog_down2", gp.tileSize, gp.tileSize);
        right1 = setup("/Player1/frog_right1", gp.tileSize, gp.tileSize);
        right2 = setup("/Player1/frog_right2", gp.tileSize, gp.tileSize);
        left1 = setup("/Player1/frog_left1", gp.tileSize, gp.tileSize);
        left2 = setup("/Player1/frog_left2", gp.tileSize, gp.tileSize);
    }
    public void getPlayerImage2() {
        up1 = setup("/Player2/panda_up1", gp.tileSize, gp.tileSize);
        up2 = setup("/Player2/panda_up2", gp.tileSize, gp.tileSize);
        down1 = setup("/Player2/panda_down1", gp.tileSize, gp.tileSize);
        down2 = setup("/Player2/panda_down2", gp.tileSize, gp.tileSize);
        right1 = setup("/Player2/panda_right1", gp.tileSize, gp.tileSize);
        right2 = setup("/Player2/panda_right2", gp.tileSize, gp.tileSize);
        left1 = setup("/Player2/panda_left1", gp.tileSize, gp.tileSize);
        left2 = setup("/Player2/panda_left2", gp.tileSize, gp.tileSize);
    }
    public void getPlayerImage3() {
        up1 = setup("/Player3/turtle_up1", gp.tileSize, gp.tileSize);
        up2 = setup("/Player3/turtle_up2", gp.tileSize, gp.tileSize);
        down1 = setup("/Player3/turtle_down1", gp.tileSize, gp.tileSize);
        down2 = setup("/Player3/turtle_down2", gp.tileSize, gp.tileSize);
        right1 = setup("/Player3/turtle_right1", gp.tileSize, gp.tileSize);
        right2 = setup("/Player3/turtle_right2", gp.tileSize, gp.tileSize);
        left1 = setup("/Player3/turtle_left1", gp.tileSize, gp.tileSize);
        left2 = setup("/Player3/turtle_left2", gp.tileSize, gp.tileSize);
    }
    public void update() {

        if (!moving) {

            if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {

                if (keyH.upPressed) {direction = "up";}
                else if (keyH.downPressed) {direction = "down";}
                else if (keyH.leftPressed) {direction = "left";}
                else if (keyH.rightPressed) {direction = "right";}
                moving = true;

                //چک کردن برخورد با کاشی
                collisionOn = false;
                gp.cChecker.checkTile(this);

                //چک کردن برخورد با object
                int objIndex = gp.cChecker.checkObject(this, true);
                PickUpObject(objIndex);

                //چک کردن برخورد با npc
                int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
                interactNPC(npcIndex);

                //چک کردن برخورد با road
                int roadIndex = gp.cChecker.checkEntity(this,gp.roadObjects);
                contactRoad(roadIndex);

                //چک کردن برخورد با monster
                int monsterIndex = gp.cChecker.checkEntity(this,gp.monster);
                contactMonster(monsterIndex);

                //چک کردن اتفاق ها
                gp.eHandler.checkEvent();
            } else {
                standCounter++;

                if (standCounter == 20) {
                    spriteNum = 1;
                    standCounter = 0;
                }
            }
        }
        if (moving) {

            //اگه برخورد false باشه بازیکن نتونه حرکت کنه

            if (!collisionOn) {
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }
            spriteCounter++;
            if (spriteCounter > 10) {

                if (spriteNum == 1) {spriteNum = 2;}
                else if (spriteNum == 2) {spriteNum = 1;}

                spriteCounter = 0;
            }
            pixelCounter += speed;

            if (pixelCounter == 48) {
                moving = false;
                pixelCounter = 0;
            }
        }
        if(gp.keyH.shotKeyPressed && !projectile.alive) {

            projectile.set(worldX,worldY,direction,true,this);

            gp.projectileList.add(projectile);
        }
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 30) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if(shotAvailableCounter < 60) {
            shotAvailableCounter++;
        }
        if(life <= 0) {
            gp.gameState = gp.gameOverState;
            gp.playSE(12);
        }
    }
    public void PickUpObject(int i) { //برداشتن آیتم
        if (i != 999) {
            String text;
            String objectName = gp.obj[gp.currentMap][i].name;
            text = "Got a " + gp.obj[gp.currentMap][i].name + "!";
            gp.obj[gp.currentMap][i] = null;
            gp.ui.addMessage(text);

            switch (objectName) {

                case "Speed" -> {
                    speed += 1;
                    gp.playSE(1);
                }
                case "Armor" -> {
                    maxLife+=2;
                    gp.playSE(4);
                }
                case "Extra Heart" -> {
                    if(life==maxLife) {
                        life+=2;
                        maxLife +=2;
                        gp.playSE(6);
                    } else if (life<maxLife) {
                        life+=2;
                        gp.playSE(6);
                    }
                }
                case "Star" -> {
                    gp.playSE(10);
                    gp.ui.score +=100;
                }
                case "Heart" -> {
                    gp.playSE(9);
                    life = maxLife;
                }
                case "Time" -> {
                    gp.playSE(3);
                    gp.ui.playTime +=20;
                    gp.ui.getPlayTime = gp.ui.playTime;
                }
                case "Leaf" -> {
                    gp.playSE(5);
                    leafObject = true;
                }
                case "Fly" -> {
                    gp.playSE(5);
                    gp.ui.score+=50;
                    invincible = true;
                    // write later
                }
                case "FinalStar" -> {
                    gp.gameState = gp.finishGameState;
                    gp.config.saveConfig();
                    gp.playSE(16);
                }
            }
        }
    }
    public void interactNPC(int i) { //برخورد با npc
        if (i != 999) {
            gp.gameState = gp.dialogueState;
            gp.npc[gp.currentMap][i].speak();
        }
    }
    public void contactMonster(int i) { //برخورد با monster
        if(i !=999) {

            if (!invincible && !gp.monster[gp.currentMap][i].dying && !Cheat) {
                invincible = true;
                life -= 1;
              //  gp.playSE(3);
            }
        }
    }
    public void contactRoad(int i) { //if player hit roadObject
        if(i !=999) {
            if(!invincible && !Cheat) {
                invincible = true;
                life -= 2;
                setDefaultValues();
            }
        }
    }
    public void damageMonster(int i) { //دمیج دادن به monster
        if (i != 999) {
            if (!gp.monster[gp.currentMap][i].invincible) {
               // gp.playSE(3);
                gp.monster[gp.currentMap][i].life -= 1;
                gp.monster[gp.currentMap][i].invincible = true;
                gp.monster[gp.currentMap][i].damageReaction();

                if (gp.monster[gp.currentMap][i].life <= 0) {
                    gp.monster[gp.currentMap][i].dying = true;
                }
            }
            if(gp.monster[3][10].dying) {
                gp.stopMusic();
            }
        }
    }
    public void draw(Graphics2D g2) { //نمایش عکس ها در صفحه نمایش اصلی

        BufferedImage image = null;
        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) {image = up1;}
                if (spriteNum == 2) {image = up2;}
            }
            case "down" -> {
                if (spriteNum == 1) {image = down1;}
                if (spriteNum == 2) {image = down2;}
            }
            case "left" -> {
                if (spriteNum == 1) {image = left1;}
                if (spriteNum == 2) {image = left2;}
            }
            case "right" -> {
                if (spriteNum == 1) {image = right1;}
                if (spriteNum == 2) {image = right2;}
            }
        }
        //cheat code
        if(keyH.cheatOn) {
            Cheat = true;
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));

            if (CheatMessageOn) {
                String text;
                text = "Cheat code activated!";
                gp.ui.addMessage(text);
                CheatMessageOn = false;
                CheatMessageOff = true;
            }
        }
        if(keyH.cheatOff) {
            keyH.cheatOn = false;
            Cheat = false;
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

            if (CheatMessageOff) {
                String text;
                text = "Cheat code Unactivated!";
                gp.ui.addMessage(text);
                CheatMessageOff = false;
                CheatMessageOn = true;
            }
        }
        if (invincible) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2.drawImage(image, worldX, worldY, null);
        //ریست آلفا
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
