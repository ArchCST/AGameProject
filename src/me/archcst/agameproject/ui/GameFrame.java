/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.ui;

import me.archcst.agameproject.util.GameSettings;

import javax.swing.*;
import java.awt.*;

public class GameFrame {
    public static JFrame jFrame = null;

    public void mainFrame() {
        jFrame = new JFrame();
        jFrame.setSize(GameSettings.GAME_WIDTH,GameSettings.GAME_HEIGHT);
        jFrame.setTitle("A Game Project");
        jFrame.setLocationRelativeTo(null); // 全屏居中
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(null);
        jFrame.setVisible(true);

        jFrame.setFocusable(true);
        jFrame.addKeyListener(new GameKeyListener());
//        jFrame.add(GamePanel.getInstance());

        jFrame.add(GameStartPanel.getInstance());
    }
}
