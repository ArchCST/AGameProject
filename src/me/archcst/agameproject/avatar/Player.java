/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar;

import me.archcst.agameproject.util.CollisionBox;
import me.archcst.agameproject.util.GameSettings;

import java.awt.*;
import java.io.File;

public class Player extends Avatar {
    private static Player player = null;

    public static Player getInstance(){
        System.out.println("players getInstantce");
        if (player == null) {
            synchronized (Player.class) {
                if (player == null) {
                    player = new Player();
                    player.setAlive(true);
                    player.loadPlayer();
                }
            }
        }
        return player;
    }

    private Player() {
        zoom = 1;
        dimension = new Dimension(48, 48);
        actionString = "walk_down";
        walkSpeed = 3;
        refreshRate = 0;
        // 坐标（屏幕正中）
        pPoint = new Point((GameSettings.GAME_WIDTH - dimension.width) /2,
                (GameSettings.GAME_HEIGHT - dimension.height) / 2);

        // 碰撞箱
        collisionBox = new CollisionBox(pPoint.x + 14, pPoint.y + 36,
                pPoint.x + dimension.width - 14, pPoint.y + dimension.height);
    }


    // 加载玩家角色的图片和 actions 对象组数
    public void loadPlayer() {
        Point sPoint = new Point(288, 0); // 素材图上的坐标
        File playerFile = new File("src/me/archcst/agameproject/static/img/characters/Actor1.png");

        actions.put("walk_down", new Action(player,
                playerFile, sPoint, 3));

        sPoint.y += 48;
        actions.put("walk_left", new Action(player,
                playerFile, sPoint, 3));

        sPoint.y += 48;
        actions.put("walk_right", new Action(player,
                playerFile, sPoint, 3));

        sPoint.y += 48;
        actions.put("walk_up", new Action(player,
                playerFile, sPoint, 3));

        File damageFile = new File("src/me/archcst/agameproject/static/img/characters/Damage1.png");
        sPoint.x = 0;
        sPoint.y = 96;
        actions.put("die", new Action(player,
                damageFile, sPoint, 3));
    }
}
