/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar;

import me.archcst.agameproject.avatar.weapons.Weapon;
import me.archcst.agameproject.avatar.weapons.Weapon_Monster;
import me.archcst.agameproject.util.GameSettings;

import java.awt.*;
import java.util.Random;

public class MonsterMoveCtrl extends MoveCtrl {
    private long lastMoveTime = System.currentTimeMillis(); // 上一次移动的时间
    private long moveDuration; // 本次移动的时长，为一个随机值
    private Random r = GameSettings.r;

    public MonsterMoveCtrl(Avatar avatar) {
        super(avatar);
        randomTime();
    }

    /**
     * 怪物的移动
     */
    public void move(Graphics g) {
        if (System.currentTimeMillis() - lastMoveTime < moveDuration) {
            validateAndMove(g);
        } else { // 超过单次移动的时长执行，只执行一次
            changeDirection();
            randomShoot();
            randomTime();
            lastMoveTime = System.currentTimeMillis();
        }
    }

    /**
     * 怪物的urdl控制，同时设置面朝方向
     */
    private void changeDirection() {
        clearDirection();

        // 怪物待机
        if (r.nextDouble() < GameSettings.MONSTER_IDLE_CHANCE) {
            avatar.currentAction = "idle";
            return;
        }

        // 怪物移动
        int mainDirection = r.nextInt(4); // 主方向，也是面朝方向
        // 副方向基于主方向的偏差，true为 1，false为 -1, 无副方向为0
        int viceDirection = 0;
        if (r.nextBoolean()) {
            viceDirection = r.nextBoolean() ? 1 : -1;
        }

        viceDirection += mainDirection;

        if (viceDirection > urdl.length - 1) viceDirection = 0;
        if (viceDirection < 0) viceDirection = urdl.length - 1;

        // 设置角色方向
        String direction = "";
        if (mainDirection == 0) direction = "up";
        if (mainDirection == 1) direction = "right";
        if (mainDirection == 2) direction = "down";
        if (mainDirection == 3) direction = "left";

        setDirection(direction, true);

        if (viceDirection == 0) direction = "up";
        if (viceDirection == 1) direction = "right";
        if (viceDirection == 2) direction = "down";
        if (viceDirection == 3) direction = "left";
        setDirection(direction, true);
    }

    private void clearDirection() {
        urdl[0] = false;
        urdl[1] = false;
        urdl[2] = false;
        urdl[3] = false;
    }

    // 角色单次随机移动的时长
    private void randomTime() {
        moveDuration = r.nextInt((int) (GameSettings.MONSTER_MAX_MOVE_TIME - GameSettings.MONSTER_MIN_MOVE_TIME)) + GameSettings.MONSTER_MIN_MOVE_TIME;
    }


    // 随机射击
    private long lastShootTime = System.currentTimeMillis();
    public void randomShoot() {
        Random r = GameSettings.r;
        // 上次射击至少 5000ms后 才能再次射击
        if (System.currentTimeMillis() - lastShootTime < 5000) {
            return;
        }

        if (r.nextDouble() < GameSettings.MONSTER_ATTACK_CHANCE) {
            Weapon weapon = avatar.getWeapon();
            if (weapon instanceof Weapon_Monster) {
                Weapon_Monster w = (Weapon_Monster) weapon;
                int bulletAmount = r.nextInt(2) + 3;
//                System.out.println(bulletAmount);
                w.setBulletAmount(bulletAmount);
                lastShootTime = System.currentTimeMillis();
            }
        }
    }

}
