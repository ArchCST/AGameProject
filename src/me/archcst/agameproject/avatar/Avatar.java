/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar;

import me.archcst.agameproject.util.CollisionBox;

import java.awt.*;
import java.util.HashMap;

public abstract class Avatar {
    public final HashMap<String, Action> actions = new HashMap<>(); //角色所有动作的集合
    protected Point pPoint; // 角色的坐标
    protected double zoom; // 角色放大系数
    protected String actionString; // 当前动作的 Key
    protected CollisionBox collisionBox; // 碰撞箱
    protected int walkSpeed; // 移动速度
    protected int refreshRate; // 动画刷新率
    protected Boolean alive; // 是否存活
    protected Dimension dimension; // 原始大小

    public void act(Graphics g, String action, int frame) {
        if (refreshRate == 0) {
            actions.get(action).draw(g,1, zoom);
        } else {
            actions.get(action).draw(g, frame, zoom);
        }
    }

    /*
     * getters & setters
     */

    public CollisionBox getCollisionBox() {
        return collisionBox;
    }

    public void setCollisionBox(CollisionBox collisionBox) {
        this.collisionBox = collisionBox;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public Boolean getAlive() {
        return alive;
    }

    public void setAlive(Boolean alive) {
        this.alive = alive;
    }

    public int getRefreshRate() {
        return refreshRate;
    }

    public void setRefreshRate(int refreshRate) {
        this.refreshRate = refreshRate;
    }

    public int getWalkSpeed() {
        return walkSpeed;
    }

    public void setWalkSpeed(int walkSpeed) {
        this.walkSpeed = walkSpeed;
    }

    public Point getpPoint() {
        return pPoint;
    }

    public void setpPoint(Point pPoint) {
        this.pPoint = pPoint;
    }

    public void setPoint(int x, int y) {
        pPoint.x = x;
        pPoint.y = y;
    }

    public String getActionString() {
        return actionString;
    }

    public void setActionString(String actionString) {
        this.actionString = actionString;
    }
}
