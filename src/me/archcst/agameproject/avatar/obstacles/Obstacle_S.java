/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar.obstacles;

import me.archcst.agameproject.avatar.Action;
import me.archcst.agameproject.util.GameSettings;

import java.awt.*;
import java.util.Random;

public class Obstacle_S extends Obstacle {
    public Obstacle_S() {
        super();
        offset.width = -5;
        offset.height = 6;
        size.width = 29;
        size.height = 42;
        setCollisionBox(1,1,0,0);

        loadAction();
    }

    @Override
    protected void loadAction() {
        Random r = GameSettings.r;
        String[] middles = new String[]{"┼","╳","〇"};
        String[][] animate = new String[1][4];

        animate[0][0] = "┌─┐";
        animate[0][1] = "│"+middles[r.nextInt(middles.length)]+"│";
        animate[0][2] = "├─┤";
        animate[0][3] = "└─┘";

        actions.put("idle", new Action(animate, Color.WHITE));
    }
}
