/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar.obstacles;

import me.archcst.agameproject.avatar.Action;

import java.awt.*;

public class Obstacle_M_2 extends Obstacle {
    public Obstacle_M_2() {
        super();
        offset.width = -5;
        offset.height = 6;
        size.width = 55;
        size.height = 68;
        setCollisionBox(1,1,0,0);

        loadAction();
    }

    @Override
    protected void loadAction() {
        String[][] animate = new String[1][6];

        animate[0][0] = "┌───┐";
        animate[0][1] = "│╲　╱│";
        animate[0][2] = "│　╳　│";
        animate[0][3] = "│╱　╲│";
        animate[0][4] = "├───┤";
        animate[0][5] = "└───┘";

        actions.put("idle", new Action(animate));
    }
}
