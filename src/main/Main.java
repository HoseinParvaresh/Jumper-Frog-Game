package main;

import javax.swing.*;

public class Main {
        public static JFrame window;

    public static void main(String[] args) {

        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false); //جلوگیری از تغییر اندازه صفحه
      //  window.setUndecorated(true);
        window.setTitle("Jumper Frog");
        new Main().setIcon();

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        gamePanel.config.loadConfig();

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
    public void setIcon() {
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("Player/iconfrog.png"));
        window.setIconImage(icon.getImage());
    }
}