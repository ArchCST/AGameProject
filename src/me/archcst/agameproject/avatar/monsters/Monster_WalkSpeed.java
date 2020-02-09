/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar.monsters;

import me.archcst.agameproject.avatar.Action;
import me.archcst.agameproject.avatar.weapons.Weapon_Monster;

import java.awt.*;

/**
 * 无武器怪
 * 根据等级：
 *     移动速度增加
 *     血量增加
 *     todo 碰撞伤害增加
 */
public class Monster_WalkSpeed extends Monster {
    public Monster_WalkSpeed(int level) {
        super(level);
        size.width = 60;
        size.height = 30;
        offset.width = -6;
        offset.height = 3;
        setCollisionBox(0.8, 0.8, 0, 0.1);
        loadAction();

        // 怪物差异性
        walkSpeed = level;
        maxHp = level * 100;
        hp = maxHp;
        crashDamage = -10 * level;
    }

    @Override
    protected void loadAction() {
        // 不动
        String[][] animate = new String[1][3];

        animate[0][0] = "　︵___︵";
        animate[0][1] = "（ ◎　◎ ）";
        animate[0][2] = "　^^　^^";

        actions.put("idle", new Action(animate));

        // 右移
        animate = new String[2][3];

        animate[0][0] = "　︵__︵";
        animate[0][1] = "（　◎ ◎）";
        animate[0][2] = "　>>　>>";

        animate[1][0] = "　︵__︵";
        animate[1][1] = "（　◎ ◎）";
        animate[1][2] = " 　>> >>";

        actions.put("walk_right", new Action(animate));

        // 左移
        animate = new String[2][3];
        animate[0][0] = "　︵__︵";
        animate[0][1] = "（◎ ◎　）";
        animate[0][2] = " << <<";

        animate[1][0] = "　︵__︵";
        animate[1][1] = "（◎ ◎　）";
        animate[1][2] = "<<　<<";

        actions.put("walk_left", new Action(animate));
    }
}
