/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.ui;

import me.archcst.agameproject.avatar.Player;
import me.archcst.agameproject.datacenter.DataCenter;
import me.archcst.agameproject.datacenter.Framer;
import me.archcst.agameproject.datacenter.PlayerController;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    private static GamePanel gamePanel;

    public static GamePanel getInstance(){
        if (gamePanel == null) {
            gamePanel = new GamePanel();
        }
        return gamePanel;
    }

    private GamePanel() {
        this.setSize(800, 600);
        this.setLocation(0,0);
        this.setVisible(true);
        this.setBackground(Color.BLACK);

        this.setFocusable(true);
        this.addKeyListener(new GameKeyListener());

        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        Framer framer = Framer.getInstance();
        DataCenter dataCenter = DataCenter.getInstance();
        while (true) {
            try {
                Thread.sleep(8);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            framer.nextFrame();
            dataCenter.gameProcess();
            this.repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        DataCenter.getInstance().drawFrame(g);
    }
}
