/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.datacenter;

import me.archcst.agameproject.avatar.Monster;
import me.archcst.agameproject.avatar.Monster_Slime;
import me.archcst.agameproject.avatar.Player;
import me.archcst.agameproject.map.GameMap;

import java.awt.*;
import java.util.ArrayList;

public class DataCenter {
    private static DataCenter dataCenter = null;
    private GameMap gameMap = GameMap.getInstance();
    private Player player = Player.getInstance();
    private ArrayList<Monster> monsters = new ArrayList<>();

    public static DataCenter getInstance() {
        if (dataCenter == null) {
            dataCenter = new DataCenter();
        }
        return dataCenter;
    }

    private DataCenter() {
        monsters.add(new Monster_Slime(gameMap.randomEmptyBlock()));
        monsters.add(new Monster_Slime(gameMap.randomEmptyBlock()));
        monsters.add(new Monster_Slime(gameMap.randomEmptyBlock()));
        monsters.add(new Monster_Slime(gameMap.randomEmptyBlock()));
    }

    public void drawFrame(Graphics g) {
        gameMap.draw(g);
        player.draw(g);
        for (Monster m:monsters) {
            m.draw(g);
        }
    }

    public void gameProcess(Graphics g) {
        player.moveCtrl.move(g);
        for (Monster m:monsters) {
//            m.moveCtrl.move(g);
        }
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }
}
