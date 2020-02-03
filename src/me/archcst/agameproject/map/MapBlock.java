/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.map;

import me.archcst.agameproject.ui.GamePanel;
import me.archcst.agameproject.util.CollisionBox;
import me.archcst.agameproject.util.GameSettings;

import java.awt.*;

public class MapBlock {
    private Image image; // 地图图片
    public Point mPoint; // 地图上的坐标
    private CollisionBox collisionBox; // 碰撞箱
    private Point sPoint; // 图片上的坐标
    private Dimension dimension; // 大小

    public MapBlock(Image image, Dimension dimension, Point mPoint, Point sPoint, CollisionBox collisionBox) {
        this.image = image;
        this.dimension = dimension;
        this.mPoint = mPoint;
        this.sPoint = sPoint;
        this.collisionBox = collisionBox;
    }

    private static Point roadPoint = new Point(96, 0); // 用于先画地板
    private static Dimension roadDimension = new Dimension(48, 48); // 用于先画地板

    /**
     * 画出地图块
     *
     * @param g      画笔
     * @param offset 偏移量，用作 player 移动时重绘 block
     */
    public void draw(Graphics g, Dimension offset) {
        // 先画一层地板
        g.drawImage(image,
                mPoint.x + offset.width, mPoint.y + offset.height,
                mPoint.x + roadDimension.width + offset.width, mPoint.y + roadDimension.height + offset.height,
                roadPoint.x, roadPoint.y,
                roadPoint.x + roadDimension.width, roadPoint.y + roadDimension.height,
                GamePanel.getInstance());

        // 再画上面的装饰
        if (sPoint != roadPoint) {
            g.drawImage(image,
                    mPoint.x + offset.width, mPoint.y + offset.height,
                    mPoint.x + dimension.width + offset.width, mPoint.y + dimension.height + offset.height,
                    sPoint.x, sPoint.y,
                    sPoint.x + dimension.width, sPoint.y + dimension.height,
                    GamePanel.getInstance());
        }

        // 开发模式画所有地图块
        if (GameSettings.DEV_SHOW_MAPBLOCKS) {
            g.setColor(Color.GRAY);
            g.drawRect(mPoint.x + offset.width, mPoint.y + offset.height,
                    dimension.width, dimension.height);
        }

        // 开发模式画碰撞箱
        if (GameSettings.DEV_MODE) {
            g.setColor(Color.BLUE);
            g.drawRect(collisionBox.x1 + offset.width, collisionBox.y1 + offset.height,
                    collisionBox.width, collisionBox.height);
        }

    }

    public CollisionBox getCollisionBox() {
        return collisionBox;
    }
}
