/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar.obstacles;

import me.archcst.agameproject.avatar.Action;

import java.awt.*;

public class ObstacleFactory {
    public static final int OBSTACLE_TYPE_AMOUNT = 3;
    public static Obstacle createObstacle(int type) {
        Obstacle obstacle = null;
        switch (type) {
            case 0:
                obstacle = new Obstacle_S();
                break;
            case 1:
                obstacle = new Obstacle_M_1();
                break;
            case 2:
                obstacle = new Obstacle_M_2();
                break;
        }

        return obstacle;
    }
}
