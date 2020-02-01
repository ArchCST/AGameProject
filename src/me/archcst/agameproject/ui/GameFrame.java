/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.ui;

import javax.swing.*;
import java.awt.*;

public class GameFrame {
    public void mainFrame() {
        JFrame jFrame = new JFrame();
        jFrame.setSize(800,600);
        jFrame.setTitle("0x133");
        jFrame.setLocationRelativeTo(null); // 全屏居中
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(null);
        jFrame.setVisible(true);

        jFrame.add(GamePanel.getInstance());
    }
}
