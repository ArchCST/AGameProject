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

import java.awt.*;
import java.util.ArrayList;

public class Target {
    private static Target target = null;
    Point targetPoint;
    private Target() {
        targetPoint = new Point();
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
        ArrayList<Monster> monsters = DataCenter.getInstance().getMonsters();
        Monster targetMonster = monsters.get(0);
        for (Monster m:monsters) {
            if (distanceToPlayer(m) < distanceToPlayer(targetMonster)) {
                targetMonster = m;
            }
        }

        targetPoint.x = targetMonster.getLocation().x + targetMonster.getSize().width / 2;
        targetPoint.y = targetMonster.getLocation().y + targetMonster.getSize().height / 2;
    }

    /**
     * 计算玩家距离目标之间的距离，通过绘图矩形进行判断
     *
     * @param target 目标物体
     * @return 两物体之间的距离
     */
    private double distanceToPlayer(Avatar target) {
        Player player = Player.getInstance();
        int px = player.getLocation().x + player.getSize().width / 2;
        int py = player.getLocation().y + player.getSize().height / 2;
        int tx = target.getLocation().x + target.getSize().width / 2;
        int ty = target.getLocation().y + target.getSize().height / 2;

        return Math.sqrt((px - tx) * (px - tx) + (py - ty) * (py - ty));
    }

    public void draw(Graphics g) {
        Camera camera = Camera.getInstance();
        int drawX = targetPoint.x - GameSettings.FONT_SIZE / 2 - GameSettings.FONT_SIZE;
        int drawY1 = targetPoint.y - GameSettings.FONT_SIZE / 2;
        int drawY2 = targetPoint.y - GameSettings.FONT_SIZE / 2 + GameSettings.FONT_SIZE;
        int drawY3 = targetPoint.y - GameSettings.FONT_SIZE / 2 + GameSettings.FONT_SIZE * 2;
        g.setColor(Color.RED);
        g.drawString("┏┳┓", camera.packX(drawX), camera.packY(drawY1));
        g.drawString("┣　┫", camera.packX(drawX), camera.packY(drawY2));
        g.drawString("┗┻┛", camera.packX(drawX), camera.packY(drawY3));

    }
}
