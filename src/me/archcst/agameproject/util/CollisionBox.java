/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.util;

import me.archcst.agameproject.map.DPoint;

import java.awt.*;
import java.awt.geom.Line2D;

public class CollisionBox implements Cloneable {
    public double x1;
    public double y1;
    public double x2;
    public double y2;
    public double width;
    public double height;

    /**
     * @param x1 左上x
     * @param y1 左上y
     * @param x2 右下x
     * @param y2 右下y
     */
    public CollisionBox(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        width = x2 - x1;
        height = y2 - y1;
    }

    public void setCollisionBox(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        width = x2 - x1;
        height = y2 - y1;
    }

    public CollisionBox() {}

    public boolean isEmpty() {
        return x2 - x1 == 0 || y2 - y1 == 0;
    }


    public CollisionBox(CollisionBox collisionBox, DPoint offset) {
        x1 = collisionBox.x1 + offset.x();
        y1 = collisionBox.y1 + offset.y();
        x2 = collisionBox.x2 + offset.x();
        y2 = collisionBox.y2 + offset.y();
        width = (int) (x2 - x1);
        height = (int) (y2 - y1);
    }


    public synchronized void boxMove(DPoint offset) {
        boxMove(offset.x(), offset.y());
    }


    public synchronized void boxMove(double x, double y) {
        x1 += x;
        y1 += y;
        x2 += x;
        y2 += y;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    // 是否覆盖某一线段，用于视野判断
    public boolean coverLine(double x1, double y1, double x2, double y2) {
        boolean up = Line2D.linesIntersect(x1, y1, x2, y2,
                this.x1, this.y1, this.x2, this.y1);

        boolean right = Line2D.linesIntersect(x1, y1, x2, y2,
                this.x2, this.y2, this.x2, this.y2);

        boolean down = Line2D.linesIntersect(x1, y1, x2, y2,
                this.x2, this.y1, this.x2, this.y2);

        boolean left = Line2D.linesIntersect(x1, y1, x2, y2,
                this.x1, this.y1, this.x2, this.y1);

        return up || right || down || left;
    }

    // 碰撞
    public boolean crash(CollisionBox cb) {
        double tw = this.width;
        double th = this.height;
        double cbw = cb.width;
        double cbh = cb.height;
        if (cbw <= 0 || cbh <= 0 || tw <= 0 || th <= 0) {
            return false;
        }
        double tx = this.x1;
        double ty = this.y1;
        double rx = cb.x1;
        double ry = cb.y1;
        cbw += rx;
        cbh += ry;
        tw += tx;
        th += ty;
        //      overflow || intersect
        return ((cbw < rx || cbw > tx) &&
                (cbh < ry || cbh > ty) &&
                (tw < tx || tw > rx) &&
                (th < ty || th > ry));
    }

    @Override
    public boolean equals(Object object) {
        boolean b = false;
        if (object instanceof CollisionBox) {
            CollisionBox box = (CollisionBox) object;
            b = this.crash(box);
//            b = thisRect.intersects(objectRect);
            // object 在外层
//            if (this.x1 >= box.x1 && this.x1 <= box.x2) {
//                if (this.y1 >= box.y1 && this.y1 <= box.y2) {
//                    return true;
//                }
//                if (this.y2 >= box.y1 && this.y2 <= box.y2) {
//                    return true;
//                }
//            }
//            if (this.x2 >= box.x1 && this.x2 <= box.x2) {
//                if (this.y1 >= box.y1 && this.y1 <= box.y2) {
//                    return true;
//                }
//                if (this.y2 >= box.y1 && this.y2 <= box.y2) {
//                    return true;
//                }
//            }
//
//            // this 在外层
//            if (box.x1 >= this.x1 && box.x1 <= this.x2) {
//                if (box.y1 >= this.y1 && box.y1 <= this.y2) {
//                    return true;
//                }
//                if (box.y2 >= this.y1 && box.y2 <= this.y2) {
//                    return true;
//                }
//            }
//            if (box.x2 >= this.x1 && box.x2 <= this.x2) {
//                if (box.y1 >= this.y1 && box.y1 <= this.y2) {
//                    return true;
//                }
//                if (box.y2 >= this.y1 && box.y2 <= this.y2) {
//                    return true;
//                }
//            }
        }
        return b;
    }

    public void draw(Graphics g) {
        Camera camera = Camera.getInstance();
        g.setColor(Color.YELLOW);
        g.drawRect(camera.packX(x1), camera.packY(y1), (int)width, (int)height);
    }

}
