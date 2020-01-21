/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.ui;

import me.archcst.agameproject.avatar.ASCIIPlayer;
import me.archcst.agameproject.datacenter.Framer;

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
        this.setSize(600, 800);
        this.setLocation(0,0);
        this.setVisible(true);
        this.setBackground(Color.BLACK);

        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        Framer framer = Framer.getInstance();
        while (true) {
            try {
                Thread.sleep(40);
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

        ASCIIPlayer player = ASCIIPlayer.getInstance();
        player.moves.get("walk").draw(g);
    }
}
