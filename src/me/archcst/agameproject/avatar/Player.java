/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar;

import me.archcst.agameproject.datacenter.Framer;
import me.archcst.agameproject.ui.GamePanel;
import me.archcst.agameproject.util.CollisionBox;
import me.archcst.agameproject.util.GameSettings;

import java.awt.*;
import java.io.File;

public class Player extends Avatar {
    private static Player player = null;

    public static Player getInstance(){
        if (player == null) {
            synchronized (Player.class) {
                if (player == null) {
                    player = new Player();
                }
            }
        }
        return player;
    }

    private Player() {
        zoom = 1;
        dimension = new Dimension(48, 48);
        currentAction = "walk_down";
        walkSpeed = 1;
        refreshRate = 0;
        alive = true;
        moveCtrl = new PlayerMoveCtrl(this);

        loadAvatarMovements(new Point(288, 0),
                new File("src/me/archcst/agameproject/static/img/characters/Actor1.png"));

        loadAvatarDie(new Point(0, 96),
                new File("src/me/archcst/agameproject/static/img/characters/Damage1.png"));

        // 坐标（屏幕正中）
        location = new Point((GameSettings.GAME_WIDTH - dimension.width) /2,
                (GameSettings.GAME_HEIGHT - dimension.height) / 2);

        // 碰撞箱
        collisionBox = new CollisionBox(location.x + 13, location.y + 36,
                location.x + dimension.width - 14, location.y + dimension.height);

    }

    @Override
    public void draw(Graphics g) {
        Action action = actions.get(currentAction);

        int frame = Framer.getInstance().getFrame(refreshRate) % action.getFrames();
        g.drawImage(action.getImage(),
                location.x, location.y,
                location.x + (int) (dimension.width * zoom), location.y + (int) (dimension.height * zoom),
                action.sx1(frame), action.sy1(frame),
                action.sx2(frame), action.sy2(frame),
                GamePanel.getInstance());

        if (GameSettings.DEV_MODE && GameSettings.DEV_SHOW_AVATAR_COLLISION_BOX) {
            g.setColor(Color.GREEN);
            g.drawRect(collisionBox.x1, collisionBox.y1,
                    collisionBox.width, collisionBox.height);
        }

    }
}
