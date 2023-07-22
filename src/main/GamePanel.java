package main;

import entity.Entity;
import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable {

    final int originaltitlesize = 16;
    final int scale = 3;

    public final int tileSize = originaltitlesize * scale; //48*48
    public final int maxScreenCol = 26;
    public final int maxScreenRow = 18;
    public final int screenWidth = tileSize * maxScreenCol; //1248 pixel
    public final int screenHeight = tileSize * maxScreenRow; //864 pixel

    public final int maxMap = 10;

    // 0 : map level1 // 1 : map level2 // 2 : map before start fight // 3 : map when start fight
    public int currentMap = 0;

    //fps
    int FPS = 60;

    //system
    public KeyHandler keyH = new KeyHandler(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AsserSetter aSetter = new AsserSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    TileManager tileM = new TileManager(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public Config config = new Config(this);
    LoginForm loginForm = new LoginForm(this);
    Thread gameThread;

    //objects
    public Player player = new Player(this, keyH);
    public Entity[][] obj = new Entity[maxMap][100];
    public Entity[][] npc = new Entity[maxMap][5];
    public Entity[][] monster = new Entity[maxMap][15];
    public Entity[][] roadObjects = new Entity[maxMap][50];
    public Entity[][] waterObject = new Entity[maxMap][50];
    public ArrayList <Entity> projectileList = new ArrayList<>();
    ArrayList<Entity> entityList = new ArrayList<>();

    //gameState
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int optionsState = 4;
    public final int gameOverState = 5;
    public final int finishGameState = 6;

    //area
    public int currentArea;
    public int nextArea = 1;
    public final int level1 = 1;
    public final int level2 = 2;
    public final int beforeFight = 3;
    public final int fight = 4;



    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void setupGame() {
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.roadObjects();
        aSetter.waterObject();
        gameState = titleState;
        currentArea = level1;
    }
    public void RetryAndRestart () {
        player.setDefaultValues();
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.roadObjects();
        aSetter.waterObject();
        ui.playTime = ui.getPlayTime;
        ui.score = 0;
        currentMap = 0;
        player.worldX = tileSize * 12;
        player.worldY = tileSize * 15;
    }
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() { //game loop

        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {

            update();
            repaint();
            try {
                double remainningTime = nextDrawTime - System.nanoTime();
                remainningTime = remainningTime / 1000000;

                if (remainningTime < 0) {
                    remainningTime = 0;
                }
                Thread.sleep((long) remainningTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void update() {

        if (gameState == playState) {
            //player
            player.update();

            //npc
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    npc[currentMap][i].update();
                }
            }
            //roadObject
            for (int i = 0; i < roadObjects[1].length; i++) {
                if (roadObjects[currentMap][i] != null) {
                    roadObjects[currentMap][i].update();
                }
            }
            //waterObject
            for (int i = 0; i < waterObject[1].length; i++) {
                if (waterObject[currentMap][i] != null) {
                    waterObject[currentMap][i].update();
                }
            }
            //monster
            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    if(monster[currentMap][i].alive && !monster[currentMap][i].dying) {
                        monster[currentMap][i].update();
                    }
                    if(!monster[currentMap][i].alive) {
                        monster[currentMap][i].checkDrop();
                        monster[currentMap][i] = null;
                    }
                }
            }
            //projectile
            for (int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i) != null) {
                    if(projectileList.get(i).alive) {
                        projectileList.get(i).update();
                    }
                    if(!projectileList.get(i).alive) {
                        projectileList.remove(i);
                    }
                }
            }
        }
        if (gameState == pauseState) {} //nothing
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (gameState == titleState) {
            ui.draw(g2);
        } else {
            //tile
            tileM.draw(g2);

            //add Entity to arraylist :

            //npc
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    entityList.add(npc[currentMap][i]);
                }
            }
            //obj
            for (int i = 0; i < obj[1].length; i++) {
                if (obj[currentMap][i] != null) {
                    entityList.add(obj[currentMap][i]);
                }
            }
            //roadObject
            for (int i = 0; i < roadObjects[1].length; i++) {
                if (roadObjects[currentMap][i] != null) {
                    entityList.add(roadObjects[currentMap][i]);
                }
            }
            //waterObject
            for (int i = 0; i < waterObject[1].length; i++) {
                if (waterObject[currentMap][i] != null) {
                    entityList.add(waterObject[currentMap][i]);
                }
            }
            //monster
            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    entityList.add(monster[currentMap][i]);
                }
            }
            //projectile
            for (int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i) != null) {
                    entityList.add(projectileList.get(i));
                }
            }
            //player
            entityList.add(player);

            //sort
            entityList.sort(new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }
            });

            //draw Entity
            for (int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }
            //clear Entity
            entityList.clear();

            //ui
            ui.draw(g2);
        }
        //debug
        if(keyH.showDebugText) {
            g2.setColor(Color.white);
            g2.setFont(new Font("Arial",Font.PLAIN,20));
            int x = 10;
            int y = 400;
            int lineHeight = 20;

            g2.drawString("WorldX" + player.worldX,x,y); y+=lineHeight;
            g2.drawString("WorldY" + player.worldY,x,y); y+=lineHeight;
            g2.drawString("Col" + (player.worldX + player.solidArea.x)/ tileSize,x,y); y+=lineHeight;
            g2.drawString("Row" + (player.worldY + player.solidArea.y)/ tileSize,x,y);
        }
        g2.dispose();
    }
    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic() {
        music.stop();
    }
    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }
    public void changeArea() {

        if(nextArea != currentArea) {
            stopMusic();

            if(nextArea==level1) {
                playMusic(13);
            }
            if(nextArea==level2) {
                playMusic(14);
            }
            if(nextArea==fight) {
                playMusic(15);
            }
        }
        currentArea = nextArea;
    }
}