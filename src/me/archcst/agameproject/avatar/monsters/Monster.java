/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar.monsters;

import me.archcst.agameproject.avatar.Avatar;
import me.archcst.agameproject.avatar.MonsterMoveCtrl;
import me.archcst.agameproject.map.GameMap;
import me.archcst.agameproject.util.Camera;
import me.archcst.agameproject.util.GameSettings;

import java.awt.*;

public abstract class Monster extends Avatar {
    protected int level; // 怪物等级
    protected int crashDamage; // 碰撞伤害
    protected int value; // 击杀分数

    // 所有Monster子类的默认属性
    protected Monster(int level) {
        super();

        level = Math.min(level, 5); // 最高等级为5级
        level = Math.max(level, 1); // 最低等级为1级
        this.level = level;
        initColor(level); // 根据等级设置颜色
        value = level*level; // 怪物分数为等级的平方

        moveCtrl = new MonsterMoveCtrl(this); // 移动控制器
    }

    public void initLocation() {
        location = GameMap.getInstance().randomLocation(collisionBox);
        collisionBox.boxMove(location);
    }

    public void initColor(int level) {
        level = Math.min(level, 5); // 最高等级为5级
        level = Math.max(level, 1); // 最低等级为1级
        switch (level) {
            case 1:
                color = Color.WHITE;
                break;
            case 2:
                color = new Color(30, 255, 0);
                break;
            case 3:
                color = new Color(0, 112, 221);
                break;
            case 4:
                color = new Color(163, 53, 238);
                break;
            case 5:
                color = new Color(255, 128, 0);
                break;
        }
    }

    @Override
    public void attack() {
        if (weapon != null) {
            weapon.shoot();
        }
    }

    public void die() {
    }


    @Override
    public void draw(Graphics g) {
        super.draw(g);
        drawBlood(g);
    }

    private void drawBlood(Graphics g) {
        Camera camera = Camera.getInstance();
        int x = camera.packX(getCenter().x() - 20);
        int y = camera.packY(location.y() - 20);

        // 画外框
        g.setColor(GameSettings.BACKGROUND_COLOR);
        g.fillRect(x, y, 40, 8);
        g.setColor(Color.LIGHT_GRAY);
        g.drawRect(x, y, 40, 8);

//         画血量条
        double hpPercent = (double) hp / maxHp;
        g.fillRect(x + 2, y+2, (int) (36 * hpPercent), 4);
    }

    public int getCrashDamage() {
        return crashDamage;
    }

    public int getValue() {
        return value;
    }

    public int getLevel() {
        return level;
    }
}
