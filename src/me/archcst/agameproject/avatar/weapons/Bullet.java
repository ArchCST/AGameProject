/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar.weapons;

import me.archcst.agameproject.avatar.Avatar;
import me.archcst.agameproject.map.DPoint;
import me.archcst.agameproject.util.Camera;

import java.awt.*;
import java.awt.geom.Line2D;

public class Bullet {
    private String image;
    private Avatar belongs; // 所有者
    private DPoint targetPoint; // 目标坐标
    private Color color; // 子弹颜色
    private DPoint location; // 子弹当前位置
    private DPoint imageOffset; // 字符打印位置修正
    private DPoint offset; // 每帧子弹偏移
    private Line2D collisionLine; // 单颗子弹位移时的碰撞线
    private double distance; // 每一帧子弹的位移距离

    public Bullet(Avatar belongs, DPoint targetPoint) {
        this.targetPoint = targetPoint;
        this.belongs = belongs;
        offset = new DPoint();
        location = new DPoint();
        collisionLine = new Line2D.Double();
    }

    public void initBullet(String image, Color color, double imageOffsetX, double imageOffsetY, double distance) {
        this.image = image;
        this.color = color;
        this.imageOffset = new DPoint(imageOffsetX, imageOffsetY);
        this.distance = distance;
        initLocationAndOffset();
    }

    /**
     * 计算每一帧 x 轴和 y 轴的偏移量，以及子弹初始位置
     */
    public void initLocationAndOffset() {
        DPoint al = new DPoint(belongs.getCenter().x() + 22, belongs.getCenter().y() + 22); // 所有者中心点

        double targetDistance = Math.sqrt((targetPoint.x() - al.x()) * (targetPoint.x() - al.x()) +
                (targetPoint.y() - al.y()) * (targetPoint.y() - al.y()));

        // 设置每一帧的偏移量
        offset.setX(distance * (targetPoint.x()- al.x())/targetDistance);
        offset.setY(distance * (targetPoint.y()- al.y())/targetDistance);

        // 设置子弹的初始位置
        double d = 20; // 初始位置距离中心的距离
        location.setX(al.x() + d * (targetPoint.x()- al.x())/targetDistance);
        location.setY(al.y() + d * (targetPoint.y()- al.y())/targetDistance);
    }

    // 子弹移动到下一个位置
    public void nextLocation() {
        // 上一帧和这一帧之间的线段为碰撞线
        collisionLine = new Line2D.Double(location.x(), location.y(),
                location.x() + offset.x(), location.y() + offset.y());
        // 更新当前子弹坐标
        location.move(offset);
    }

    public void hit() {
//        belongs.getWeapon().getBullets().remove(this); // 从子弹列表中删除
        // 为 avatar 新增受击体
        // 子弹坐标爆炸
    }

    public void draw(Graphics g) {
        Camera camera = Camera.getInstance();

        g.setColor(color);
        g.drawString(image, camera.packX(location.x() + imageOffset.x()),
                camera.packY(location.y() + imageOffset.y()));

        // 碰撞线，正好可以作为子弹的拖尾
        g.drawLine(camera.packX(collisionLine.getX1()), camera.packY(collisionLine.getY1()),
                camera.packX(collisionLine.getX2()), camera.packY(collisionLine.getY2()));
    }


}
