/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.util;

import java.awt.*;
import java.io.File;
import java.util.Random;

public class GameSettings {
    // 开发
    public static Random r = new Random(); // 控制游戏所有的随机数产生，可添加seed进行测试
    public static final boolean DEV_MODE = true; // 开发模式开关
    public static boolean DEV_SHOW_AVATAR_COLLISION_BOX = false; // 画出所有角色的碰撞箱 5
    public static boolean DEV_SHOW_AVATAR_BOX = false; // 画出所有角色的外框 4
    public static boolean DEV_SHOW_MAP_COLLISION_BOX = false; // 画出地图的碰撞箱
    public static boolean DEV_SHOW_CONFLICT_COLLISION_BOX = false; // 画出发生碰撞的碰撞箱 6
    public static boolean DEV_SHOW_TEMP_COLLISION_BOX = false; // 画出发生位移前用来预判的碰撞箱 7
    public static boolean DEV_SHOW_PANEL_CENTRAL_POINT = false; // 画出Panel的中心点 3
    public static final boolean DEV_SHOW_AVATAR_CENTRAL_POINT = false; // 画出所有角色的中心点
    public static boolean DEV_SHOW_BEACON = false; // 画出Beacon 8

    // 游戏基本设置
    public static boolean HINT = true; // 显示按键提示 1
    public static boolean SHOW_AIMING = false; // 显示准星 2
    public static boolean MONSTER_MOVE = true; // 显示准星 2
    public static final long PLAYER_INVINCIBLE_TIME = 500; // 扣血前的无敌时间
    public static final int GAME_WIDTH = 1280; // 游戏界面的宽度
    public static final int GAME_HEIGHT = 720; // 游戏界面的高度
    public static final long GAME_SLEEP_TIME = 8; // 游戏帧数控制
    public static final int AVATAR_REFRESH_RATE = 16; // 角色动画刷新频率
    public static final File RECORD_FILE = new File("src/me/archcst/agameproject/records"); // 记录存档文件

    // 地图
    public static final int MAP_MIN_WIDTH = 15; // 地图最小宽度
    public static final int MAP_MAX_WIDTH = 27; // 地图最大宽度
    public static final int MAP_MIN_HEIGHT = 15; // 地图最小高度
    public static final int MAP_MAX_HEIGHT = 21; // 地图最大高度
    public static final int BLOCK_SIZE = 42; // 地图块的大小
    public static final double OBSTACLE_RATE = 0.1; // 障碍物相对地图块的出现率

    // 怪物
    public static final long MONSTER_MIN_MOVE_TIME = 500; // 单次最小移动时间
    public static final long MONSTER_MAX_MOVE_TIME = 1000; // 单次最大移动时间
    public static final double MONSTER_IDLE_CHANCE = 0.3; // 怪物停止移动的几率
    public static final double MONSTER_ATTACK_CHANCE = 0.5; // 怪物攻击机率

    // 摄像机
    public static final int BEACON_DISPLACEMENT = 80; // 信标的位移距离
    public static final double CAMERA_SPEED = 1.5; // 摄像机的速度

    // ASCII 画风设置
    public static final int FONT_SIZE = 13; // 字体大小
//    public static final Font FONT = new Font("WenQuanYi Micro Hei Mono Regular", Font.PLAIN, FONT_SIZE);
    public static final Color MAP_COLOR = Color.darkGray;
    public static final Color BACKGROUND_COLOR = Color.black;
    public static final Color PLAYER_NORMAL_COLOR = Color.WHITE;
}
