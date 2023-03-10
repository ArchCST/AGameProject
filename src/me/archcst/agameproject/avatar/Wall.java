/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar;

import me.archcst.agameproject.map.DPoint;
import me.archcst.agameproject.util.CollisionBox;
import me.archcst.agameproject.util.GameSettings;

import java.awt.*;

public class Wall extends Avatar {

    public Wall(int x, int y) {
        this.location = new DPoint(x, y);
        size.width = GameSettings.BLOCK_SIZE;
        size.height = GameSettings.BLOCK_SIZE;

        currentAction = "idle";
        offset.width = 1;
        offset.height = 6;
        alive = true;

        color = Color.LIGHT_GRAY;

        loadAction();

        setCollisionBox(1,1,0,0);

    }

//    public void initLocation(Graphics g) {
//        location = ASCIIMap.getInstance().randomLocation(g,this);
//        collisionBox.boxMove(location);
//    }

    @Override
    protected void loadAction() {
        String[][] animate = new String[1][4];

        animate[0][0] = "┏━┓";
        animate[0][1] = "┃▢┃";
//        animate[0][2] = "┗┻┛";
        animate[0][2] = "┣━┫";
        animate[0][3] = "┗━┛";

        actions.put("idle", new Action(animate));
    }

    @Override
    public void draw(Graphics g) {
//        Camera camera = Camera.getInstance();
//        g.setColor(GameSettings.BACKGROUND_COLOR);
//        g.fillRect(camera.cameraedX(location.x),camera.cameraedY(location.y),
//                sSize.width, sSize.height);
        super.draw(g);
    }

    @Override
    public void attack() {
        System.out.println("Can't shoot");
    }
}
