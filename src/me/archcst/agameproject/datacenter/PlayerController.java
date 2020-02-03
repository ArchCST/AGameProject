/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.datacenter;

import me.archcst.agameproject.avatar.Player;
import me.archcst.agameproject.map.GameMap;

import java.awt.*;

public class PlayerController {
    private Player player = Player.getInstance();
    private Dimension tempMapOffset = GameMap.getInstance().getOffset();
    private Boolean up = false, right = false, down = false, left = false;

    public void playerMoving(Graphics g) {
        GameMap gameMap = GameMap.getInstance();
        // 角色不动时不刷新动画
        if (!up && !down && !left && !right) {
            player.setRefreshRate(0);
        } else {
            player.setRefreshRate(12);
        }

        if (up) {
            tempMapOffset.height = gameMap.getOffset().height + player.getWalkSpeed();
            if (gameMap.playerCollision(g, tempMapOffset)) {
                tempMapOffset.height = gameMap.getOffset().height - player.getWalkSpeed();
            }
        }
        if (down) {
            tempMapOffset.height = gameMap.getOffset().height - player.getWalkSpeed();
            if (gameMap.playerCollision(g, tempMapOffset)) {
                tempMapOffset.height = gameMap.getOffset().height + player.getWalkSpeed();
            }
        }
        if (left) {
            tempMapOffset.width = gameMap.getOffset().width + player.getWalkSpeed();
            if (gameMap.playerCollision(g, tempMapOffset)) {
                tempMapOffset.width = gameMap.getOffset().width - player.getWalkSpeed();
            }
        }
        if (right) {
            tempMapOffset.width = gameMap.getOffset().width - player.getWalkSpeed();
            if (gameMap.playerCollision(g, tempMapOffset)) {
                tempMapOffset.width = gameMap.getOffset().width + player.getWalkSpeed();
            }
        }

        gameMap.setOffset(tempMapOffset);
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

            if (up) player.setActionString("walk_up");
            if (down) player.setActionString("walk_down");
            if (left) player.setActionString("walk_left");
            if (right) player.setActionString("walk_right");
        }
    }

    public void die() {
        player.setActionString("die");
        player.setAlive(false);
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
