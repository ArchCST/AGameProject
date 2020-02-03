/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.util;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;

public class GameSettings {
    public static final Boolean DEV_MODE = true;
    public static final Boolean DEV_SHOW_MAPBLOCKS = true;
    public static final int GAME_WIDTH = 1400;
    public static final int GAME_HEIGHT = 900;

    public static final int CHANCE_OF_OBSTACLE_1 = 1000;
    public static final int CHANCE_OF_OBSTACLE_2 = 1000;
    public static final int CHANCE_OF_OBSTACLE_4 = 1000;

    private static GameSettings gameSettings = null;
    private GameSettings() {}

    public static GameSettings getInstance() {
        if (gameSettings == null) {
            gameSettings = new GameSettings();
        }

        return gameSettings;
    }
}
