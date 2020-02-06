/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar;
import java.awt.*;
import java.io.File;

public class Monster_Dragon extends Monster {
    public Monster_Dragon(double zoom, int walkSpeed) {
        super(zoom);
        this.walkSpeed = walkSpeed;

        // 特定怪物的碰撞箱
        setCollisionBox(0.5, 0.3, 0, 0.3);

        loadAvatarMovements(new Point(0, 0),
                new File("src/me/archcst/agameproject/static/img/characters/Monster.png"));

        loadAvatarDie(new Point(144, 0),
                new File("src/me/archcst/agameproject/static/img/characters/Damage3.png"));
    }
}
