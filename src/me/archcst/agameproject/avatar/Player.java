/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar;

import me.archcst.agameproject.map.GameMap;
import me.archcst.agameproject.util.CollisionBox;
import me.archcst.agameproject.util.GameSettings;

import java.awt.*;
import java.io.File;

public class Player extends Avatar {
    private static Player player = null;

    public static Player getInstance(){
        if (player == null) {
            synchronized (Player.class) {
                if (player == null) {
                    player = new Player();
                }
            }
        }
        return player;
    }

    private Player() {
        zoom = 1;
        sSize = new Dimension(48, 48);
        currentAction = "walk_down";
        walkSpeed = 2;
        refreshRate = GameSettings.AVATAR_REFRESH_RATE;
        alive = true;
        moveCtrl = new PlayerMoveCtrl(this);

        loadAvatarMovements(new Point(288, 0),
                new File("src/me/archcst/agameproject/static/img/characters/Actor1.png"));

        loadAvatarDie(new Point(0, 96),
                new File("src/me/archcst/agameproject/static/img/characters/Damage1.png"));

        location = new Point(
                (GameMap.getInstance().getMapSize().width / 2) * GameMap.BLOCK_SIZE,
                (GameMap.getInstance().getMapSize().height / 2) * GameMap.BLOCK_SIZE);

        // 碰撞箱
        collisionBox = new CollisionBox(location.x + 13, location.y + 36,
                location.x + sSize.width - 14, location.y + sSize.height);
    }
}
