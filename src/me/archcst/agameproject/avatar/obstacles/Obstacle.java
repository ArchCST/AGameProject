/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar.obstacles;

import me.archcst.agameproject.avatar.Action;
import me.archcst.agameproject.avatar.Avatar;
import me.archcst.agameproject.map.GameMap;
import me.archcst.agameproject.util.CollisionBox;
import me.archcst.agameproject.util.GameSettings;

import java.awt.*;

public abstract class Obstacle extends Avatar {

    public Obstacle() {
        zoom = 1;
        this.location = new Point();
        alive = true;
        size = new Dimension();
        currentAction = "idle";

    }
}
