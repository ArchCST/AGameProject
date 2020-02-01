/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar;

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
        point = new Point(0, 0);
    }
}
