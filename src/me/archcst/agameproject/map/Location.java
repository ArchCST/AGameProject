/*
 * 含小数点的坐标系统
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.map;

public class Location {
    private double x;
    private double y;

    public Location() {
        x = 0;
        y = 0;
    }

    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Location(Location location) {
        x = location.x;
        y = location.y;
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

    public void moveLocation(Location location) {
        x += location.x;
        y += location.y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
