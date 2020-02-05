/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.util;

public class GameSettings {
    public static final boolean DEV_MODE = true; // 开发模式开关
    public static final boolean DEV_SHOW_MAP_BLOCKS = false; // 画出所有地图块矩形
    public static final boolean DEV_SHOW_AVATAR_COLLISION_BOX = true; // 画出所有角色的碰撞箱
    public static final boolean DEV_SHOW_MAP_COLLISION_BOX = true; // 画出地图的碰撞箱
    public static final boolean DEV_SHOW_CONFLICT_COLLISION_BOX = true; // 画出发生碰撞的碰撞箱
    public static final boolean DEV_SHOW_TEMP_COLLISION_BOX = true; // 画出发生位移前用来预判的碰撞箱
    public static final boolean DEV_SHOW_PANEL_CENTRAL_POINT = true; // 画出Panel的中心点

    public static final int BLOCK_SIZE = 48; // 地图块的大小
    public static final int GAME_WIDTH = 1400; // 游戏界面的宽度
    public static final int GAME_HEIGHT = 900; // 游戏界面的高度

    public static final int BEACON_DISPLACEMENT = 50; // 信标的位移距离
    public static final int CAMERA_SPEED = 3; // 摄像机的速度

    public static final int CHANCE_OF_OBSTACLE_1 = 1000; // 1格障碍物的出现概率
    public static final int CHANCE_OF_OBSTACLE_2 = 1000; // 2格障碍物的出现概率
    public static final int CHANCE_OF_OBSTACLE_4 = 1000; // 4格障碍物的出现概率

    public static final int AVATAR_REFRESH_RATE = 16; // 角色动画刷新频率

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
