/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar;

import me.archcst.agameproject.map.GameMap;
import me.archcst.agameproject.util.CollisionBox;

import java.awt.*;
import java.util.Random;

public class MonsterMoveCtrl extends MoveCtrl{
    private long startTime = System.currentTimeMillis();
    private long randomTime;
    private long maxMoveTime; // 怪物单次移动的最长时间
    private long minMoveTime; // 怪物单次移动的最短时间
    private Random r = new Random();

    public MonsterMoveCtrl(Avatar avatar, long minMoveTime, long maxMoveTime) {
        super(avatar);
        this.minMoveTime = Math.min(minMoveTime, maxMoveTime);
        this.maxMoveTime = Math.max(minMoveTime, maxMoveTime);
        randomTime();
    }

    /**
     * 怪物的移动
     * @param g 测试用画笔
     */
    public void move(Graphics g) {
        if (System.currentTimeMillis() - startTime < randomTime) {
            validateAndMove(g);
        } else {
            changeDirection();
            randomTime();
            startTime = System.currentTimeMillis();
        }
    }

    /**
     * 怪物的urdl控制，同时设置面朝方向
     */
    private void changeDirection() {
        clearDirection();
        int mainDirection = r.nextInt(4); // 主方向，也是面朝方向
        // 副方向基于主方向的偏差，true为 1，false为 -1, 无副方向为0
        int viceDirection = 0;
        if (r.nextBoolean()) {
            viceDirection = r.nextBoolean()? 1 : -1;
        }

        viceDirection += mainDirection;

        if (viceDirection > urdl.length - 1) viceDirection = 0;
        if (viceDirection < 0) viceDirection = urdl.length - 1;

        urdl[mainDirection] = true;
        urdl[viceDirection] = true;

        // 设置角色方向
        String direction = "";
        if (mainDirection == 0) direction = "up";
        if (mainDirection == 1) direction = "right";
        if (mainDirection == 2) direction = "down";
        if (mainDirection == 3) direction = "left";

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
        randomTime = r.nextInt((int) (maxMoveTime - minMoveTime)) + minMoveTime;
    }

}
