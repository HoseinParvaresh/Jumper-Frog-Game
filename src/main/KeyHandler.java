package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;

    public boolean downPressed,leftPressed,rightPressed,upPressed, enterPressed,shotKeyPressed,escKey;
    public boolean cheatKey1,cheatKey2,cheatKey3,cheatKey4,cheatKey5,cheatOn,cheatOff;

    boolean showDebugText = false;
    boolean pause = false;

    public KeyHandler(GamePanel gp) {this.gp = gp;}
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        //بازی در منو
        if (gp.gameState == gp.titleState) {titleState(code);}
        //بازی در حال اجرا
        else if (gp.gameState == gp.playState) {playState(code);}
        //بازی متوقف شده
        else if (gp.gameState == gp.pauseState) {pauseState(code);}
        // بازی در دیالوگ
        else if (gp.gameState == gp.dialogueState) {dialogueState(code);}
        //بازی در تنظیمات
        else if (gp.gameState == gp.optionsState) {optionsState(code);}
        //پایان بازی
        else if (gp.gameState == gp.gameOverState) {gameOverState(code);}
        //بازی تمام شده
        else if (gp.gameState == gp.finishGameState) {finishGameState(code);}
        //fireball
        if(code == KeyEvent.VK_F) {shotKeyPressed = true;}
        //debug
        if (code == KeyEvent.VK_D) {
            if (!showDebugText) {showDebugText = true;}
            else if (showDebugText) {showDebugText = false;}
        }
        //reload map
        if (code == KeyEvent.VK_R) {
            gp.player.worldX = gp.tileSize *12;
            gp.player.worldY = gp.tileSize *15;
        }
        //cheat code
        if(code == KeyEvent.VK_C) {cheatKey1 = true;}
        if(code == KeyEvent.VK_H) {cheatKey2 = true;}
        if (code == KeyEvent.VK_E) {cheatKey3 = true;}
        if (code == KeyEvent.VK_A) {cheatKey4 = true;}
        if (code == KeyEvent.VK_T) {cheatKey5 = true;}
        if(code == KeyEvent.VK_0) {cheatOff = true;}
        if(cheatKey1 && cheatKey2 && cheatKey3 && cheatKey4 && cheatKey5) {
              cheatOn = true;
              cheatKey1 = false;
              cheatKey2 = false;
              cheatKey3 = false;
              cheatKey4 = false;
              cheatKey5 = false;
              cheatOff = false;
        }
    }
    public void titleState(int code) {

        if (gp.ui.titleScreenState == 0) { //loginForm

            if (code == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
                gp.playSE(7);
                if (gp.ui.commandNum < 0) {gp.ui.commandNum = 1;}
            }
            if (code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
                gp.playSE(7);
                if (gp.ui.commandNum > 1) {gp.ui.commandNum = 0;}
            }
            if (code == KeyEvent.VK_ENTER) {
                gp.playSE(8);
                //login
                if (gp.ui.commandNum == 0) {gp.loginForm.logIn();}
                //signup
                if (gp.ui.commandNum == 1) {gp.loginForm.signUp();}
            }
        } else if (gp.ui.titleScreenState == 1) { //menu

            if (code == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
                gp.playSE(7);
                if (gp.ui.commandNum < 0) {gp.ui.commandNum = 3;}
            }
            if (code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
                gp.playSE(7);
                if (gp.ui.commandNum > 3) {gp.ui.commandNum = 0;}
            }
            if (code == KeyEvent.VK_ENTER) {
                gp.playSE(8);

                if (gp.ui.commandNum == 0) {
                    //new game
                    gp.gameState = gp.playState;
                    gp.playMusic(13);
                }
                if (gp.ui.commandNum == 1) {
                    //load game
                }
                if (gp.ui.commandNum == 2) {gp.ui.titleScreenState = 2;} //select Character
                if (gp.ui.commandNum == 3) {System.exit(0);} //exit
            }
        } else if (gp.ui.titleScreenState == 2) { //Select Character
            if (code == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
                gp.playSE(7);
                if (gp.ui.commandNum < 0) {gp.ui.commandNum = 4;}
            }
            if (code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
                gp.playSE(7);
                if (gp.ui.commandNum > 4) {gp.ui.commandNum = 0;}
            }
            if (code == KeyEvent.VK_ENTER) {
                gp.playSE(8);

                if (gp.ui.commandNum == 0) {
                    //char1
                    gp.player.getPlayerImage();
                    gp.ui.titleScreenState = 1;
                    gp.ui.commandNum = 0;
                }
                if (gp.ui.commandNum == 1) {
                    //char2
                    gp.player.getPlayerImage1();
                    gp.ui.titleScreenState = 1;
                    gp.ui.commandNum = 0;
                }
                if (gp.ui.commandNum == 2) {
                    //char 3
                    gp.player.getPlayerImage3();
                    gp.ui.titleScreenState = 1;
                    gp.ui.commandNum = 0;
                }
                if (gp.ui.commandNum == 3) {
                    //char 4
                    gp.player.getPlayerImage2();
                    gp.ui.titleScreenState = 1;
                    gp.ui.commandNum = 0;
                }
                if (gp.ui.commandNum == 4) {
                    //back
                    gp.ui.titleScreenState = 1;
                    gp.ui.commandNum = 0;
                }
            }
        }
    }
    public void playState(int code) {
        if (code == KeyEvent.VK_UP) {upPressed = true;}
        if (code == KeyEvent.VK_DOWN) {downPressed = true;}
        if (code == KeyEvent.VK_LEFT) {leftPressed = true;}
        if (code == KeyEvent.VK_RIGHT) {rightPressed = true;}
        if (code == KeyEvent.VK_P) {gp.gameState = gp.pauseState;}
        if (code == KeyEvent.VK_ESCAPE) {gp.gameState = gp.optionsState;}
    }
    public void pauseState(int code) {
        //pause game
        if (code == KeyEvent.VK_P) {
            if (!pause) {pause = true;}
            if (pause) {
                pause = false;
                gp.gameState = gp.playState;
                }
            }
        }
    public void dialogueState(int code) {
        if (code == KeyEvent.VK_ENTER) {gp.gameState = gp.playState;}
    }
    public void optionsState(int code) {
        if(code == KeyEvent.VK_ESCAPE) {gp.gameState = gp.playState;}
        if(code == KeyEvent.VK_ENTER) {enterPressed = true;}
        int maxCommandNum = 0;
        switch (gp.ui.subState) {
            case 0 : maxCommandNum = 4; break;
            case 2 : maxCommandNum = 1; break;
        }
        if(code == KeyEvent.VK_UP) {
            gp.ui.commandNum--;
            gp.playSE(7);
            if(gp.ui.commandNum < 0) {gp.ui.commandNum = maxCommandNum;}
        }
        if(code == KeyEvent.VK_DOWN) {
            gp.ui.commandNum++;
            gp.playSE(7);
            if(gp.ui.commandNum > maxCommandNum) {gp.ui.commandNum = 0;}
        }
        if(code == KeyEvent.VK_LEFT) {
            if(gp.ui.subState==0) {
                //music
                if(gp.ui.commandNum == 0 && gp.music.volumeScale > 0) {
                    gp.music.volumeScale--;
                    gp.music.checkVolume();
                    gp.playSE(7);
                }
                //SE
                if(gp.ui.commandNum == 1 && gp.se.volumeScale > 0) {
                    gp.se.volumeScale--;
                    gp.playSE(7);
                }
            }
        }
        if(code == KeyEvent.VK_RIGHT) {
            if(gp.ui.subState==0) {
                //music
                if(gp.ui.commandNum == 0 && gp.music.volumeScale < 5) {
                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                    gp.playSE(7);
                }
                //SE
                if(gp.ui.commandNum == 1 && gp.se.volumeScale < 5) {
                    gp.se.volumeScale++;
                    gp.playSE(7);
                }
            }
        }
    }
    public void gameOverState(int code) {

        if(code == KeyEvent.VK_UP) {
            gp.ui.commandNum--;
            if(gp.ui.commandNum < 0) {gp.ui.commandNum = 1;}
            gp.playSE(7);
        }
        if(code == KeyEvent.VK_DOWN) {
            gp.ui.commandNum++;
            if(gp.ui.commandNum > 1) {gp.ui.commandNum = 0;}
            gp.playSE(7);
        }
        if(code == KeyEvent.VK_ENTER) {
            gp.playSE(8);
            //retry
            if(gp.ui.commandNum==0) {
                gp.gameState = gp.playState;
                gp.RetryAndRestart();
                gp.playMusic(13);
            }
            else if(gp.ui.commandNum==1) {
                //quit
                gp.gameState = gp.titleState;
                gp.RetryAndRestart();
            }
        }
    }
    public void finishGameState(int code){
        if(code == KeyEvent.VK_UP) {
            gp.ui.commandNum--;
            if(gp.ui.commandNum < 0) {gp.ui.commandNum = 1;}
            gp.playSE(7);
        }
        if(code == KeyEvent.VK_DOWN) {
            gp.ui.commandNum++;
            if(gp.ui.commandNum > 1) {gp.ui.commandNum = 0;}
            gp.playSE(7);
        }
        if(code == KeyEvent.VK_ENTER) {
            gp.playSE(8);
            //play again
            if(gp.ui.commandNum==0) {
                gp.gameState = gp.playState;
                gp.RetryAndRestart();
                gp.playMusic(13);
            }
            else if(gp.ui.commandNum==1) {
                //quit
                gp.gameState = gp.titleState;
                gp.RetryAndRestart();
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP) {upPressed = false;}
        if (code == KeyEvent.VK_DOWN) {downPressed = false;}
        if (code == KeyEvent.VK_LEFT) {leftPressed = false;}
        if (code == KeyEvent.VK_RIGHT) {rightPressed = false;}
        if (code == KeyEvent.VK_F) {shotKeyPressed = false;}
    }
    @Override
    public void keyTyped(KeyEvent e) {}
}

