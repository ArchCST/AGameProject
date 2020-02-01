/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.datacenter;

import me.archcst.agameproject.avatar.Player;

import java.awt.*;

public class PlayerController {
    Player player = Player.getInstance();

    private Boolean up = false, right = false, down = false, left = false;

    public void changePlayerLocation() {
        Point playerLocation = player.getPoint();

        if (!up && !down && !left && !right) {
            player.setRefreshRate(0);
        } else {
            player.setRefreshRate(16);
        }

        if (up) {
            playerLocation.y -= player.getWalkSpeed();
        }
        if (down) {
            playerLocation.y += player.getWalkSpeed();
        }
        if (left) {
            playerLocation.x -= player.getWalkSpeed();
        }
        if (right) {
            playerLocation.x += player.getWalkSpeed();
        }

        player.setPoint(playerLocation);
    }


    /**
     * 根据按键设置人物方向
     *
     * @param direction 方向
     * @param p         true: 按下  false: 释放
     */
    public void setDirections(String direction, Boolean p) {
        if (p) {
            switch (direction) {
                case "up":
                    up = true;
                    if (!down) {
                        player.setActionString("walk_up");
                    }
                    break;
                case "down":
                    down = true;
                    if (!up) {
                        player.setActionString("walk_down");
                    }
                    break;
                case "left":
                    left = true;
                    if (!right) {
                        player.setActionString("walk_left");
                    }
                    break;
                case "right":
                    right = true;
                    if (!left) {
                        player.setActionString("walk_right");
                    }
                    break;
            }
        } else {
            switch (direction) {
                case "up":
                    up = false;
                    break;
                case "down":
                    down = false;
                    break;
                case "left":
                    left = false;
                    break;
                case "right":
                    right = false;
                    break;
            }

            if (up) {
                player.setActionString("walk_up");
            }
            if (down) {
                player.setActionString("walk_down");
            }
            if (left) {
                player.setActionString("walk_left");
            }
            if (right) {
                player.setActionString("walk_right");
            }
        }

    }

    /*
     * setters & getters
     */
    private static PlayerController playerController = null;

    public static PlayerController getInstance() {
        if (playerController == null) {
            synchronized (PlayerController.class) {
                if (playerController == null) {
                    playerController = new PlayerController();
                }
            }
        }
        return playerController;
    }

    private PlayerController() {
    }
}
