/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar;

import java.awt.*;
import java.security.cert.PolicyNode;
import java.util.HashMap;

public abstract class Avatar {
    public final HashMap<String, Action> actions = new HashMap<>(); //角色所有动作的集合
    protected Point point; // 角色的坐标
    protected double zoom; // 角色放大系数
    protected String actionString; // 当前动作的 Key
    protected int walkSpeed; // 移动速度
    protected int refreshRate;

    public void act(Graphics g, String action, int frame) {
        if (refreshRate == 0) {
            actions.get(action).draw(g,1, point, zoom);
        } else {
            actions.get(action).draw(g, frame, point, zoom);
        }
    }

    /*
     * getters & setters
     */

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

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public void setPoint(int x, int y) {
        point.x = x;
        point.y = y;
    }

    public String getActionString() {
        return actionString;
    }

    public void setActionString(String actionString) {
        this.actionString = actionString;
    }
}
