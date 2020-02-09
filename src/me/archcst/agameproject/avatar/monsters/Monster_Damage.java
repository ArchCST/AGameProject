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
 * 根据等级：
 *     单发子弹伤害增加
 *     子弹移动速度略微变快
 *     移动速度稍微变快
 */
public class Monster_Damage extends Monster {
    public Monster_Damage(int level) {
        super(level);
        size.width = 40;
        size.height = 34;
        offset.width = -6;
        offset.height = 10;
        setCollisionBox(1, 0.9, 0, 0.1);
        loadAction();

        // 怪物差异性
        walkSpeed = 1 + level * 0.5;
        maxHp = 100;
        hp = maxHp;
        crashDamage = -10;

        weapon = new Weapon_Monster(this,
                120, // 每分钟子弹数，默认120
                level*0.5 + 1.5, // 子弹移动距离（速度），默认3
                3, // 单次射击子弹个数，默认3
                5 * level * level // 单发子弹伤害，默认5
        );
    }

    @Override
    protected void loadAction() {
        // 不动
        String[][] animate = new String[1][3];

        animate[0][0] = "   ◜  ◝";
        animate[0][1] = "（⦾  ⦾）";
        animate[0][2] = "     ˇ";

        actions.put("idle", new Action(animate));

        // 右移
        animate = new String[2][3];

        animate[0][0] = "    ◜  ◝";
        animate[0][1] = "（ ⦾ ⦾）";
        animate[0][2] = "      ◦";

        animate[1][0] = "   ◜  ◝";
        animate[1][1] = "（ ⦾ ⦾）";
        animate[1][2] = "     ◦";

        actions.put("walk_right", new Action(animate));

        // 左移
        animate = new String[2][3];

        animate[0][0] = " ◜  ◝";
        animate[0][1] = "（⦾ ⦾ ）";
        animate[0][2] = "    ◦";

        animate[1][0] = "  ◜  ◝";
        animate[1][1] = "（⦾ ⦾ ）";
        animate[1][2] = "     ◦";

        actions.put("walk_left", new Action(animate));
    }
}
