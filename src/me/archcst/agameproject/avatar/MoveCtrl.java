/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar;

import me.archcst.agameproject.datacenter.Framer;
import me.archcst.agameproject.map.GameMap;
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
        Point displacement = new Point(0, 0);

        setAvatarRefreshRate();

        if (up() && !down()) { // 上
            displacement.y = -avatar.walkSpeed;
            tempCB = new CollisionBox(avatar.getCollisionBox(), displacement);
            if (gameMap.mapCollision(g, tempCB)) {
                displacement.y = 0;
            }
        }
        if (right() && !left()) { // 右
            displacement.x = avatar.walkSpeed;
            tempCB = new CollisionBox(avatar.getCollisionBox(), displacement);
            if (gameMap.mapCollision(g, tempCB)) {
                displacement.x = 0;
            }
        }
        if (down() && !up()) { // 下
            displacement.y = avatar.walkSpeed;
            tempCB = new CollisionBox(avatar.getCollisionBox(), displacement);
            if (gameMap.mapCollision(g, tempCB)) {
                displacement.y = 0;
            }
        }
        if (left() && !right()) { // 左
            displacement.x = -avatar.walkSpeed;
            tempCB = new CollisionBox(avatar.getCollisionBox(), displacement);
            if (gameMap.mapCollision(g, tempCB)) {
                displacement.x = 0;
            }
        }

        avatar.avatarMove(displacement);
    }

    private void testCode(Graphics g, Point displacement) {
        System.out.print(up() + " ");
        System.out.print(right() + " ");
        System.out.print(down() + " ");
        System.out.print(left() + " ");
        String frame = " 帧: " + Framer.getInstance().getFrame();
        System.out.println(displacement + " " + frame);
        g.drawString(frame, Camera.getInstance().packX(avatar.location.x), Camera.getInstance().packY(avatar.location.y));
    }

    /**
     * 根据传入字符串设置人物方向
     *
     * @param direction 方向字符串
     * @param p         true: 按下  false: 释放
     */
    public void setDirection(String direction, Boolean p) {
        if (p) {
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
        } else {
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
