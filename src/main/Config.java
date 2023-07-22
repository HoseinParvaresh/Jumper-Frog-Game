package main;

import java.io.*;

public class Config {

    GamePanel gp;
    boolean one = false;

    public Config(GamePanel gp) {
        this.gp = gp;
    }

    public void saveConfig() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));

            //music volume
            bw.write(String.valueOf(gp.music.volumeScale));
            bw.newLine();

            //SE volume
            bw.write(String.valueOf(gp.se.volumeScale));
            bw.newLine();

            bw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (gp.gameState==gp.gameOverState && !one || gp.gameState==gp.finishGameState && !one) {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("player.txt",true));

                bw.write("Username: "+gp.loginForm.usernameField.getText()+"\n");
                bw.write("Score: "+gp.ui.score+"\n");
                bw.write("Time: "+(int)(gp.ui.getPlayTime-gp.ui.playTime)+"\n");
                bw.write("----------------------------------\n");
                bw.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            one = true;
        }
        if(gp.gameState==gp.playState || gp.gameState==gp.titleState) {
            one = false;
        }
    }
    public void loadConfig() {

        try {
            BufferedReader br = new BufferedReader(new FileReader("config.txt"));

            String s = br.readLine();

            //music volume
            gp.music.volumeScale = Integer.parseInt(s);
            s = br.readLine();

            //se volume
            gp.se.volumeScale = Integer.parseInt(s);

            br.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        }
    }
