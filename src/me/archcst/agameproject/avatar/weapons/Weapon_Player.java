/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar.weapons;

import me.archcst.agameproject.avatar.Player;
import me.archcst.agameproject.datacenter.DataCenter;
import me.archcst.agameproject.util.GameSettings;
import me.archcst.agameproject.util.Target;

import java.awt.*;
import java.util.Random;

public class Weapon_Player extends Weapon {
    public Weapon_Player() {
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

        // 武器数值设定
        damage = 5; // 不暴击的子弹伤害
        speed = 600; // 射速，每分钟子弹伤害
        crd = 3; // 暴击伤害倍率
        cri = 0.1; // 暴击几率
    }

    @Override
    protected void generateBullet() {
        Random r = GameSettings.r; // 判断是否暴击用
        // 生成子弹，固定子弹伤害
        int bulletDamage = (int) (r.nextDouble() < cri ? damage * crd : damage);
        Bullet bullet = new Bullet(avatar, Target.getInstance().getTargetPoint(), bulletDamage);
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
