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
import me.archcst.agameproject.util.Camera;

import java.awt.*;
import java.util.ArrayList;

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
        gameMap = GameMap.getInstance();
        player = Player.getInstance();
        monsters = new ArrayList<>();
        monsters.add(new Monster_Slime(gameMap.randomEmptyBlock()));
        monsters.add(new Monster_Slime(gameMap.randomEmptyBlock()));
        monsters.add(new Monster_Slime(gameMap.randomEmptyBlock()));
        monsters.add(new Monster_Slime(gameMap.randomEmptyBlock()));
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
