/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.ui;

import me.archcst.agameproject.util.GameSettings;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

public class GameFrame {
    public static JFrame jFrame = null;

    public void mainFrame() {
//        Font font = null;
//        try {
//            font = Font.createFont(Font.TRUETYPE_FONT, new File("src/me/archcst/agameproject/util/wqy-microhei.ttc"));
//            font = font.deriveFont(Font.PLAIN,13f);
//        } catch (FontFormatException | IOException e) {
//            e.printStackTrace();
//        }
//        initGlobalFont(font);
        initGlobalFont(new Font("dialog", Font.PLAIN, 13));

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

    // 设置全局字体
    private static void initGlobalFont(Font font) {
        FontUIResource fontRes = new FontUIResource(font);
        for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements();) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontRes);
            }
        }
    }
}
