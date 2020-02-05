/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar;

import me.archcst.agameproject.map.GameMap;
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

        Camera camera = Camera.getInstance();

        Point beacon = new Point();
        if (up() || right() || down() || left()) {
            if (up()) beacon.y = -GameSettings.BEACON_DISPLACEMENT;
            if (right()) beacon.x = GameSettings.BEACON_DISPLACEMENT;
            if (down()) beacon.y = GameSettings.BEACON_DISPLACEMENT;
            if (left()) beacon.x = -GameSettings.BEACON_DISPLACEMENT;
        }

        beacon.x += avatar.location.x;
        beacon.y += avatar.location.y;

        camera.setBeacon(beacon);

    }
}
