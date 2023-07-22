package main;

import javax.sound.sampled.*;
import java.net.URL;

public class Sound {
    Clip clip;
    URL [] soundURL = new URL[30];
    FloatControl fc;
    int volumeScale = 3;
    float volume;

    public Sound() {
        soundURL[13] = getClass().getResource("/sound/back1.wav"); //background
        soundURL[0] = getClass().getResource("/sound/2.wav"); // background
        soundURL[1] = getClass().getResource("/sound/speed.wav"); // speed
        soundURL[3] = getClass().getResource("/sound/pop.wav"); //time
        soundURL[4] = getClass().getResource("/sound/armor.wav"); //armor
        soundURL[5] = getClass().getResource("/sound/Sound.wav"); //leaf
        soundURL[6] = getClass().getResource("/sound/zss_heart.wav"); //extra Heart
        soundURL[7] = getClass().getResource("/sound/cursor.wav"); // up and down in menu and options
        soundURL[8] = getClass().getResource("/sound/chipwall.wav"); //choice selections
        soundURL[9] = getClass().getResource("/sound/heart.wav"); //Heart
        soundURL[10] = getClass().getResource("/sound/star.wav"); //star
        soundURL[11] = getClass().getResource("/sound/fly.wav"); //fly
        soundURL[12] = getClass().getResource("/sound/gameover.wav"); //GameOver
        soundURL[14] = getClass().getResource("/sound/level2.wav"); //level2
        soundURL[15] = getClass().getResource("/sound/fight.wav"); //fight
        soundURL[16] = getClass().getResource("/sound/win1.wav"); //win
    }
    public void setFile (int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();

        } catch (Exception e) {}
    }
    public void play () {clip.start();}
    public void loop () {clip.loop(Clip.LOOP_CONTINUOUSLY);}
    public void stop () {clip.stop();}
    public void checkVolume() {

        switch (volumeScale) {
            case 0 -> volume = -80f;
            case 1 -> volume = -20f;
            case 2 -> volume = -12f;
            case 3 -> volume = -5f;
            case 4 -> volume = 1f;
            case 5 -> volume = 6f;
        }
        fc.setValue(volume);
    }
}
