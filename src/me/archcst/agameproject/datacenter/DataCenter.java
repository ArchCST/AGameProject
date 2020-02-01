/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.datacenter;

import me.archcst.agameproject.avatar.Player;
import me.archcst.agameproject.ui.GameFrame;
import me.archcst.agameproject.util.GameLoader;

import javax.swing.*;
import java.awt.*;

public class DataCenter {
    private static DataCenter dataCenter = null;

    public static DataCenter getInstance() {
        if (dataCenter == null) {
            dataCenter = new DataCenter();
        }
        return dataCenter;
    }

    private DataCenter() {}

    public void drawFrame(Graphics g) {
        Player player = Player.getInstance();
        Framer framer = Framer.getInstance();
        player.act(g, player.getActionString(), framer.getFrame(player.getRefreshRate()));
    }

    public void gameProcess() {
        PlayerController playerController = PlayerController.getInstance();
        playerController.changePlayerLocation();
    }


}
