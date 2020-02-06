/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.datacenter;

import me.archcst.agameproject.avatar.*;
import me.archcst.agameproject.map.GameMap;
import me.archcst.agameproject.util.Camera;

import java.awt.*;
import java.util.ArrayList;

public class DataCenter {
    private static DataCenter dataCenter = null;
    private GameMap gameMap;
    private Player player;
    private ArrayList<Monster> monsters;

    public static DataCenter getInstance(Graphics g) {
        if (dataCenter == null) {
            dataCenter = new DataCenter(g);
        }
        return dataCenter;
    }

    private DataCenter(Graphics g) {
        gameMap = GameMap.getInstance();
        player = Player.getInstance();
        monsters = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            monsters.add(new Monster_Slime(1, 1));
            monsters.add(new Monster_Devil(1, 2));
            monsters.add(new Monster_Dragon(1, 3));
            monsters.add(new Monster_Orc(1, 2));
        }

        for (Monster m: monsters) {
            m.initLocation(g);
        }
    }

    public void drawFrame(Graphics g) {
        Camera.getInstance().updateCamera();
        gameMap.draw(g);
        player.draw(g);
        for (Monster m:monsters) {
            m.draw(g);
        }
    }

    public void gameProcess(Graphics g) {
        player.moveCtrl.move(g);
        for (Monster m:monsters) {
            m.moveCtrl.move(g);
        }
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }
}
