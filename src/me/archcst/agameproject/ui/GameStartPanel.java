/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.ui;

import me.archcst.agameproject.util.GameSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameStartPanel extends JPanel implements Runnable {
    private static GameStartPanel gamePanel;

    public static GameStartPanel getInstance() {
        if (gamePanel == null) {
            gamePanel = new GameStartPanel();
        }
        return gamePanel;
    }

    private GameStartPanel() {
        this.setSize(GameSettings.GAME_WIDTH, GameSettings.GAME_HEIGHT);
        this.setLocation(0, 0);
        this.setVisible(true);
        this.setBackground(Color.BLACK);

        Thread thread = new Thread(this);
        thread.start();

    }

    @Override
    public void run() {

        while (frame < 80) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            frame++;
            this.repaint();
        }

        gameStart();
    }

    int frame;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Monaco", Font.PLAIN, 80));

        g.drawString("AGameProject", 200, 300);
        g.setFont(new Font("Monaco", Font.PLAIN, 24));

        if (frame % 4 == 0) {
//            g.drawString("by ArchCST, Press [ANY_KEY]", 446, 360);
            g.drawString("by ArchCST, Loading", 546, 360);
        } else if (frame % 4 == 1){
//            g.drawString("by ArchCST, Press", 446, 360);
            g.drawString("by ArchCST, Loading.", 546, 360);
        } else if (frame % 4 == 2){
            g.drawString("by ArchCST, Loading..", 546, 360);
        } else if (frame % 4 == 3){
            g.drawString("by ArchCST, Loading...", 546, 360);
        }
//        g.drawString("to start", 838, 360);
    }

    private void gameStart() {
        // 加载游戏界面
        this.setFocusable(false);
        GameFrame.jFrame.remove(this);
        GameFrame.jFrame.add(GamePanel.getInstance());
        GameFrame.jFrame.repaint();
    }
}
