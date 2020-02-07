/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar.obstacles;

import me.archcst.agameproject.avatar.Action;

import java.awt.*;

public class Obstacle_Small extends Obstacle {
    public Obstacle_Small() {
        super();
        offset.width = -5;
        offset.height = 6;
        size.width = 29;
        size.height = 44;
        setCollisionBox(1,1,0,0);

        loadAction();
    }

    @Override
    protected void loadAction() {
        String[][] animate = new String[1][4];

        animate[0][0] = "┌─┐";
        animate[0][1] = "│╳│";
        animate[0][2] = "├─┤";
        animate[0][3] = "└─┘";

        actions.put("idle", new Action(animate, Color.WHITE));
    }
}
