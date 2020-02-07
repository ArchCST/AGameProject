/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar;

import me.archcst.agameproject.datacenter.Framer;
import me.archcst.agameproject.map.GameMap;
import me.archcst.agameproject.map.Location;
import me.archcst.agameproject.util.Camera;
import me.archcst.agameproject.util.CollisionBox;
import me.archcst.agameproject.util.GameSettings;

import java.awt.*;

public abstract class MoveCtrl {
    protected boolean[] urdl = new boolean[]{false, false, false, false}; // 上右下左
    protected Avatar avatar;

    public MoveCtrl(Avatar avatar) {
        this.avatar = avatar;
    }


    public abstract void move(Graphics g);

    /**
     * 确认和地图的碰撞箱不冲突，然后按照 urdl 设置的方向移动
     */
    protected void validateAndMove(Graphics g) {
        GameMap gameMap = GameMap.getInstance();
        CollisionBox tempCB;
        Location displacement = new Location(0, 0);

        setAvatarRefreshRate();

        if (up() && !down()) { // 上
            displacement.setY(-moveDistance());
            tempCB = new CollisionBox(avatar.getCollisionBox(), displacement);
            if (gameMap.mapCollision(g, tempCB)) {
                displacement.setY(0);
            }
        }
        if (right() && !left()) { // 右
            displacement.setX(moveDistance());
            tempCB = new CollisionBox(avatar.getCollisionBox(), displacement);
            if (gameMap.mapCollision(g, tempCB)) {
                displacement.setX(0);
            }
        }
        if (down() && !up()) { // 下
            displacement.setY(moveDistance());
            tempCB = new CollisionBox(avatar.getCollisionBox(), displacement);
            if (gameMap.mapCollision(g, tempCB)) {
                displacement.setY(0);
            }
        }
        if (left() && !right()) { // 左
            displacement.setX(-moveDistance());
            tempCB = new CollisionBox(avatar.getCollisionBox(), displacement);
            if (gameMap.mapCollision(g, tempCB)) {
                displacement.setX(0);
            }
        }

        avatar.avatarMove(displacement);
    }

    // 返回x轴或者y轴的位移值，根据勾股定律计算
    private double moveDistance() {
        int directions = 0;
        for (boolean b:urdl) {
            if (b) directions++;
        }

        if (directions % 2 == 1) {
            return avatar.walkSpeed;
        } else return Math.sqrt(avatar.walkSpeed * avatar.walkSpeed / 2);
    }

    /**
     * 根据传入字符串设置人物方向
     *
     * @param direction 方向字符串
     * @param p         true: 按下  false: 释放
     */
    public void setDirection(String direction, Boolean p) {
        if (p) { // 按下按键
            switch (direction) {
                case "up":
                    setUp(true);
                    if (!left()) {
                        avatar.setCurrentAction("walk_right");
                    }
                    break;
                case "right":
                    setRight(true);
                    if (!left()) {
                        avatar.setCurrentAction("walk_right");
                    }
                    break;
                case "down":
                    setDown(true);
                    if (!right()) {
                        avatar.setCurrentAction("walk_left");
                    }
                    break;
                case "left":
                    setLeft(true);
                    if (!right()) {
                        avatar.setCurrentAction("walk_left");
                    }
                    break;
            }
        } else { // 放开按键
            switch (direction) {
                case "up":
                    setUp(false);
                    break;
                case "down":
                    setDown(false);
                    break;
                case "left":
                    setLeft(false);
                    break;
                case "right":
                    setRight(false);
                    break;
            }

//            if (up()) avatar.setCurrentAction("walk_up");
//            if (down()) avatar.setCurrentAction("walk_down");
//            if (left()) avatar.setCurrentAction("walk_left");
//            if (right()) avatar.setCurrentAction("walk_right");
        }
    }

    private void setAvatarRefreshRate() {
        if (up() || right() || down() || left()) {
            avatar.setRefreshRate(GameSettings.AVATAR_REFRESH_RATE);
        } else {
            avatar.setRefreshRate(0);
        }
    }

    /*
     * setters & getters
     */
    public boolean up() {
        return urdl[0];
    }

    public boolean right() {
        return urdl[1];
    }

    public boolean down() {
        return urdl[2];
    }

    public boolean left() {
        return urdl[3];
    }

    public void setUp(boolean b) {
        urdl[0] = b;
    }

    public void setRight(boolean b) {
        urdl[1] = b;
    }

    public void setDown(boolean b) {
        urdl[2] = b;
    }

    public void setLeft(boolean b) {
        urdl[3] = b;
    }
}
