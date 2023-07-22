package entity;

import main.GamePanel;

public class NPC_Frog extends Entity{

    public boolean collision = true;
    public NPC_Frog(GamePanel gp) {
        super(gp);
        type = type_npc;
        direction = "left";
        speed = 2;
        getImage();
        setDialogue();
    }
    public void getImage() {
        left1 = setup("/npc/frog_left1",gp.tileSize-10,gp.tileSize);
        left2 = setup("/npc/frog_left2",gp.tileSize-10,gp.tileSize);
    }
    public void update() {
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if(collision) {
            //if npc hit player
            if (contactPlayer) {
                gp.gameState = gp.dialogueState;
                gp.npc[gp.currentMap][0].speak();
                collision = false;
                }
            }
        switch (direction) {
            case "left" -> worldX -= speed;
        }
        spriteCounter++;
        if (spriteCounter > 15) {

            if (spriteNum == 1) {spriteNum = 2;}
            else if (spriteNum == 2) {spriteNum = 1;}
            spriteCounter = 0;
        }
    }
    public void setDialogue() {

        dialogues[0] = """
                Welcome to Jumper Frog game.\s
                \s
                Try to pass between the cars\s
                \s
                when you reach the river, cross the river with the help of tree\s
                trunks and turtles to reach the next stage.\s
                \s
                It is between the airways that some things may cause the frog\s
                to die !\s
                \s
                good luck.\s
                \s
                                                                                 Press "Enter" Key""";
    }
    public void speak() {super.speak();}
}
