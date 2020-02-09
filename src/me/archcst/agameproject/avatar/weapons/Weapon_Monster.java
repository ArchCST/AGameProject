/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar.weapons;

import me.archcst.agameproject.avatar.Avatar;
import me.archcst.agameproject.avatar.Player;
import me.archcst.agameproject.datacenter.DataCenter;

import java.awt.*;

public class Weapon_Monster extends Weapon {
    private double distance; // 子弹移动速度（距离）
    private int singleShootAmount; // 单次射击的个数
    private int damage; // 单发子弹的伤害

    // 怪物武器的属性：射速，子弹移动速度，单词射击的个数，单发子弹的伤害，射速
    public Weapon_Monster(Avatar avatar, int speed, double distance, int singleShootAmount, int damage) {
        super(avatar);
        this.speed = speed;
        this.distance = distance;
        this.singleShootAmount = singleShootAmount;
        this.damage = damage;
    }

    private void loadWeapon() {
//        imageRight[0] = " ┳一";
//        imageRight[1] = "┳一 ";
//        imageRight[2] = " ┳一";
//        offsetRight.setX(11);
//        offsetRight.setY(23);
//
//        imageLeft[0] = "一┳";
//        imageLeft[1] = " 一┳";
//        imageLeft[2] = "一┳";
//        offsetLeft.setX(-22);
//        offsetLeft.setY(23);
    }

    @Override
    protected void generateBullet() {
        Bullet bullet = new Bullet(avatar, Player.getInstance().getCenter(), damage);
        bullet.initBullet("◉", Color.WHITE, -4, 4, distance);

        DataCenter.getInstance().monsterBullets.add(bullet);
    }


    private long lastShootTime = System.currentTimeMillis(); // 上一次射击的时间
    private int bulletAmount = 0; // 单次射击生成的子弹数量

    @Override
    public void shoot() {
        long bulletGap = 60000 / speed; // 两发子弹间的间隔时间

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastShootTime > bulletGap) {
            if (bulletAmount-- > 0) {
                generateBullet();
                lastShootTime = System.currentTimeMillis();
            }
        }
    }

    public void setBulletAmount(int bulletAmount) {
        this.bulletAmount = bulletAmount;
    }

    public int getSingleShootAmount() {
        return singleShootAmount;
    }
}
