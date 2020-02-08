/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar.weapons;

import me.archcst.agameproject.avatar.Player;
import me.archcst.agameproject.datacenter.DataCenter;
import me.archcst.agameproject.util.Target;

import java.awt.*;

public class Weapon_Player extends Weapon {
    public Weapon_Player() {
        speed = 600;
        weaponColor = Color.WHITE;
        loadWeapon();
    }

    private void loadWeapon() {
        imageRight[0] = " ┳一";
        imageRight[1] = "┳一 ";
        imageRight[2] = " ┳一";
        offsetRight.setX(11);
        offsetRight.setY(23);

        imageLeft[0] = "一┳";
        imageLeft[1] = " 一┳";
        imageLeft[2] = "一┳";
        offsetLeft.setX(-22);
        offsetLeft.setY(23);
    }

    @Override
    protected void generateBullet() {
        Bullet bullet = new Bullet(avatar, Target.getInstance().getTargetPoint());
        bullet.initBullet("●", Color.WHITE, -4,4, 10);
        DataCenter.getInstance().playBullets.add(bullet);
    }

    private long lastShootTime = System.currentTimeMillis(); // 上一次射击的时间
    @Override
    public void shoot() {
        long bulletGap = 60000 / speed; // 两发子弹间的间隔时间
        long currentTime = System.currentTimeMillis();
        if (avatar.isAttacking() && currentTime - lastShootTime > bulletGap) {
            generateBullet();
            lastShootTime = System.currentTimeMillis();
        }
    }
}
