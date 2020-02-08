/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar;

import me.archcst.agameproject.map.DPoint;
import me.archcst.agameproject.util.Camera;
import me.archcst.agameproject.util.GameSettings;

import java.awt.*;

public class PlayerMoveCtrl extends MoveCtrl {

    public PlayerMoveCtrl(Avatar avatar) {
        super(avatar);
    }

    @Override
    public void move(Graphics g) {
        validateAndMove(g);

        // 设置信标
        DPoint beacon = new DPoint();
        if (up() || right() || down() || left()) {
            if (up()) beacon.setY(-GameSettings.BEACON_DISPLACEMENT);
            if (right()) beacon.setX(GameSettings.BEACON_DISPLACEMENT);
            if (down()) beacon.setY(GameSettings.BEACON_DISPLACEMENT);
            if (left()) beacon.setX(-GameSettings.BEACON_DISPLACEMENT);
        }

        beacon.moveX(avatar.location.x() - 12);
        beacon.moveY(avatar.location.y());

        Camera.getInstance().setBeacon(beacon);
    }
}
