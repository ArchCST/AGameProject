/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar;

import me.archcst.agameproject.datacenter.Framer;
import me.archcst.agameproject.map.GameMap;
import me.archcst.agameproject.ui.GamePanel;
import me.archcst.agameproject.util.Camera;
import me.archcst.agameproject.util.CollisionBox;
import me.archcst.agameproject.util.GameSettings;

import java.awt.*;
import java.io.File;
import java.util.HashMap;

public abstract class Avatar {
    public final HashMap<String, Action> actions = new HashMap<>(); //角色所有动作的集合
    protected Point location; // 角色的坐标
    protected String currentAction; // 当前动作的 Key
    protected int walkSpeed; // 移动速度
    protected int refreshRate; // 动画刷新率
    protected Dimension dimension; // 原始大小
    protected double zoom; // 角色放大系数
    protected CollisionBox collisionBox; // 碰撞箱
    protected Boolean alive; // 是否存活
    public MoveCtrl moveCtrl;

    public Avatar() {
    }

    public void draw(Graphics g){
        Camera camera = Camera.getInstance();
        Action action = actions.get(currentAction);

        int frame = Framer.getInstance().getFrame(refreshRate) % action.getFrames();
        g.drawImage(action.getImage(),
                camera.cameraedX(location.x), camera.cameraedY(location.y),
                camera.cameraedX(location.x) + (int) (dimension.width * zoom), camera.cameraedY(location.y) + (int) (dimension.height * zoom),
                action.sx1(frame), action.sy1(frame),
                action.sx2(frame), action.sy2(frame),
                GamePanel.getInstance());

        if (GameSettings.DEV_MODE && GameSettings.DEV_SHOW_AVATAR_COLLISION_BOX) {
            g.setColor(Color.GREEN);
            g.drawRect(camera.cameraedX(collisionBox.x1), camera.cameraedY(collisionBox.y1),
                    collisionBox.width, collisionBox.height);
        }
    };

    protected void loadAvatarMovements(Point sPoint, File imageFile) {
        actions.put("walk_down", new Action(this,
                imageFile, sPoint, 3));

        sPoint.y += 48;
        actions.put("walk_left", new Action(this,
                imageFile, sPoint, 3));

        sPoint.y += 48;
        actions.put("walk_right", new Action(this,
                imageFile, sPoint, 3));

        sPoint.y += 48;
        actions.put("walk_up", new Action(this,
                imageFile, sPoint, 3));
    }

    protected void loadAvatarDie(Point sPoint, File imageFile) {
        actions.put("die", new Action(this,
                imageFile, sPoint, 3));
    }

    /**
     * 移动角色
     * @param offset 偏移量
     */
    public void avatarMove(Point offset) {
        location.x += offset.x;
        location.y += offset.y;
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
     * @param widthPercent 宽度百分比
     * @param heightPercent 高度百分比
     * @param rightPercent 右移百分比
     * @param downPercent 下移百分比
     */
    public void setCollisionBox(double widthPercent, double heightPercent, double rightPercent, double downPercent) {
        CollisionBox collisionBox = new CollisionBox();
        int width = (int) (dimension.width * widthPercent);
        int height = (int) (dimension.height * heightPercent);
        collisionBox.x1 = location.x + (dimension.width - width) / 2;
        collisionBox.y1 = location.y + (dimension.height - height) / 2;
        collisionBox.x2 = location.x + width;
        collisionBox.y2 = location.x + height;

        // 距离图片中心的偏移量
        Point offset = new Point();
        offset.x = dimension.width / 2 + (int)(dimension.width * rightPercent);
        offset.y = dimension.height / 2 + (int)(dimension.height * downPercent);
        collisionBox.boxMove(offset);
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

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public void setLocation(int x, int y) {
        location.x = x;
        location.y = y;
    }

    public String getCurrentAction() {
        return currentAction;
    }

    public void setCurrentAction(String currentAction) {
        this.currentAction = currentAction;
    }

    public void die() {
        currentAction = "die";
        alive = true;
    }

    public void setZoom(double zoom) {
        this.zoom = zoom;
    }
}
