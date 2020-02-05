/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar;

import me.archcst.agameproject.map.GameMap;
import me.archcst.agameproject.util.CollisionBox;

import java.awt.*;
import java.io.File;

public class Monster_Slime extends Monster {
    public Monster_Slime(Point location) {
        moveCtrl = new MonsterMoveCtrl(this, 500, 1000);
//        Point mapOffset = GameMap.getInstance().getOffset();
        this.location = location;

        zoom = 1;
        dimension = new Dimension(48, 48);
        currentAction = "walk_down";
        walkSpeed = 1;
        refreshRate = 16;
        alive = true;

        // 碰撞箱
//        setCollisionBox(0.8, 0.3, new Point(0, 5));
        collisionBox = new CollisionBox(location.x + 13, location.y + 30,
                location.x + dimension.width - 14, location.y + dimension.height);

        loadAvatarMovements(new Point(144, 0),
                new File("src/me/archcst/agameproject/static/img/characters/Monster.png"));

        loadAvatarDie(new Point(144, 48),
                new File("src/me/archcst/agameproject/static/img/characters/Damage3.png"));
    }
}
