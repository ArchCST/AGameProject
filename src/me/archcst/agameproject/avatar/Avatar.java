/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar;

import me.archcst.agameproject.datacenter.Framer;
import me.archcst.agameproject.map.Location;
import me.archcst.agameproject.util.Camera;
import me.archcst.agameproject.util.CollisionBox;
import me.archcst.agameproject.util.GameSettings;

import java.awt.*;
import java.util.HashMap;

public abstract class Avatar {
    public final HashMap<String, Action> actions = new HashMap<>(); //角色所有动作的集合
    protected String currentAction; // 当前动作

    protected Location location; // 角色的坐标
    protected Dimension offset; // 字体Y轴修正
    protected double walkSpeed; // 移动速度
    protected int refreshRate; // 动画刷新率
    protected Dimension size; // 原始大小
    protected double zoom; // 角色放大系数
    protected CollisionBox collisionBox; // 碰撞箱
    protected Boolean alive; // 是否存活
    public MoveCtrl moveCtrl; // 移动控制器

    protected Avatar() {
        currentAction = "idle";
        collisionBox = new CollisionBox();
        offset = new Dimension(); // 字体Y轴修正
    }

    public void draw(Graphics g) {
        Camera camera = Camera.getInstance();
        Action action = actions.get(currentAction);
        int frame = 0;

        if (action != null) {
            frame = Framer.getInstance().getFrame(refreshRate) % action.getFrames();
        } else {
            System.out.println("找不到动作: " + currentAction);
        }
        String[] animateByFrame = action.getAnimateByFrame(frame);

        // 清空绘画区域的地图
        g.setColor(GameSettings.BACKGROUND_COLOR);
        g.fillRect(camera.packX(location.intX()), camera.packY(location.intY()),
                size.width, size.height);

        for (int i = 0; i < animateByFrame.length; i++) {
            g.setColor(action.getColor());
            g.drawString(animateByFrame[i],
                    camera.packX(location.intX() + offset.width),
                    camera.packY(location.intY() + i * GameSettings.FONT_SIZE + offset.height));
        }

        // 开发模式画出角色外框
        if (GameSettings.DEV_MODE && GameSettings.DEV_SHOW_AVATAR_BOX) {
            g.setColor(Color.BLUE);
            g.drawRect(camera.packX(location.intX()), camera.packY(location.intY()),
                    size.width, size.height);
        }

        // 开发模式画出碰撞箱
        if (GameSettings.DEV_MODE && GameSettings.DEV_SHOW_AVATAR_COLLISION_BOX) {
            g.setColor(Color.GREEN);
            collisionBox.draw(g);
        }

    }


    protected abstract void loadAction();

    /**
     * 移动角色
     *
     */
    public void avatarMove(double x, double y) {
        location.moveX(x);
        location.moveY(y);
        // 也要移动碰撞箱
        collisionBox.boxMove(x, y);
    }


    /**
     * 移动角色
     *
     */
    public void avatarMove(Location offset) {
        location.moveX(offset.x());
        location.moveY(offset.y());
        // 也要移动碰撞箱
        collisionBox.boxMove(offset);
    }

    /*
     * getters & setters
     */
    public CollisionBox getCollisionBox() {
        return collisionBox;
    }

    /**
     * 设置碰撞箱
     *
     * @param widthPercent  宽度百分比
     * @param heightPercent 高度百分比
     * @param rightPercent  右移百分比
     * @param downPercent   下移百分比
     */
    public void setCollisionBox(double widthPercent, double heightPercent, double rightPercent, double downPercent) {
        double width = size.width * zoom * widthPercent;
        double height = size.height * zoom * heightPercent;
        double x1 = location.x() + (size.width * zoom - width) / 2;
        double y1 = location.y() + (size.height * zoom - height) / 2;
        collisionBox.setCollisionBox(x1, y1, x1 + width, y1 + height);

        // 距离图片中心的偏移量
        Location offset = new Location();
        offset.setX(size.width * zoom * rightPercent);
        offset.setY(size.height * zoom * downPercent);
        collisionBox.boxMove(offset);
    }

    public Dimension getSize() {
        return size;
    }

    public void setSize(Dimension size) {
        this.size = size;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setCurrentAction(String currentAction) {
        this.currentAction = currentAction;
    }

    public void die() {
        currentAction = "die";
        alive = true;
    }
}
