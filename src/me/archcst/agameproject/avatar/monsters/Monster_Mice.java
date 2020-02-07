/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar.monsters;

import me.archcst.agameproject.avatar.Action;

import java.awt.*;

public class Monster_Mice extends Monster {
    public Monster_Mice(double zoom, int walkSpeed) {
        super(zoom);
        this.walkSpeed = walkSpeed;
        size.width = 60;
        size.height = 30;
        offset.width = -6;
        offset.height = 3;

        // 特定怪物的碰撞箱
        setCollisionBox(0.8, 0.8, 0, 0.1);

        loadAction();
    }

    @Override
    protected void loadAction() {
        // 不动
        String[][] animate = new String[1][3];

        animate[0][0] = "　︵___︵";
        animate[0][1] = "（ ◎　◎ ）";
        animate[0][2] = "　^^　^^";

        actions.put("idle", new Action(animate, Color.WHITE));

        // 右移
        animate = new String[2][3];

        animate[0][0] = "　︵__︵";
        animate[0][1] = "（　◎ ◎）";
        animate[0][2] = "　>>　>>";

        animate[1][0] = "　︵__︵";
        animate[1][1] = "（　◎ ◎）";
        animate[1][2] = " 　>> >>";

        actions.put("walk_right", new Action(animate, Color.WHITE));

        // 左移
        animate = new String[2][3];
        animate[0][0] = "　︵__︵";
        animate[0][1] = "（◎ ◎　）";
        animate[0][2] = " << <<";

        animate[1][0] = "　︵__︵";
        animate[1][1] = "（◎ ◎　）";
        animate[1][2] = "<<　<<";

        actions.put("walk_left", new Action(animate, Color.WHITE));
    }
}
