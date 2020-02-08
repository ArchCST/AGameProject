/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar.weapons;

import me.archcst.agameproject.avatar.Avatar;
import me.archcst.agameproject.datacenter.DataCenter;
import me.archcst.agameproject.map.DPoint;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public abstract class Weapon {
    protected Avatar avatar; // 所有者
    protected String[] imageLeft = new String[3]; // 武器外观
    protected String[] imageRight = new String[3]; // 武器外观
    protected Color weaponColor; // 武器颜色
    protected int refreshRate;
    protected DPoint offsetLeft; // 绘画位置的偏移量
    protected DPoint offsetRight; // 绘画位置的偏移量

    protected int speed; // 射速，每分钟多少颗子弹
    protected double gap; // 射击间隙
    protected int damage; // 子弹默认伤害
    protected double cri; // 暴击率
    protected double crd; // 暴击倍率


    public Weapon() {
        offsetLeft = new DPoint();
        offsetRight = new DPoint();
        refreshRate = 16;
        speed = 1;
    }

    public Color getWeaponColor() {
        return weaponColor;
    }

    public String[] getImageLeft() {
        return imageLeft;
    }

    public String[] getImageRight() {
        return imageRight;
    }


    public abstract void shoot();

    protected abstract void generateBullet();

    public DPoint offsetLeft() {
        return offsetLeft;
    }

    public DPoint offsetRight() {
        return offsetRight;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
        avatar.setWeapon(this);
    }
}
