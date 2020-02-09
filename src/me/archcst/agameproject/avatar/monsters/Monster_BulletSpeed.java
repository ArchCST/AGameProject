/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar.monsters;

import me.archcst.agameproject.avatar.Action;
import me.archcst.agameproject.avatar.weapons.Weapon;
import me.archcst.agameproject.avatar.weapons.Weapon_Monster;

import java.awt.*;

/**
 * 根据等级：
 *     射速增加
 *     子弹移动速度增加
 *     单次射击子弹个数增加
 */
public class Monster_BulletSpeed extends Monster {
    public Monster_BulletSpeed(int level) {
        super(level);
        size.width = 44;
        size.height = 30;
        offset.width = 1;
        offset.height = 10;
        setCollisionBox(1, 0.9, 0, 0.1);
        loadAction();

        // 怪物差异性
        walkSpeed = 1;
        hp = 100;
        maxHp = 100;
        crashDamage = -10;

        weapon = new Weapon_Monster(this,
                100 + level * level * 20, // 每分钟子弹数，默认120
                level + 2, // 子弹移动距离（速度），默认3
                3 * level, // 单次射击子弹个数，默认3
                5 // 单发子弹伤害，默认5
        );
    }

    @Override
    protected void loadAction() {
        // 不动
        String[][] animate = new String[1][3];

        animate[0][0] = "  ◜'''◝";
        animate[0][1] = "(๑⁼̴̀д⁼̴́๑)";
        animate[0][2] = " ︶ ︶";

        actions.put("idle", new Action(animate));

        // 右移
        animate = new String[2][3];

        animate[0][0] = "　 ◜''◝";
        animate[0][1] = " ( ๑⁼̴̀д⁼̴́)";
        animate[0][2] = " 　﹋﹋";

        animate[1][0] = "　 ◜''◝";
        animate[1][1] = " ( ๑⁼̴̀д⁼̴́)";
        animate[1][2] = "　﹋﹋";

        actions.put("walk_right", new Action(animate));

        // 左移
        animate = new String[2][3];

        animate[0][0] = " ◜''◝";
        animate[0][1] = "(⁼̴̀д⁼̴́๑ )";
        animate[0][2] = "　﹋﹋";

        animate[1][0] = " ◜''◝";
        animate[1][1] = "(⁼̴̀д⁼̴́๑ )";
        animate[1][2] = " ﹋﹋";

        actions.put("walk_left", new Action(animate));
    }
}
