/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar;

import me.archcst.agameproject.map.GameMap;

import java.awt.*;

public class PlayerMoveCtrl extends MoveCtrl {
    public PlayerMoveCtrl(Avatar avatar) {
        super(avatar);
    }


    @Override
    public void move(Graphics g) {
        GameMap gameMap = GameMap.getInstance();
        Point displacement = new Point();
        // 角色不动时不刷新动画
        if (!urdl[0] && !urdl[1] && !urdl[2] && !urdl[3]) {
            avatar.setRefreshRate(0);
        } else {
            avatar.setRefreshRate(12);
        }

        if (urdl[0]) { // 上
            displacement.y = avatar.getWalkSpeed();
            displacement.y = gameMap.playerCollision(g, displacement) ? 0 : displacement.y;
        }

        if (urdl[1]) { // 右
            displacement.x = -avatar.getWalkSpeed();
            displacement.x = gameMap.playerCollision(g, displacement) ? 0 : displacement.x;
        }

        if (urdl[2]) { // 下
            displacement.y = -avatar.getWalkSpeed();
            displacement.y = gameMap.playerCollision(g, displacement) ? 0 : displacement.y;
        }

        if (urdl[3]) { // 左
            displacement.x = avatar.getWalkSpeed();
            displacement.x = gameMap.playerCollision(g, displacement) ? 0 : displacement.x;
        }

        gameMap.mapMove(displacement);
    }
}
