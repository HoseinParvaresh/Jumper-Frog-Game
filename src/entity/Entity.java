package entity;

import main.GamePanel;
import main.UtilityTool;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class Entity {

    public GamePanel gp;

    public BufferedImage up1, up2,down1,down2,left1,left2,left3,left4,right1,right2,right3,right4;
    public BufferedImage image, image2, image3, image4, image5, image6;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    String[] dialogues = new String[20];

    //State
    public int worldX, worldY;
    public String direction = "down";
    public int spriteNum = 1;
    int dialogueIndex = 0;
    public boolean collisionOn = false;
    public boolean invincible = false;
    public boolean CheatMessageOn = true;
    public boolean CheatMessageOff = true;
    public boolean Cheat = false;
    public boolean alive = true;
    public boolean dying = false;
    public boolean boss;
    public boolean leafObject = false;


    //Counter
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int hpBarCounter = 0;
    public int invincibleCounter = 0;
    public int shotAvailableCounter = 0;
    int dyingCounter = 0;

    //ویژگی های کاراکترها
    public String name;
    public int speed;
    public int maxLife;
    public int life;
    public int attack;
    public int defense;
    public Projectile projectile;

    //type
    public int type; // 0 = player, 1 = npc, 2 = monster
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }
    public void setAction() {}
    public void damageReaction() {}
    public void checkDrop() {}
    public void speak() {

        if (dialogues[dialogueIndex] == null) {dialogueIndex = 0;}
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;
    }
    public void dropItem(Entity droppedItem) {

        for(int i=0;i< gp.obj[1].length; i++) {
            if(gp.obj[gp.currentMap][i] == null) {
                gp.obj[gp.currentMap][i] = droppedItem;
                gp.obj[gp.currentMap][i].worldX = worldX; //جایی که monster مرده
                gp.obj[gp.currentMap][i].worldY = worldY;
                break;
            }
        }
    }
    public void update() {

        setAction();
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.roadObjects);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        //if monster hit player
        if (this.type == type_monster && contactPlayer) {
            if (!gp.player.invincible && !gp.player.Cheat) {damagePlayer(attack);}
        }
        //if collision false player can move
        if (!collisionOn) {
            switch (direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
            }
        }
        spriteCounter++;
        if (spriteCounter > 15) {

            if (spriteNum == 1) {spriteNum = 2;}
            else if (spriteNum == 2) {spriteNum = 1;}

            spriteCounter = 0;
        }
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if(shotAvailableCounter < 60) {shotAvailableCounter++;}
    }
    public void damagePlayer(int attack) {
        gp.player.life-=1;
        gp.player.invincible= true;
    }
    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        g2.drawImage(image, worldX, worldY, null);

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
        if (invincible) {changeAlpha(g2,0.4f);}
        if (dying) {dyingAnimation(g2);}

        g2.drawImage(image, worldX, worldY, null);
        changeAlpha(g2,1f);
    }
    public void dyingAnimation(Graphics2D g2) { //انیمیشن مردن

        dyingCounter++;
        int i = 5;

        if(dyingCounter <=i) {changeAlpha(g2,0f);}
        if(dyingCounter > i && dyingCounter <=i*2) {changeAlpha(g2,1f);}
        if(dyingCounter > i*2 && dyingCounter <=i*3) {changeAlpha(g2,0f);}
        if(dyingCounter > i*3 && dyingCounter <=i*4) {changeAlpha(g2,1f);}
        if(dyingCounter > i*4 && dyingCounter <=i*5) {changeAlpha(g2,0f);}
        if(dyingCounter > i*5 && dyingCounter <=i*6) {changeAlpha(g2,1f);}
        if(dyingCounter > i*6 && dyingCounter <=i*7) {changeAlpha(g2,0f);}
        if(dyingCounter > i*7 && dyingCounter <=i*8) {changeAlpha(g2,1f);}
        if(dyingCounter > i*8) {alive = false;}
    }
    public void changeAlpha(Graphics2D g2, float alphaValue) {g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));}

    public BufferedImage setup(String imagePath, int width, int height) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage Image = null;
        try {
            Image = read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            Image = uTool.scaleImage(Image, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Image;
    }
}
