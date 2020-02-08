/*
 * 含小数点的坐标系统
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.map;

public class DPoint {
    private double x;
    private double y;

    public DPoint() {
        x = 0;
        y = 0;
    }

    public DPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public DPoint(DPoint DPoint) {
        x = DPoint.x;
        y = DPoint.y;
    }

    public int intX() {
        return (int) x;
    }

    public int intY() {
        return (int) y;
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    public void moveX(double x) {
        this.x += x;
    }

    public void moveY(double y) {
        this.y += y;
    }

    public void move(DPoint DPoint) {
        x += DPoint.x;
        y += DPoint.y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
