/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar.weapons;

import me.archcst.agameproject.avatar.Player;
import me.archcst.agameproject.datacenter.DataCenter;

import java.awt.*;

public class Weapon_Monster extends Weapon {
    public Weapon_Monster() {
        speed = 120;
        weaponColor = Color.WHITE;
        loadWeapon();
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
        Bullet bullet = new Bullet(avatar, Player.getInstance().getCenter());
        bullet.initBullet("◉", Color.RED, -4, 4, 3);

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

                System.out.println("ba:" + bulletAmount);
                generateBullet();
                lastShootTime = System.currentTimeMillis();
            }
        }
    }

    public void setBulletAmount(int bulletAmount) {
        this.bulletAmount = bulletAmount;
    }
}
