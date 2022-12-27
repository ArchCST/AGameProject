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
        size.width = 18; // 绘图宽度
        size.height = 44; // 绘图高度
        walkSpeed = GameSettings.PLAYER_WALK_SPEED; // 移动速度
        refreshRate = GameSettings.AVATAR_REFRESH_RATE; // 动画刷新率
        moveCtrl = new PlayerMoveCtrl(this); // 移动控制器
        weapon = new Weapon_Player(this); // 武器
        hp = 100; // 当前血量
        maxHp = 100; // 最大血量

        loadAction(); // 加载动作动画

        // 初始位置
        location.setX((GameMap.getInstance().getMapSize().width / 2) * GameSettings.BLOCK_SIZE + 10);
        location.setY((GameMap.getInstance().getMapSize().height / 2) * GameSettings.BLOCK_SIZE);

        // 字体修正
        offset.width = 0;
        offset.height = 12;

        // 角色颜色
        color = Color.WHITE;

        // 碰撞箱
        setCollisionBox(1, 0.5, 0, 0.2);
    }

    public void newGame() {
        alive = true;
        hp = 100;
        location.setX((GameMap.getInstance().getMapSize().width / 2) * GameSettings.BLOCK_SIZE + 10);
        location.setY((GameMap.getInstance().getMapSize().height / 2) * GameSettings.BLOCK_SIZE);

        setCollisionBox(1, 0.5, 0, 0.2);
    }

    @Override
    protected void loadAction() {
        // 不动
        String[][] animate = new String[1][3];

        animate[0][0] = " O ";
        animate[0][1] = "/|\\";
        animate[0][2] = "/ \\";

        actions.put("idle", new Action(animate));

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

        actions.put("walk_right", new Action(animate));

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

        actions.put("walk_left", new Action(animate));
    }

    @Override
    public void attack() {
        weapon.shoot();
    }

    private long lastDamageTime = System.currentTimeMillis(); // 上一次受伤的时间
    @Override
    public void changeHp(int amount) {
        if (System.currentTimeMillis() - lastDamageTime > GameSettings.PLAYER_INVINCIBLE_TIME) {
            super.changeHp(amount);
            lastDamageTime = System.currentTimeMillis();
        }

        if (hp == 0) {
            alive = false;
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
