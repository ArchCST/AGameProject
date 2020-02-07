/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.util;

import java.awt.*;
import java.util.Random;

public class GameSettings {
    public static final boolean DEV_MODE = true; // 开发模式开关

    public static Random r = new Random(3);
    public static final long GAME_SLEEP_TIME = 8; // 8

    public static final int MAP_MIN_WIDTH = 10;
    public static final int MAP_MAX_WIDTH = 27;
    public static final int MAP_MIN_HEIGHT = 10;
    public static final int MAP_MAX_HEIGHT = 21;

    public static final boolean DEV_SHOW_MAP_BLOCKS = false; // 画出所有地图块矩形
    public static final boolean DEV_SHOW_AVATAR_COLLISION_BOX = true; // 画出所有角色的碰撞箱
    public static final boolean DEV_SHOW_AVATAR_BOX = true; // 画出所有角色的碰撞箱
    public static final boolean DEV_SHOW_MAP_COLLISION_BOX = true; // 画出地图的碰撞箱
    public static final boolean DEV_SHOW_CONFLICT_COLLISION_BOX = true; // 画出发生碰撞的碰撞箱
    public static final boolean DEV_SHOW_TEMP_COLLISION_BOX = true; // 画出发生位移前用来预判的碰撞箱
    public static final boolean DEV_SHOW_PANEL_CENTRAL_POINT = true; // 画出Panel的中心点

    public static final int BLOCK_SIZE = 42; // 地图块的大小
    public static final int GAME_WIDTH = 1600; // 游戏界面的宽度
    public static final int GAME_HEIGHT = 900; // 游戏界面的高度

    public static final int BEACON_DISPLACEMENT = 50; // 信标的位移距离
    public static final int CAMERA_SPEED = 3; // 摄像机的速度

    public static final double OBSTACLE_RATE = 0.1;

    public static final int AVATAR_REFRESH_RATE = 16; // 角色动画刷新频率
    public static final double MONSTER_IDLE_CHANCE = 0.3; // 怪物停止移动的几率
    public static final double MONSTER_ATTACK_CHANCE = 0.5; // 怪物攻击机率

    public static final long MONSTER_MIN_MOVE_TIME = 500;
    public static final long MONSTER_MAX_MOVE_TIME = 1000;

    // ASCII 画风设定
    public static final int FONT_SIZE = 14;
//    public static final Font FONT = new Font("Dialog", Font.PLAIN, FONT_SIZE);
    public static final Color MAP_COLOR = Color.darkGray;
    public static final Color BACKGROUND_COLOR = Color.black;
    public static final Color PLAYER_NORMAL_COLOR = Color.WHITE;

//    static {
//        try {
//            FONT = Font.createFont(Font.TRUETYPE_FONT, new File("src/me/archcst/agameproject/static/FONT.ttc"));
//            FONT = FONT.deriveFont(Font.PLAIN, FONT_SIZE);
//            System.out.println("success");
//        } catch (FontFormatException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    private static GameSettings gameSettings = null;
//    private GameSettings() {}
//
//    public static GameSettings getInstance() {
//        if (gameSettings == null) {
//            gameSettings = new GameSettings();
//        }
//
//        return gameSettings;
//    }
}
