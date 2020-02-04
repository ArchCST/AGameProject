/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar;

import me.archcst.agameproject.datacenter.Framer;
import me.archcst.agameproject.map.GameMap;
import me.archcst.agameproject.ui.GamePanel;
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

    public void draw(Graphics g) {
        Point mapOffset = GameMap.getInstance().getOffset();
        Action action = actions.get(currentAction);

        int frame = Framer.getInstance().getFrame(refreshRate) % action.getFrames();
        g.drawImage(action.getImage(),
                location.x + mapOffset.x, location.y + mapOffset.y,
                location.x + mapOffset.x + (int) (dimension.width * zoom), location.y + mapOffset.y + (int) (dimension.height * zoom),
                action.sx1(frame), action.sy1(frame),
                action.sx2(frame), action.sy2(frame),
                GamePanel.getInstance());

        if (GameSettings.DEV_MODE && GameSettings.DEV_SHOW_AVATAR_COLLISION_BOX) {
            g.setColor(Color.GREEN);
            g.drawRect(collisionBox.x1, collisionBox.y1,
                    collisionBox.width, collisionBox.height);
        }
    }

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
}
