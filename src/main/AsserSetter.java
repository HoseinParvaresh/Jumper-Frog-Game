package main;

import entity.Entity;
import entity.NPC_Frog;
import monster.Boss;
import monster.Snake;
import monster.Spider;
import object.OBJ_Fly;
import object.OBJ_Leaf;
import roadObjects.*;
import waterObject.Poison_Turtle;
import waterObject.TreeTrunk;
import waterObject.Turtle;
import waterObject.Water;

import java.util.Random;

public class AsserSetter extends Entity {

    GamePanel gp;

    public AsserSetter(GamePanel gp) {
        super(gp);
        this.gp = gp;
    }
    public void setObject() {
        int mapNum = 0;

        Random random = new Random();
        int i = random.nextInt(100)+1;
         if(i<=50) {
             int j = random.nextInt(2) + 11;
             int l = random.nextInt(23) + 1;
             gp.obj[mapNum][0] = new OBJ_Fly(gp);
             gp.obj[mapNum][0].worldX = gp.tileSize * l;
             gp.obj[mapNum][0].worldY = gp.tileSize * j;
         }
         if(i>50 && i<=100) {
             int j = random.nextInt(2) + 11;
             int l = random.nextInt(23) + 1;
             gp.obj[mapNum][1] = new OBJ_Leaf(gp);
             gp.obj[mapNum][1].worldX = gp.tileSize * l;
             gp.obj[mapNum][1].worldY = gp.tileSize * j;
         }

    }
    public void setNPC() {
        int mapNum = 0;

        gp.npc[mapNum][0] = new NPC_Frog(gp);
        gp.npc[mapNum][0].worldX = gp.tileSize * 27;
        gp.npc[mapNum][0].worldY = gp.tileSize * 15;

    }
    public void setMonster() {
        int mapNum = 0;

        //map 1:
        gp.monster[mapNum][0] = new Snake(gp);
        gp.monster[mapNum][0].worldX = gp.tileSize * 4;
        gp.monster[mapNum][0].worldY = gp.tileSize * 7;

        gp.monster[mapNum][1] = new Snake(gp);
        gp.monster[mapNum][1].worldX = gp.tileSize * 8;
        gp.monster[mapNum][1].worldY = gp.tileSize * 7;

        gp.monster[mapNum][2] = new Snake(gp);
        gp.monster[mapNum][2].worldX = gp.tileSize * 10;
        gp.monster[mapNum][2].worldY = gp.tileSize * 7;

        gp.monster[mapNum][3] = new Snake(gp);
        gp.monster[mapNum][3].worldX = gp.tileSize * 16;
        gp.monster[mapNum][3].worldY = gp.tileSize * 7;

        gp.monster[mapNum][4] = new Snake(gp);
        gp.monster[mapNum][4].worldX = gp.tileSize * 22;
        gp.monster[mapNum][4].worldY = gp.tileSize * 7;

        //map 2 :
        mapNum++;

        gp.monster[mapNum][5] = new Spider(gp);
        gp.monster[mapNum][5].worldX = gp.tileSize * 4;
        gp.monster[mapNum][5].worldY = gp.tileSize * 7;

        gp.monster[mapNum][6] = new Spider(gp);
        gp.monster[mapNum][6].worldX = gp.tileSize * 8;
        gp.monster[mapNum][6].worldY = gp.tileSize * 7;

        gp.monster[mapNum][7] = new Spider(gp);
        gp.monster[mapNum][7].worldX = gp.tileSize * 10;
        gp.monster[mapNum][7].worldY = gp.tileSize * 7;

        gp.monster[mapNum][8] = new Spider(gp);
        gp.monster[mapNum][8].worldX = gp.tileSize * 16;
        gp.monster[mapNum][8].worldY = gp.tileSize * 7;

        gp.monster[mapNum][9] = new Spider(gp);
        gp.monster[mapNum][9].worldX = gp.tileSize * 22;
        gp.monster[mapNum][9].worldY = gp.tileSize * 7;

        //maps 3 :
        mapNum+=2;
        gp.monster[mapNum][10] = new Boss(gp);
        gp.monster[mapNum][10].worldX = gp.tileSize * 12;
        gp.monster[mapNum][10].worldY = gp.tileSize * 6;
    }
    public void roadObjects() {
        int mapNum = 0;
        int i = 0;

        //map 1 :

        //road 1
        gp.roadObjects[mapNum][i] = new Brown_Car(gp,gp.tileSize *31,gp.tileSize *13,"left");
        i++;
        gp.roadObjects[mapNum][i] = new Yellow_Car(gp,gp.tileSize *21,gp.tileSize *13,"left");
        i++;
        gp.roadObjects[mapNum][i] = new Red_Car(gp,gp.tileSize *11,gp.tileSize *13,"left");
        i++;

        //road 2
        gp.roadObjects[mapNum][i] = new Bus_Car(gp,gp.tileSize *16,gp.tileSize *12,"right");
        gp.roadObjects[mapNum][i].speed = 2;
        i++;
        gp.roadObjects[mapNum][i] = new White_Car(gp,gp.tileSize *7,gp.tileSize *12,"right");
        gp.roadObjects[mapNum][i].speed = 2;
        i++;
        gp.roadObjects[mapNum][i] = new Orange_Car(gp,gp.tileSize *-2,gp.tileSize *12,"right");
        gp.roadObjects[mapNum][i].speed = 2;
        i++;

        //road 3
        gp.roadObjects[mapNum][i] = new Red_Car1(gp,gp.tileSize *7,gp.tileSize *11,"left");
        i++;
        gp.roadObjects[mapNum][i] = new Police_Car(gp,gp.tileSize *27,gp.tileSize *11,"left");
        i++;
        gp.roadObjects[mapNum][i] = new Bus_Car(gp,gp.tileSize * 16,gp.tileSize *11,"left");
        i++;

        //road 4
        gp.roadObjects[mapNum][i] = new Brown_Car(gp,gp.tileSize *17,gp.tileSize *10,"right");
        gp.roadObjects[mapNum][i].speed = 2;
        i++;
        gp.roadObjects[mapNum][i] = new Blue_Car(gp,gp.tileSize *7,gp.tileSize *10,"right");
        gp.roadObjects[mapNum][i].speed = 2;
        i++;
        gp.roadObjects[mapNum][i] = new Purple_Car(gp,gp.tileSize *27,gp.tileSize *10,"right");
        gp.roadObjects[mapNum][i].speed = 2;
        i++;

        //road 5
        gp.roadObjects[mapNum][i] = new Red_Car1(gp,gp.tileSize *2,gp.tileSize *9,"left");
        i++;
        gp.roadObjects[mapNum][i] = new Orange_Car(gp,gp.tileSize *22,gp.tileSize *9,"left");
        i++;
        gp.roadObjects[mapNum][i] = new White_Car(gp,gp.tileSize *12,gp.tileSize *9,"left");
        i++;

        //road 6
        gp.roadObjects[mapNum][i] = new Red_Car(gp,gp.tileSize *-1,gp.tileSize *8,"right");
        gp.roadObjects[mapNum][i].speed = 2;
        i++;
        gp.roadObjects[mapNum][i] = new Police_Car(gp,gp.tileSize *9,gp.tileSize *8,"right");
        gp.roadObjects[mapNum][i].speed = 2;
        i++;
        gp.roadObjects[mapNum][i] = new Blue_Car(gp,gp.tileSize *19,gp.tileSize *8,"right");
        gp.roadObjects[mapNum][i].speed = 2;

        mapNum++;


        //map 2 :


        //road 1
        gp.roadObjects[mapNum][i] = new Chicken_Animal(gp,gp.tileSize *31,gp.tileSize *13,"left");
        i++;
        gp.roadObjects[mapNum][i] = new BlackHorse_Animal(gp,gp.tileSize *11,gp.tileSize *13 -17,"left");
        i++;
        gp.roadObjects[mapNum][i] = new BearWhite_Animal(gp,gp.tileSize *21,gp.tileSize *13,"left");
        i++;

        //road 2
        gp.roadObjects[mapNum][i] = new Cow_Animal(gp,gp.tileSize *17,gp.tileSize *12,"right");
        gp.roadObjects[mapNum][i].speed = 2;
        i++;
        gp.roadObjects[mapNum][i] = new Lama_Animal(gp,gp.tileSize *7,gp.tileSize *12,"right");
        gp.roadObjects[mapNum][i].speed = 2;
        i++;
        gp.roadObjects[mapNum][i] = new Pig_Animal(gp,gp.tileSize *-2,gp.tileSize *12,"right");
        gp.roadObjects[mapNum][i].speed = 2;
        i++;

        //road 3
        gp.roadObjects[mapNum][i] = new BearBrown_Animal(gp,gp.tileSize *7,gp.tileSize *11,"left");
        i++;
        gp.roadObjects[mapNum][i] = new GoldenHorse_Animal(gp,gp.tileSize *27,gp.tileSize *11-17,"left");
        i++;
        gp.roadObjects[mapNum][i] = new Lama_Animal(gp,gp.tileSize * 16,gp.tileSize *11,"left");
        i++;

        //road 4
        gp.roadObjects[mapNum][i] = new BearWhite_Animal(gp,gp.tileSize *17,gp.tileSize *10,"right");
        gp.roadObjects[mapNum][i].speed = 2;
        i++;
        gp.roadObjects[mapNum][i] = new Cow_Animal(gp,gp.tileSize *7,gp.tileSize *10,"right");
        gp.roadObjects[mapNum][i].speed = 2;
        i++;
        gp.roadObjects[mapNum][i] = new Sheep_Animal(gp,gp.tileSize *27,gp.tileSize *10,"right");
        gp.roadObjects[mapNum][i].speed = 2;
        i++;

        //road 5
        gp.roadObjects[mapNum][i] = new WhiteHorse_Animal(gp,gp.tileSize *2,gp.tileSize *9-17,"left");
        i++;
        gp.roadObjects[mapNum][i] = new Chicken_Animal(gp,gp.tileSize *22,gp.tileSize *9,"left");
        i++;
        gp.roadObjects[mapNum][i] = new Sheep_Animal(gp,gp.tileSize *12,gp.tileSize *9,"left");
        i++;
    }
    public void waterObject() {
        int mapNum = 0;
        int i = 0;

        //line 2
        gp.waterObject[mapNum][i] = new TreeTrunk(gp,gp.tileSize,gp.tileSize*4,"left");
        i++;
        gp.waterObject[mapNum][i] = new TreeTrunk(gp,gp.tileSize*9,gp.tileSize*4,"left");
        i++;
        gp.waterObject[mapNum][i] = new TreeTrunk(gp,gp.tileSize*17,gp.tileSize*4,"left");
        i++;
        gp.waterObject[mapNum][i] = new TreeTrunk(gp,gp.tileSize*25,gp.tileSize*4,"left");
        i++;
        gp.waterObject[mapNum][i] = new Water(gp, gp.tileSize * 5, gp.tileSize * 4, "left");
        gp.waterObject[mapNum][i].speed = 3;
        i++;
        gp.waterObject[mapNum][i] = new Water(gp, gp.tileSize * 13, gp.tileSize * 4, "left");
        gp.waterObject[mapNum][i].speed = 3;
        i++;
        gp.waterObject[mapNum][i] = new Water(gp, gp.tileSize * 21, gp.tileSize * 4, "left");
        gp.waterObject[mapNum][i].speed = 3;
        i++;
        gp.waterObject[mapNum][i] = new Water(gp, gp.tileSize * 29, gp.tileSize * 4, "left");
        gp.waterObject[mapNum][i].speed = 3;
        i++;

        //line 4
        gp.waterObject[mapNum][i] = new TreeTrunk(gp,gp.tileSize*5,gp.tileSize*2,"left");
        i++;
        gp.waterObject[mapNum][i] = new TreeTrunk(gp,gp.tileSize*13,gp.tileSize*2,"left");
        i++;
        gp.waterObject[mapNum][i] = new TreeTrunk(gp,gp.tileSize*21,gp.tileSize*2,"left");
        i++;
        gp.waterObject[mapNum][i] = new TreeTrunk(gp,gp.tileSize*29,gp.tileSize*2,"left");
        i++;
        gp.waterObject[mapNum][i] = new Water(gp,gp.tileSize * 9,gp.tileSize*2,"left");
        gp.waterObject[mapNum][i].speed = 3;
        i++;
        gp.waterObject[mapNum][i] = new Water(gp,gp.tileSize * 17,gp.tileSize*2,"left");
        gp.waterObject[mapNum][i].speed = 3;
        i++;
        gp.waterObject[mapNum][i] = new Water(gp,gp.tileSize * 25,gp.tileSize*2,"left");
        gp.waterObject[mapNum][i].speed = 3;
        i++;
        gp.waterObject[mapNum][i] = new Water(gp,gp.tileSize,gp.tileSize*2,"left");
        gp.waterObject[mapNum][i].speed = 3;
        i++;

        //line 3
        gp.waterObject[mapNum][i] = new Turtle(gp,gp.tileSize*6,gp.tileSize*3,"right");
        i++;
        gp.waterObject[mapNum][i] = new Turtle(gp,gp.tileSize*17,gp.tileSize*3,"right");
        i++;

        Random random = new Random();
        int j = random.nextInt(100)+1;
        if(j<=50) {
            gp.waterObject[mapNum][i] = new Poison_Turtle(gp,gp.tileSize*17,gp.tileSize*3,"right");
            i++;
        }
        gp.waterObject[mapNum][i] = new Turtle(gp,gp.tileSize*30,gp.tileSize*3,"right");
        i++;
        gp.waterObject[mapNum][i] = new Water(gp,gp.tileSize * 12,gp.tileSize*3,"right");
        i++;
        gp.waterObject[mapNum][i] = new Water(gp,gp.tileSize * 13,gp.tileSize*3,"right");
        i++;
        gp.waterObject[mapNum][i] = new Water(gp, 0,gp.tileSize*3,"right");
        i++;
        gp.waterObject[mapNum][i] = new Water(gp, gp.tileSize*2,gp.tileSize*3,"right");
        i++;
        gp.waterObject[mapNum][i] = new Water(gp, gp.tileSize*23,gp.tileSize*3,"right");
        i++;
        gp.waterObject[mapNum][i] = new Water(gp, gp.tileSize*25,gp.tileSize*3,"right");
        i++;

        //line 1
        gp.waterObject[mapNum][i] = new Turtle(gp,gp.tileSize*2,gp.tileSize*5,"right");
        i++;
        gp.waterObject[mapNum][i] = new Turtle(gp,gp.tileSize*14,gp.tileSize*5,"right");
        i++;
        gp.waterObject[mapNum][i] = new Turtle(gp,gp.tileSize*25,gp.tileSize*5,"right");
        i++;
        gp.waterObject[mapNum][i] = new Water(gp, gp.tileSize*21,gp.tileSize*5,"right");
        i++;
        gp.waterObject[mapNum][i] = new Water(gp, gp.tileSize*20,gp.tileSize*5,"right");
        i++;
        gp.waterObject[mapNum][i] = new Water(gp, gp.tileSize*8,gp.tileSize*5,"right");
        i++;
        gp.waterObject[mapNum][i] = new Water(gp, gp.tileSize*10,gp.tileSize*5,"right");
        i++;
        gp.waterObject[mapNum][i] = new Water(gp, gp.tileSize*-4,gp.tileSize*5,"right");
        i++;
        gp.waterObject[mapNum][i] = new Water(gp, gp.tileSize*-2,gp.tileSize*5,"right");
        i++;
        }
    }
