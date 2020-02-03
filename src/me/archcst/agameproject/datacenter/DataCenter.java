/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.datacenter;

import me.archcst.agameproject.avatar.Player;
import me.archcst.agameproject.map.GameMap;

import java.awt.*;

public class DataCenter {
    private static DataCenter dataCenter = null;
    private PlayerController playerController = PlayerController.getInstance();
    private GameMap gameMap = GameMap.getInstance();
    private Player player = Player.getInstance();
    private Framer framer = Framer.getInstance();

    public static DataCenter getInstance() {
        if (dataCenter == null) {
            dataCenter = new DataCenter();
        }
        return dataCenter;
    }

    private DataCenter() {}

    public void drawFrame(Graphics g) {

        gameMap.draw(g);
        player.act(g, player.getActionString(), framer.getFrame(player.getRefreshRate()));
    }

    public void gameProcess(Graphics g) {
        playerController.playerMoving(g);
    }


}
