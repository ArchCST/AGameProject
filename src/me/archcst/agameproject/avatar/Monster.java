/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar;

import me.archcst.agameproject.datacenter.Framer;
import me.archcst.agameproject.map.GameMap;
import me.archcst.agameproject.ui.GamePanel;
import me.archcst.agameproject.util.GameSettings;

import java.awt.*;

public abstract class Monster extends Avatar {
//    Point mapOffset = GameMap.getInstance().getOffset();
//    public void draw(Graphics g) {
//        Action action = actions.get(currentAction);
//
//        int frame = Framer.getInstance().getFrame(refreshRate) % action.getFrames();
//        g.drawImage(action.getImage(),
//                location.x, location.y,
//                location.x + (int) (dimension.width * zoom), location.y + (int) (dimension.height * zoom),
//                action.sx1(frame), action.sy1(frame),
//                action.sx2(frame), action.sy2(frame),
//                GamePanel.getInstance());
//
//        if (GameSettings.DEV_MODE && GameSettings.DEV_SHOW_AVATAR_COLLISION_BOX) {
//            g.setColor(Color.GREEN);
//            g.drawRect(collisionBox.x1, collisionBox.y1,
//                    collisionBox.width, collisionBox.height);
//        }
//    }
}
