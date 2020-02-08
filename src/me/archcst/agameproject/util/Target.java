/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.util;

import me.archcst.agameproject.avatar.Avatar;
import me.archcst.agameproject.avatar.Player;
import me.archcst.agameproject.avatar.monsters.Monster;
import me.archcst.agameproject.datacenter.DataCenter;
import me.archcst.agameproject.map.GameMap;
import me.archcst.agameproject.map.DPoint;

import java.awt.*;
import java.util.ArrayList;

public class Target {
    private static Target target = null;
    private static Monster targetMonster;
    DPoint targetPoint;

    private Target() {
        targetPoint = new DPoint();
        if (DataCenter.getInstance().getMonsters().size() > 0) {
            refreshTarget();
        }
    }

    public static Target getInstance() {
        if (target == null) {
            target = new Target();
        }
        return target;
    }

    /**
     * 找到传入的数组中距离玩家最近的角色，并设置其中心点为当前目标点
     */
    public void refreshTarget() {
        ArrayList<Monster> monstersInsight = new ArrayList<>();
        // 所有视野中的怪
        for (Monster monster : DataCenter.getInstance().getMonsters()) {
            if (Insight(monster)) {
                monstersInsight.add(monster);
            }
        }

        // 设定距离最短的怪为目标怪，如果视野中无怪则不更新目标怪
        if (monstersInsight.size() > 0) {
            targetMonster = monstersInsight.get(0);
            for (Monster m :monstersInsight) {
                if (distanceToPlayer(m) < distanceToPlayer(targetMonster)) {
                    targetMonster = m;
                }
            }

        }

        targetPoint.setX(targetMonster.getLocation().x() + targetMonster.getSize().width / 2);
        targetPoint.setY(targetMonster.getLocation().y() + targetMonster.getSize().height / 2);
    }

    /**
     * 计算玩家距离目标之间的距离，通过绘图矩形进行判断
     *
     * @param target 目标物体
     * @return 两物体之间的距离
     */
    private double distanceToPlayer(Avatar target) {
        Player player = Player.getInstance();
        double px = player.getLocation().x() + player.getSize().width / 2;
        double py = player.getLocation().y() + player.getSize().height / 2;
        double tx = target.getLocation().x() + target.getSize().width / 2;
        double ty = target.getLocation().y() + target.getSize().height / 2;

        return Math.sqrt((px - tx) * (px - tx) + (py - ty) * (py - ty));
    }

    // 判断怪物在不在视野中
    private boolean Insight(Avatar avatar) {
        Player player = Player.getInstance();
        double px = player.getLocation().x() + player.getSize().width / 2;
        double py = player.getLocation().y() + player.getSize().height / 2;
        double tx = avatar.getLocation().x() + avatar.getSize().width / 2;
        double ty = avatar.getLocation().y() + avatar.getSize().height / 2;
        ArrayList<CollisionBox> mc = GameMap.getInstance().getAllMapCollisionBox();
        for (CollisionBox cb : mc) {
            if (cb.coverLine(px, py, tx, ty)) {
                return false;
            }
        }
        return true;
    }

    // 画准星
    public void draw(Graphics g) {
        if (GameSettings.SHOW_AIMING) {
            Camera camera = Camera.getInstance();
            double drawX = targetPoint.x() - GameSettings.FONT_SIZE / 2 - GameSettings.FONT_SIZE;
            double drawY1 = targetPoint.y() - GameSettings.FONT_SIZE / 2;
            double drawY2 = targetPoint.y() - GameSettings.FONT_SIZE / 2 + GameSettings.FONT_SIZE;
            double drawY3 = targetPoint.y() - GameSettings.FONT_SIZE / 2 + GameSettings.FONT_SIZE * 2;
            g.setColor(Color.RED);
            g.drawString("┏┳┓", camera.packX(drawX), camera.packY(drawY1));
            g.drawString("┣　┫", camera.packX(drawX), camera.packY(drawY2));
            g.drawString("┗┻┛", camera.packX(drawX), camera.packY(drawY3));
        }
    }

    public DPoint getTargetPoint() {
        return targetPoint;
    }
}
