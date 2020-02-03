/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.util;

import java.awt.*;
import java.util.Objects;

public class CollisionBox {
    public int x1;
    public int y1;
    public int x2;
    public int y2;
    public int width;
    public int height;

    /**
     * @param x1 左上x
     * @param y1 左上y
     * @param x2 右下x
     * @param y2 右下y
     */
    public CollisionBox(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        width = x2 - x1;
        height = y2 - y1;
    }

    public void setCollisionBox(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        width = x2 - x1;
        height = y2 - y1;
    }

    public CollisionBox(CollisionBox collisionBox, Dimension offset) {
        this.x1 = collisionBox.x1 + offset.width;
        this.x2 = collisionBox.x2 + offset.width;
        this.y1 = collisionBox.y1 + offset.height;
        this.y2 = collisionBox.y2 + offset.height;
        this.width = collisionBox.x2 - collisionBox.x1;
        this.height = collisionBox.y2 - collisionBox.y1;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof CollisionBox) {
            CollisionBox box = (CollisionBox) object;
            // object 在外层
            if (this.x1 > box.x1 && this.x1 < box.x2) {
                if (this.y1 > box.y1 && this.y1 < box.y2) {
                    return true;
                }
                if (this.y2 > box.y1 && this.y2 < box.y2) {
                    return true;
                }
            }
            if (this.x2 > box.x1 && this.x2 < box.x2) {
                if (this.y1 > box.y1 && this.y1 < box.y2) {
                    return true;
                }
                if (this.y2 > box.y1 && this.y2 < box.y2) {
                    return true;
                }
            }

            // this 在外层
            if (box.x1 > this.x1 && box.x1 < this.x2) {
                if (box.y1 > this.y1 && box.y1 < this.y2) {
                    return true;
                }
                if (box.y2 > this.y1 && box.y2 < this.y2) {
                    return true;
                }
            }
            if (box.x2 > this.x1 && box.x2 < this.x2) {
                if (box.y1 > this.y1 && box.y1 < this.y2) {
                    return true;
                }
                if (box.y2 > this.y1 && box.y2 < this.y2) {
                    return true;
                }
            }
        }
        return false;
    }
}
