/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.datacenter;

import me.archcst.agameproject.avatar.*;
import me.archcst.agameproject.avatar.monsters.Monster;
import me.archcst.agameproject.avatar.monsters.MonsterFactory;
import me.archcst.agameproject.map.GameMap;
import me.archcst.agameproject.util.Camera;
import me.archcst.agameproject.util.CollisionBox;
import me.archcst.agameproject.util.GameSettings;
import me.archcst.agameproject.util.Target;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class DataCenter {
    private static DataCenter dataCenter = null;
    private GameMap gameMap;
    private Player player;
    private ArrayList<Monster> monsters;

    public static DataCenter getInstance() {
        if (dataCenter == null) {
            dataCenter = new DataCenter();
        }
        return dataCenter;
    }

    private DataCenter() {
        Random r = GameSettings.r;
        gameMap = GameMap.getInstance();
        player = Player.getInstance();
        monsters = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            monsters.add(MonsterFactory.createMonster(r.nextInt(MonsterFactory.MONSTER_TYPE_AMOUNT)));
//            monsters.add(new Monster_Slime(1, 1));
//            monsters.add(new Monster_Devil(1, 2));
//            monsters.add(new Monster_Dragon(1, 3));
//            monsters.add(new Monster_Orc(1, 2));
        }

        for (Monster m: monsters) {
            m.initLocation();
        }
    }

    public void drawFrame(Graphics g) {
        Camera.getInstance().updateCamera();
        Target target = Target.getInstance();
        gameMap.draw(g);
        for (Monster m:monsters) {
            m.draw(g);
        }
        player.draw(g);
        target.draw(g);

        // 画出地图所有碰撞箱
        if (GameSettings.DEV_MODE && GameSettings.DEV_SHOW_MAP_COLLISION_BOX) {
            for (CollisionBox cb:gameMap.getAllMapCollisionBox()) {
                cb.draw(g);
            }
        }
    }

    public void gameProcess(Graphics g) {
        player.moveCtrl.move(g);
        for (Monster m:monsters) {
            m.moveCtrl.move(g);
        }
        Target.getInstance().refreshTarget();
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }
}
