/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar;

import me.archcst.agameproject.avatar.weapons.Bullet;
import me.archcst.agameproject.avatar.weapons.Weapon_Player;
import me.archcst.agameproject.datacenter.DataCenter;
import me.archcst.agameproject.datacenter.Framer;
import me.archcst.agameproject.map.GameMap;
import me.archcst.agameproject.map.DPoint;
import me.archcst.agameproject.util.Camera;
import me.archcst.agameproject.util.CollisionBox;
import me.archcst.agameproject.util.GameSettings;
import me.archcst.agameproject.util.Target;

import java.awt.*;
import java.util.Vector;

public class Player extends Avatar {
    private static Player player = null;

    public static Player getInstance() {
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
        size = new Dimension(18, 44);
        walkSpeed = 2;
        refreshRate = GameSettings.AVATAR_REFRESH_RATE;
        alive = true;
        moveCtrl = new PlayerMoveCtrl(this);
        weapon = new Weapon_Player();
        hp = 95;
        maxHp = 100;

        loadAction();
        location = new DPoint(
                (GameMap.getInstance().getMapSize().width / 2) * GameSettings.BLOCK_SIZE + 10,
                (GameMap.getInstance().getMapSize().height / 2) * GameSettings.BLOCK_SIZE);
        offset.width = 0;
        offset.height = 12;

        // 碰撞箱
        setCollisionBox(1, 0.5, 0, 0.2);
//        collisionBox = new CollisionBox(location.x + 13, location.y + 36,
//                location.x + sSize.width - 14, location.y + sSize.height);

    }

    @Override
    protected void loadAction() {
        // 不动
        String[][] animate = new String[1][3];

        animate[0][0] = " O ";
        animate[0][1] = "/|\\";
        animate[0][2] = "/ \\";

        actions.put("idle", new Action(animate, Color.WHITE));

        // 右移
        animate = new String[3][3];

        animate[0][0] = " O ";
        animate[0][1] = "/|\\";
        animate[0][2] = "/ \\";

        animate[1][0] = " O ";
        animate[1][1] = "/|\\";
        animate[1][2] = "/ >";

        animate[2][0] = " O ";
        animate[2][1] = "/|\\";
        animate[2][2] = " >\\";

        actions.put("walk_right", new Action(animate, Color.WHITE));

        // 左移
        animate = new String[3][3];

        animate[0][0] = " O ";
        animate[0][1] = "/|\\";
        animate[0][2] = "/ \\";

        animate[1][0] = " O ";
        animate[1][1] = "/|\\";
        animate[1][2] = "< \\";

        animate[2][0] = " O ";
        animate[2][1] = "/|\\";
        animate[2][2] = "/<";

        actions.put("walk_left", new Action(animate, Color.WHITE));
    }

    @Override
    public void attack() {
        weapon.shoot();
    }

    @Override
    public void changeHp(int amount) {
        super.changeHp(amount);
        if (hp == 0) {
            DataCenter.getInstance().gameOver();
        }
    }

    // 生成玩家受击体，以玩家中心点为中心，宽度13，高度37的方盒子
    public CollisionBox getAttackBox() {
        return new CollisionBox(
                player.getCenter().x() - 6,
                player.getCenter().y() - 18,
                player.getCenter().x() + 6,
                player.getCenter().y() + 18);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        Camera camera = Camera.getInstance();

        // 画武器
        g.setColor(weapon.getWeaponColor());
        int frame = isAttacking() ? Framer.getInstance().getFrame(5) % 3 : 0;
        if (Target.getInstance().getTargetPoint().x() < location.x()) {
            g.drawString(weapon.getImageLeft()[frame],
                    camera.packX(location.x() + weapon.offsetLeft().x()),
                    camera.packY(location.y() + weapon.offsetLeft().y()));
        } else {
            g.drawString(weapon.getImageRight()[frame],
                    camera.packX(location.x() + weapon.offsetRight().x()),
                    camera.packY(location.y() + weapon.offsetRight().y()));
        }

        // 画血量
        drawBlood(g);
    }

    private void drawBlood(Graphics g) {
        String[] blood = new String[5];
        blood[0] = "╭─────────────╮";
        blood[1] = "│　　　　　　　　　　　　　│";
        blood[2] = "│　　　　　　　　　　　　　│";
        blood[3] = "│　　　　　　　　　　　　　│";
        blood[4] = "╰─────────────╯";

        g.setColor(GameSettings.BACKGROUND_COLOR);
        g.fillRect(30, 30, 183, 55);
        for (int i = 0; i < blood.length; i++) {
            g.setColor(Color.WHITE);
            g.drawString(blood[i], 24, 34 + i*14);
        }

        g.drawString("HP:", 39, 50);
        g.drawString("MP:", 39, 75);
        g.drawRect(67, 35, 140, 20);
        g.drawRect(67, 60, 140, 20);

        // 画血量条
        double hpPercent = (double) hp / maxHp;
        g.setColor(Color.WHITE);
        g.fillRect(69, 37, (int) (136 * hpPercent), 16);
    }
}
