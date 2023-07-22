package main;

import entity.Entity;
import object.OBJ_Heart;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class UI {

    GamePanel gp;
    public Graphics2D g2;

    Font pixel,purisa;
    BufferedImage heart_full,heart_blank,heart_half;

    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();

    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0; //0 : برای ثبت نام, 1: منو , 2 : انتخاب کاراکتر
    public int score = 0;
    public double playTime = 100; //تایم بازی
    public double getPlayTime = playTime;
    int subState = 0;
    DecimalFormat dFormat = new DecimalFormat("#0.0");

    public UI(GamePanel gp) {
        this.gp = gp;
        //نوع فونت / سبک فونت / اندازه فونت
        try {
            InputStream is = getClass().getResourceAsStream("/font/pixel.ttf");
            pixel = Font.createFont(Font.TRUETYPE_FONT,is);
            is = getClass().getResourceAsStream("/font/Purisa Bold.ttf");
            purisa = Font.createFont(Font.TRUETYPE_FONT,is);

        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //heart
        Entity heart = new OBJ_Heart(gp);
        heart_blank = heart.image;
        heart_full = heart.image2;
        heart_half = heart.image3;
    }
    public void addMessage (String text) {
        message.add(text);
        messageCounter.add(0);
    }
    public void draw(Graphics2D g2) {

        this.g2 = g2;
        g2.setFont(pixel);
        g2.setColor(Color.white);
        //منو
        if(gp.gameState == gp.titleState) {
            g2.drawImage(bufferedImage("/Player/screen1"),0,0,gp.screenWidth,gp.screenHeight,null); //background
            drawTitleScreen();
        }
        //بازی در حال اجرا
        if (gp.gameState == gp.playState) {
            gp.changeArea();
            drawPlayerLife();
            drawMonsterLife();
            drawTime();
            drawScore();
            drawMessage();
        }
        //بازی متوقف شده
        if (gp.gameState == gp.pauseState) {
            drawPlayerLife();
            drawMonsterLife();
            drawTime();
            drawScore();
            drawPauseScreen();
        }
        //موقع گفتن دیالوگ
        if (gp.gameState == gp.dialogueState) {
            drawPlayerLife();
            drawTime();
            drawScore();
            drawDialogueScreen();
        }
        //صفحه تنظیمات
        if(gp.gameState == gp.optionsState) {
            drawOptionsScreen();
            drawMonsterLife();
            drawPlayerLife();
            drawTime();
            drawScore();
        }
        // هنگام باخت
        if (gp.gameState == gp.gameOverState) {
            drawGameOverState();
            gp.config.saveConfig();
            gp.stopMusic();
        }
        //پایان بازی
        if(gp.gameState==gp.finishGameState) {
            drawFinishGameState();
            gp.config.saveConfig();
            gp.stopMusic();
        }
    }
    public void drawPlayerLife() {
        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;

        //طراحی جون پر
        while (i < gp.player.maxLife/2) {
            g2.drawImage(heart_blank,x,y,null);
            i++;
            x+= gp.tileSize;
        }
        //ریست
        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;

        //طراحی جون فعلی
         while (i < gp.player.life) {
             g2.drawImage(heart_half,x,y,null);
             i++;
             if(i < gp.player.life) {
                 g2.drawImage(heart_full,x,y,null);
             }
                i++;
             x+= gp.tileSize;
         }
    }
    public void drawMonsterLife() {

        for(int i=0; i<gp.monster[1].length; i++) {

            Entity monster = gp.monster[gp.currentMap][i];

            if(monster !=null) {
                if (monster.boss) {

                    double oneScale = (double) gp.tileSize*8/monster.maxLife;
                    double hpBarValue = oneScale*monster.life;

                    int x = gp.screenWidth/2 - gp.tileSize*4;
                    int y = gp.tileSize*15;

                    g2.setColor(new Color(35,35,35));
                    g2.fillRect(x-1,y-1,gp.tileSize*8 + 2,22);

                    g2.setColor(new Color(255,0,30));
                    g2.fillRect(x,y,(int) hpBarValue,20);

                    g2.setFont(g2.getFont().deriveFont(Font.BOLD,30f));
                    g2.setColor(Color.black);
                    g2.drawString(monster.name,x+4,y-10);
                }
            }
        }
    }
    public void drawMessage() {
        int messageX = gp.tileSize;
        int messageY = gp.tileSize *4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,32F));

        for(int i=0; i< message.size(); i++) {
            if(message.get(i) != null) {

                g2.setColor(Color.black);
                g2.drawString(message.get(i),messageX+2,messageY+2);
                g2.setColor(Color.white);
                g2.drawString(message.get(i),messageX,messageY);

                int counter = messageCounter.get(i) + 1; //message counter ++
                messageCounter.set(i,counter); // set the counter to the array
                messageY += 50;

                if(messageCounter.get(i) > 180) {
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }
    public void drawTitleScreen() { //طراحی منو

        if (titleScreenState==0) {

            String text;
            g2.setColor(new Color(0,0,0,120));
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 65F));
            g2.fillRoundRect(gp.tileSize*9,gp.tileSize*5,gp.tileSize*7+20,gp.tileSize*6,35,35);
            g2.setStroke(new BasicStroke(5));
            g2.setColor(Color.gray);
            g2.drawRoundRect(gp.tileSize*9+5,gp.tileSize*5+5,gp.tileSize*7+10,gp.tileSize*6-10,30,30);


            g2.setColor(Color.black);
            text = "Log in";
            int x = getXForCenteredText(text);
            int y = gp.tileSize * 7;
            g2.drawString(text,x,y);
            if(commandNum == 0) {
                g2.drawString(">",x-gp.tileSize,y);
            }

            text = "Sign up";
            x = getXForCenteredText(text);
            y += gp.tileSize * 3;
            g2.drawString(text,x,y);
            if(commandNum == 1) {
                g2.drawString(">",x-gp.tileSize,y);
            }
        }
        else if (titleScreenState==1) {

            //عنوان منو
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110F));
            String text = "Jumper Frog";
            int x = getXForCenteredText(text);
            int y = gp.tileSize * 3;

            //سایه
            g2.setColor(Color.gray);
            g2.drawString(text, x + 4, y + 4);
            //رنگ اصلی
            g2.setColor(Color.black);
            g2.drawString(text, x, y);

            //گزینه منو
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 45F));

            text = "NEW GAME";
            x = getXForCenteredText(text);
            y += gp.tileSize*5;
            g2.drawString(text,x,y);
            if(commandNum == 0) {
                g2.drawString(">",x-gp.tileSize,y);
            }
            text = "LOAD GAME";
            x = getXForCenteredText(text);
            y += gp.tileSize+5;
            g2.drawString(text,x,y);
            if(commandNum == 1) {
                g2.drawString(">",x-gp.tileSize,y);
            }
            text = "Select Character";
            x = getXForCenteredText(text);
            y += gp.tileSize+5;
            g2.drawString(text,x,y);
            if(commandNum == 2) {
                g2.drawString(">",x-gp.tileSize,y);
            }
            text = "QUIT";
            x = getXForCenteredText(text);
            y += gp.tileSize+5;
            g2.drawString(text,x,y);
            if(commandNum == 3) {
                g2.drawString(">",x-gp.tileSize,y);
            }
        }
        else if (titleScreenState == 2) {

            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
            String text = "Select Character";
            int x = getXForCenteredText(text);
            int y = gp.tileSize * 3;

            //سایه
            g2.setColor(Color.GRAY);
            g2.drawString(text, x + 4, y + 4);
            //رنگ اصلی
            g2.setColor(Color.black);
            g2.drawString(text, x, y);

            //گزینه منو
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));

            text = "Character 1";
            x = getXForCenteredText(text);
            y += gp.tileSize *3;
            g2.drawString(text,x,y);
            g2.drawImage(bufferedImage("/Player/frog_up1"),getXForCenteredText(text)+gp.tileSize *4,y-42,gp.tileSize+7,gp.tileSize+7,null);
            if(commandNum == 0) {
                g2.drawString(">",x-gp.tileSize,y);
            }
            text = "Character 2";
            x = getXForCenteredText(text);
            y += gp.tileSize +10;
            g2.drawString(text,x,y);
            g2.drawImage(bufferedImage("/Player1/frog_left1"),getXForCenteredText(text)+gp.tileSize *4,y-40,gp.tileSize,gp.tileSize,null);
            if(commandNum == 1) {
                g2.drawString(">",x-gp.tileSize,y);
            }
            text = "Character 3";
            x = getXForCenteredText(text);
            y += gp.tileSize +10;
            g2.drawString(text,x,y);
            g2.drawImage(bufferedImage("/Player3/turtle"),getXForCenteredText(text)+gp.tileSize *4,y-40,gp.tileSize,gp.tileSize,null);
            if(commandNum == 2) {
                g2.drawString(">",x-gp.tileSize,y);
            }
            text = "Character 4";
            x = getXForCenteredText(text);
            y += gp.tileSize +10;
            g2.drawString(text,x,y);
            g2.drawImage(bufferedImage("/Player2/panda1"),getXForCenteredText(text)+gp.tileSize *4,y-40,gp.tileSize,gp.tileSize,null);
            if(commandNum == 3) {
                g2.drawString(">",x-gp.tileSize,y);
            }
            text = "Back";
            x = getXForCenteredText(text);
            y += gp.tileSize *3;
            g2.drawString(text,x,y);
            if(commandNum == 4) {
                g2.drawString(">",x-gp.tileSize,y);
            }
        }
    }
    public void drawPauseScreen() { //طراحی متن متوقف کردن بازی

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
        g2.setColor(Color.black);
        String text = "PAUSE";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
    }
    public void drawDialogueScreen() {

        //صفحه دیالوگ
        int x = gp.tileSize * 13;
        int y = gp.tileSize * 9;
        int width = gp.screenWidth - (gp.tileSize * 18);
        int height = gp.tileSize * 6;
        drawSubWindow(x, y, width, height);

        //تنظیمات متن دیالوگ
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 15F)); //اندازه فونت دیالوگ
        x += gp.tileSize -30;
        y += gp.tileSize -20;

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 20;
        }
    }
    public void drawGameOverState() {

        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

        int x;
        int y;
        String text;

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,130f));
        text = "Game over";
        //سایه
        g2.setColor(Color.BLACK);
        x = getXForCenteredText(text);
        y = gp.tileSize *5;
        g2.drawString(text,x,y);
        //متن اصلی
        g2.setColor(Color.white);
        g2.drawString(text,x-4,y-4);

        //retry
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Retry";
        x = getXForCenteredText(text);
        y += gp.tileSize *8;
        g2.drawString(text,x,y);
         if(commandNum == 0) {
             g2.drawString(">",x-40,y);
         }
        //quit
        text = "Quit";
        x = getXForCenteredText(text);
        y +=60;
        g2.drawString(text,x,y);
        if(commandNum == 1) {
            g2.drawString(">",x-40,y);
        }
        //score
        text = "Score";
        g2.setFont(g2.getFont().deriveFont(80f));
        g2.setColor(Color.black);
        x = gp.tileSize *4;
        y = gp.tileSize *10;
        g2.drawString(text+":"+score,x,y); //سایه
        g2.setColor(Color.white);
        g2.drawString(text+":"+score,x-4,y-4);

        //Time
        text = "Time";
        g2.setFont(g2.getFont().deriveFont(80f));
        g2.setColor(Color.black);
        x = gp.tileSize *17;
        y = gp.tileSize *10;
        g2.drawString(text+":"+(int)(getPlayTime-playTime),x,y); //سایه
        g2.setColor(Color.white);
        g2.drawString(text+":"+(int)(getPlayTime-playTime),x-4,y-4);

        gp.config.saveConfig();
    }
    public void drawOptionsScreen() {

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        // sub window
        int frameX = gp.tileSize * 9;
        int frameY = gp.tileSize *3;
        int frameWidth = gp.tileSize *8;
        int frameHeight = gp.tileSize *10;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);

        switch (subState) {
            case 0: option_top(frameX,frameY);break;
            case 1: options_control(frameX,frameY);break;
            case 2: options_endGamesConfirmation(frameX,frameY);break;
        }
    }
    public void option_top(int frameX,int frameY) {

        int textX;
        int textY;

        //title
        String text = "Options";
        textX = getXForCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text,textX,textY);

        //Music
        textX = frameX + gp.tileSize;
        textY += gp.tileSize *3;
        g2.drawString("Music",textX,textY);
        if(commandNum==0) {
            g2.drawString(">",textX-25,textY);
        }
        //SE
        textY += gp.tileSize;
        g2.drawString("Sound Effect",textX,textY);
        if(commandNum==1) {
            g2.drawString(">",textX-25,textY);
        }
        //Control
        textY += gp.tileSize;
        g2.drawString("Control",textX,textY);
        if(commandNum==2) {
            g2.drawString(">",textX-25,textY);
            if(gp.keyH.enterPressed) {
                subState = 1;
                commandNum = 0;
                gp.keyH.enterPressed = false;
            }
        }
        //EndGame
        textY += gp.tileSize;
        g2.drawString("End Game",textX,textY);
        if(commandNum==3) {
            g2.drawString(">",textX-25,textY);
            if(gp.keyH.enterPressed) {
                subState = 2;
                commandNum = 0;
                gp.keyH.enterPressed = false;
            }
        }
        //back
        textY += gp.tileSize *2;
        g2.drawString("Back",textX,textY);
        if(commandNum==4) {
            g2.drawString(">",textX-25,textY);
            if(gp.keyH.enterPressed) {
                gp.gameState = gp.playState;
                commandNum = 0;
                gp.keyH.enterPressed = false;
            }
        }
        //music volume
        textX = frameX + (int) (gp.tileSize * 4.5);
        textY = frameY + gp.tileSize * 3 + 24;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX,textY,120,24);
        int volumeWidth = 24 * gp.music.volumeScale;
        g2.fillRect(textX,textY,volumeWidth,24);

        //SE
        textY += gp.tileSize;
        g2.drawRect(textX,textY,120,24);
        volumeWidth = 24 * gp.se.volumeScale;
        g2.fillRect(textX,textY,volumeWidth,24);


    }
    public void options_control(int frameX,int frameY) {
        int textX;
        int textY;

        //Title
        String text = "Control";
        textX = getXForCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text,textX,textY);

        textX = frameX + gp.tileSize;
        textY += gp.tileSize *2;

        g2.drawString("Move",textX,textY); textY +=gp.tileSize;
        g2.drawString("Confirm",textX,textY); textY +=gp.tileSize;
        g2.drawString("Shoot",textX,textY); textY +=gp.tileSize;
        g2.drawString("Pause",textX,textY); textY +=gp.tileSize;
        g2.drawString("Options",textX,textY); textY +=gp.tileSize;

        textX = frameX + gp.tileSize *6;
        textY = frameY + gp.tileSize *3;
        g2.drawImage(bufferedImage("/objects/move"),textX-15,textY-40,gp.tileSize *2,gp.tileSize,null);textY+=gp.tileSize;
        g2.drawString("ENTER",textX,textY); textY+=gp.tileSize;
        g2.drawString("F",textX,textY); textY+=gp.tileSize;
        g2.drawString("P",textX,textY); textY+=gp.tileSize;
        g2.drawString("ESC",textX,textY); textY+=gp.tileSize;

        //back
        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize *9;
        g2.drawString("Back",textX,textY);
        if(commandNum == 0) {
            g2.drawString(">",textX-25,textY);
            if(gp.keyH.enterPressed) {
                subState = 0;
                commandNum = 2;
                gp.keyH.enterPressed = false;
            }
        }
    }
    public void options_endGamesConfirmation(int frameX, int frameY) {
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize *3;

        currentDialogue = "Quit the game and\nreturn to the title screen?";
        for(String line: currentDialogue.split("\n")) {
            g2.drawString(line,textX,textY);
            textY += 40;
        }
        //yes
        String text = "Yes";
        textX = getXForCenteredText(text);
        textY += gp.tileSize *3;
        g2.drawString(text,textX,textY);
        if(commandNum==0) {
            g2.drawString(">",textX-25,textY);
            if(gp.keyH.enterPressed) {
                subState = 0;
                gp.gameState = gp.titleState;
                gp.stopMusic();
                gp.RetryAndRestart();
                gp.keyH.enterPressed = false;
            }
        }
        //no
        text = "No";
        textX = getXForCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text,textX,textY);
        if(commandNum==1) {
            g2.drawString(">",textX-25,textY);
            if(gp.keyH.enterPressed) {
                subState = 0;
                commandNum = 3;
                gp.keyH.enterPressed = false;
            }
        }
    }
    public void drawFinishGameState() {

        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

        int x;
        int y;
        String text;

        g2.setFont(g2.getFont().deriveFont(Font.BOLD,70f));
        text = "Congratulation  "+gp.loginForm.usernameField.getText()+"  You Won!";
        //سایه
        g2.setColor(Color.BLACK);
        x = getXForCenteredText(text);
        y = gp.tileSize *5;
        g2.drawString(text,x,y);
        //متن اصلی
        g2.setColor(Color.white);
        g2.drawString(text,x-4,y-4);

        //retry
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Play Again";
        x = getXForCenteredText(text);
        y += gp.tileSize *8;
        g2.drawString(text,x,y);
        if(commandNum == 0) {
            g2.drawString(">",x-40,y);
        }
        //quit
        text = "Quit";
        x = getXForCenteredText(text);
        y +=60;
        g2.drawString(text,x,y);
        if(commandNum == 1) {
            g2.drawString(">",x-40,y);
        }
        //score
        text = "Score";
        g2.setFont(g2.getFont().deriveFont(80f));
        g2.setColor(Color.black);
        x = gp.tileSize *4;
        y = gp.tileSize *10;
        g2.drawString(text+":"+score,x,y); //سایه
        g2.setColor(Color.white);
        g2.drawString(text+":"+score,x-4,y-4);

        //Time
        text = "Time";
        g2.setFont(g2.getFont().deriveFont(80f));
        g2.setColor(Color.black);
        x = gp.tileSize *17;
        y = gp.tileSize *10;
        g2.drawString(text+":"+(int)(getPlayTime-playTime),x,y); //سایه
        g2.setColor(Color.white);
        g2.drawString(text+":"+(int)(getPlayTime-playTime),x-4,y-4);

        gp.config.saveConfig();
    }
    public void drawSubWindow(int x, int y, int width, int height) {

        Color c = new Color(0, 0, 0, 215);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }
    public void drawTime () {

        if(playTime >= 100) {
            //پس زمینه تایمر
            g2.setColor(Color.black);
            g2.fillRoundRect(gp.tileSize *22-12, 5, gp.tileSize *3+50, gp.tileSize +7, 25, 25);

            //کادر دور تامیر
            g2.setColor(Color.gray);
            g2.setStroke(new BasicStroke(4));
            g2.drawRoundRect(gp.tileSize *22-7, 10, gp.tileSize *3+40, gp.tileSize -3, 15, 15);
        }
        else if(playTime < 100) {
            //پس زمینه تایمر
            g2.setColor(Color.black);
            g2.fillRoundRect(gp.tileSize *22-12, 5, gp.tileSize *3+35, gp.tileSize +7, 25, 25);

            //کادر دور تامیر
            g2.setColor(Color.gray);
            g2.setStroke(new BasicStroke(4));
            g2.drawRoundRect(gp.tileSize *22-7, 10, gp.tileSize *3+25, gp.tileSize -3, 15, 15);
        }
        //تایمر
        g2.setFont(pixel);
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));

        if(gp.gameState==gp.dialogueState || gp.gameState==gp.pauseState || gp.gameState==gp.optionsState) {
            g2.drawString("Time : "+dFormat.format(playTime),gp.tileSize *22, gp.tileSize);

        } else {
            playTime -= (double) 1 / 60;
              g2.drawString("Time : " + dFormat.format(playTime), gp.tileSize * 22, gp.tileSize);

            if(playTime<0) {
                gp.playSE(12);
               gp.gameState = gp.gameOverState;
            }
        }
    }
    public void drawScore () {

        getScore(score);
        g2.setFont(pixel);
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));

        g2.drawString("Score : " + score, gp.tileSize * 17, gp.tileSize);
    }
    public void getScore (int score) {

        if(score==0) {
            //پس زمینه
            g2.setColor(Color.black);
            g2.fillRoundRect(gp.tileSize *17-12, 5, gp.tileSize *3+15, gp.tileSize +7, 25, 25);

            //کادر دور
            g2.setColor(Color.gray);
            g2.setStroke(new BasicStroke(4));
            g2.drawRoundRect(gp.tileSize *17-7, 10, gp.tileSize *3+5, gp.tileSize -3, 15, 15);
        }
        if(score > 0 && score<100) {
            //پس زمینه
            g2.setColor(Color.black);
            g2.fillRoundRect(gp.tileSize *17-12, 5, gp.tileSize *3+35, gp.tileSize +7, 25, 25);

            //کادر دور
            g2.setColor(Color.gray);
            g2.setStroke(new BasicStroke(4));
            g2.drawRoundRect(gp.tileSize *17-7, 10, gp.tileSize *3+25, gp.tileSize -3, 15, 15);
        }
        if(score>=100 && score<1000) {
            //پس زمینه
            g2.setColor(Color.black);
            g2.fillRoundRect(gp.tileSize *17-12, 5, gp.tileSize *4, gp.tileSize +7, 25, 25);

            //کادر دور
            g2.setColor(Color.gray);
            g2.setStroke(new BasicStroke(4));
            g2.drawRoundRect(gp.tileSize *17-7, 10, gp.tileSize *3+38, gp.tileSize -3, 15, 15);
        }
        if(score>=1000) {
            //پس زمینه
            g2.setColor(Color.black);
            g2.fillRoundRect(gp.tileSize *17-12, 5, gp.tileSize *4+15, gp.tileSize +7, 25, 25);

            //کادر دور
            g2.setColor(Color.gray);
            g2.setStroke(new BasicStroke(4));
            g2.drawRoundRect(gp.tileSize *17-7, 10, gp.tileSize *4+5, gp.tileSize -3, 15, 15);
        }
    }
    public BufferedImage bufferedImage (String Path) {
        BufferedImage Image;
        try {
            Image = read(Objects.requireNonNull(getClass().getResourceAsStream(Path + ".png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Image;
    }
    public int getXForCenteredText(String text) { //گرفتن x برای گذاشتن متن در وسط صفحه
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }
}
