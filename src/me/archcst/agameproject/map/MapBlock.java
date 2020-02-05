/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.map;

import me.archcst.agameproject.ui.GamePanel;
import me.archcst.agameproject.util.Camera;
import me.archcst.agameproject.util.CollisionBox;
import me.archcst.agameproject.util.GameSettings;

import java.awt.*;

public class MapBlock {
    private Image image; // 地图图片
    private Point mPoint; // 地图上的坐标
    private Point sPoint; // 图片上的坐标
    private Dimension dimension; // 大小
    private CollisionBox collisionBox; // 碰撞箱

    /**
     * 构造地图块
     * @param image 图片对象
     * @param dimension 图片上的长和宽
     * @param mPoint 屏幕上的坐标
     * @param sPoint 图片上的坐标
     * @param collisionBox 碰撞箱
     */
    public MapBlock(Image image, Dimension dimension, Point mPoint, Point sPoint, CollisionBox collisionBox) {
        this.image = image;
        this.dimension = dimension;
        this.mPoint = mPoint;
        this.sPoint = sPoint;
        this.collisionBox = collisionBox;
    }

//    /**
//     * 移动地图块
//     * @param offset 偏移量
//     */
//    public void blockMove(Point offset) {
//        mPoint.x += offset.x;
//        mPoint.y += offset.y;
//        // 也要移动碰撞箱
//        collisionBox.boxMove(offset);
//    }

    private static Point roadPoint = new Point(96, 0); // 用于先画地板
    private static Dimension roadDimension = new Dimension(48, 48); // 用于先画地板

    /**
     * 画出地图块
     */
    public void draw(Graphics g) {
        Camera camera = Camera.getInstance();
        // 先画一层地板
        g.drawImage(image,
                camera.cameraedX(mPoint.x), camera.cameraedY(mPoint.y),
                camera.cameraedX(mPoint.x) + roadDimension.width, camera.cameraedY(mPoint.y) + roadDimension.height,
                roadPoint.x, roadPoint.y,
                roadPoint.x + roadDimension.width, roadPoint.y + roadDimension.height,
                GamePanel.getInstance());

        // 再画上面的装饰
        if (sPoint != roadPoint) {
            g.drawImage(image,
                    camera.cameraedX(mPoint.x), camera.cameraedY(mPoint.y),
                    camera.cameraedX(mPoint.x) + dimension.width, camera.cameraedY(mPoint.y) + dimension.height,
                    sPoint.x, sPoint.y,
                    sPoint.x + dimension.width, sPoint.y + dimension.height,
                    GamePanel.getInstance());
        }

        // 开发模式画所有地图块灰色外框
        if (GameSettings.DEV_MODE && GameSettings.DEV_SHOW_MAP_BLOCKS) {
            g.setColor(Color.GRAY);
            g.drawRect(camera.cameraedX(mPoint.x), camera.cameraedY(mPoint.y),
                    dimension.width, dimension.height);
        }

        // 开发模式画碰撞箱
        if (GameSettings.DEV_MODE && GameSettings.DEV_SHOW_MAP_COLLISION_BOX) {
            g.setColor(Color.BLUE);
            g.drawRect(camera.cameraedX(collisionBox.x1), camera.cameraedY(collisionBox.y1),
                    collisionBox.width, collisionBox.height);
        }

    }

    public CollisionBox getCollisionBox() {
        return collisionBox;
    }
}
