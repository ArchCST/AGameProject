/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.datacenter;

import me.archcst.agameproject.avatar.Monster_Slime;
import me.archcst.agameproject.avatar.Player;
import me.archcst.agameproject.map.GameMap;

import java.awt.*;

public class DataCenter {
    private static DataCenter dataCenter = null;
    private GameMap gameMap = GameMap.getInstance();
    private Player player = Player.getInstance();
    Monster_Slime monster_slime = new Monster_Slime(new Point(48,96));

    public static DataCenter getInstance() {
        if (dataCenter == null) {
            dataCenter = new DataCenter();
        }
        return dataCenter;
    }

    private DataCenter() {}

    public void drawFrame(Graphics g) {
        gameMap.draw(g);
        player.draw(g);
        monster_slime.draw(g);
    }

    public void gameProcess(Graphics g) {
        player.moveCtrl.move(g);
        monster_slime.moveCtrl.move(g);
    }
}
