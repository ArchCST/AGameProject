/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar.monsters;

import me.archcst.agameproject.avatar.Avatar;
import me.archcst.agameproject.avatar.MonsterMoveCtrl;
import me.archcst.agameproject.avatar.weapons.Bullet;
import me.archcst.agameproject.avatar.weapons.Weapon_Monster;
import me.archcst.agameproject.map.GameMap;
import me.archcst.agameproject.map.DPoint;
import me.archcst.agameproject.util.Camera;
import me.archcst.agameproject.util.CollisionBox;
import me.archcst.agameproject.util.GameSettings;

import java.awt.*;
import java.util.Vector;

public abstract class Monster extends Avatar {

    // 所有Monster子类的默认属性
    protected Monster(double zoom) {

        location = new DPoint();
        moveCtrl = new MonsterMoveCtrl(this);
        size = new Dimension();
        collisionBox = new CollisionBox();
        this.zoom = zoom;
        alive = true;
        weapon = new Weapon_Monster();
        hp = 100;
        maxHp = 100;
    }

    public void initLocation() {
        location = GameMap.getInstance().randomLocation(collisionBox);
        collisionBox.boxMove(location);
    }

    @Override
    public void attack() {
        weapon.shoot();
    }

    public void die() {
    }


    @Override
    public void draw(Graphics g) {
        super.draw(g);
        drawBlood(g);
    }

    private void drawBlood(Graphics g) {
        Camera camera = Camera.getInstance();
        int x = camera.packX(getCenter().x() - 20);
        int y = camera.packY(location.y() - 20);

        // 画外框
        g.setColor(GameSettings.BACKGROUND_COLOR);
        g.fillRect(x, y, 40, 8);
        g.setColor(Color.LIGHT_GRAY);
        g.drawRect(x, y, 40, 8);

//         画血量条
        double hpPercent = (double) hp / maxHp;
        g.fillRect(x + 2, y+2, (int) (36 * hpPercent), 4);
    }

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
