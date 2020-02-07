/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar.obstacles;

public class ObstacleFactory {
    public static final int OBSTACLE_TYPE_AMOUNT = 2;
    public static Obstacle createObstacle(int type) {
        Obstacle obstacle = null;
        switch (type) {
            case 0:
                obstacle = new Obstacle_Small();
                break;
            case 1:
                obstacle = new Obstacle_Middle();
                break;
        }

        return obstacle;
    }
}
