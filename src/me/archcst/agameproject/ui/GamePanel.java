/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.ui;

import me.archcst.agameproject.datacenter.DataCenter;
import me.archcst.agameproject.datacenter.Framer;
import me.archcst.agameproject.util.GameSettings;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    private static GamePanel gamePanel;
    private static Framer framer = Framer.getInstance();

    public static GamePanel getInstance(){
        if (gamePanel == null) {
            gamePanel = new GamePanel();
        }
        return gamePanel;
    }

    private GamePanel() {
        this.setSize(GameSettings.GAME_WIDTH, GameSettings.GAME_HEIGHT);
        this.setLocation(0,0);
        this.setVisible(true);
        this.setBackground(Color.BLACK);

//        this.setFocusable(true);
//        this.addKeyListener(new GameKeyListener());

        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(GameSettings.GAME_SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            framer.nextFrame();
            this.repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        DataCenter dataCenter = DataCenter.getInstance();
        dataCenter.drawFrame(g);
        dataCenter.gameProcess(g);

        if (GameSettings.DEV_MODE && GameSettings.DEV_SHOW_PANEL_CENTRAL_POINT) {
            g.setColor(Color.CYAN);
//            g.drawString("╋", getWidth() / 2 - 8, getHeight() / 2 + 8);
            g.fillRect(getWidth()/2 - 2, getHeight() /2 -2 , 6, 6);
        }


    }
}
