/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar;

import me.archcst.agameproject.map.GameMap;
import me.archcst.agameproject.map.Location;
import me.archcst.agameproject.util.GameSettings;

import java.awt.*;

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
        size = new Dimension(18, 44);
        walkSpeed = 2;
        refreshRate = GameSettings.AVATAR_REFRESH_RATE;
        alive = true;
        moveCtrl = new PlayerMoveCtrl(this);

        loadAction();
        location = new Location(
                (GameMap.getInstance().getMapSize().width / 2) * GameSettings.BLOCK_SIZE + 10,
                (GameMap.getInstance().getMapSize().height / 2) * GameSettings.BLOCK_SIZE);
        offset.width = 0;
        offset.height = 12;

        // 碰撞箱
        setCollisionBox(1, 0.5, 0, 0.2);
//        collisionBox = new CollisionBox(location.x + 13, location.y + 36,
//                location.x + sSize.width - 14, location.y + sSize.height);

    }

    @Override
    protected void loadAction() {
        // 不动
        String[][] animate = new String[1][3];

        animate[0][0] = " O ";
        animate[0][1] = "/|\\";
        animate[0][2] = "/ \\";

        actions.put("idle", new Action(animate, Color.WHITE));

        // 右移
        animate = new String[3][3];

        animate[0][0] = " O ";
        animate[0][1] = "/|\\";
        animate[0][2] = "/ \\";

        animate[1][0] = " O ";
        animate[1][1] = "/|\\";
        animate[1][2] = "/ >";

        animate[2][0] = " O ";
        animate[2][1] = "/|\\";
        animate[2][2] = " >\\";

        actions.put("walk_right", new Action(animate, Color.WHITE));

        // 左移
        animate = new String[3][3];

        animate[0][0] = " O ";
        animate[0][1] = "/|\\";
        animate[0][2] = "/ \\";

        animate[1][0] = " O ";
        animate[1][1] = "/|\\";
        animate[1][2] = "< \\";

        animate[2][0] = " O ";
        animate[2][1] = "/|\\";
        animate[2][2] = "/<";

        actions.put("walk_left", new Action(animate, Color.WHITE));
    }

}
