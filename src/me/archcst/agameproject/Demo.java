/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject;

import me.archcst.agameproject.ui.GameFrame;
import me.archcst.agameproject.util.GameLoader;

public class Demo {
    public static void main(String[] args) {
        GameLoader gameLoader = new GameLoader();
        gameLoader.initGame();

        GameFrame gameFrame = new GameFrame();
        gameFrame.mainFrame();
    }
}
