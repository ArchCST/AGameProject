/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar.monsters;

import me.archcst.agameproject.avatar.Action;

import java.awt.*;

public class Monster_Face extends Monster {
    public Monster_Face(double zoom, int walkSpeed) {
        super(zoom);
        this.walkSpeed = walkSpeed;
        size.width = 44;
        size.height = 30;
        offset.width = 1;
        offset.height = 10;

        // 特定怪物的碰撞箱
        setCollisionBox(1, 0.9, 0, 0.1);

        loadAction();
    }

    @Override
    protected void loadAction() {
        // 不动
        String[][] animate = new String[1][3];

        animate[0][0] = "  ◜'''◝";
        animate[0][1] = "(๑⁼̴̀д⁼̴́๑)";
        animate[0][2] = " ︶ ︶";

        actions.put("idle", new Action(animate, Color.WHITE));

        // 右移
        animate = new String[2][3];

        animate[0][0] = "　 ◜''◝";
        animate[0][1] = " ( ๑⁼̴̀д⁼̴́)";
        animate[0][2] = " 　﹋﹋";

        animate[1][0] = "　 ◜''◝";
        animate[1][1] = " ( ๑⁼̴̀д⁼̴́)";
        animate[1][2] = "　﹋﹋";

        actions.put("walk_right", new Action(animate, Color.WHITE));

        // 左移
        animate = new String[2][3];

        animate[0][0] = " ◜''◝";
        animate[0][1] = "(⁼̴̀д⁼̴́๑ )";
        animate[0][2] = "　﹋﹋";

        animate[1][0] = " ◜''◝";
        animate[1][1] = "(⁼̴̀д⁼̴́๑ )";
        animate[1][2] = " ﹋﹋";

        actions.put("walk_left", new Action(animate, Color.WHITE));
    }
}
