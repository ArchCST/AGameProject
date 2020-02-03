/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject;

import me.archcst.agameproject.avatar.Player;
import me.archcst.agameproject.map.GameMap;
import me.archcst.agameproject.ui.GameFrame;

public class Demo {
    public static void main(String[] args) {
//        GameMap gameMap = GameMap.getInstance();
//        Player player = Player.getInstance();

//        player.loadPlayer();
//        gameMap.newMap();

        GameFrame gameFrame = new GameFrame();
        gameFrame.mainFrame();
    }
}
