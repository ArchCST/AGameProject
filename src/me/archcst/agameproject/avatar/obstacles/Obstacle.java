/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar.obstacles;

import me.archcst.agameproject.avatar.Avatar;
import me.archcst.agameproject.map.DPoint;

import java.awt.*;

public abstract class Obstacle extends Avatar {

    public Obstacle() {
        zoom = 1;
        this.location = new DPoint();
        alive = true;
        size = new Dimension();
        currentAction = "idle";
    }

    @Override
    public void attack() {
        System.out.println("无法射击");
    }
}
