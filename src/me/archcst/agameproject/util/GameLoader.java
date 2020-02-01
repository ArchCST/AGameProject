/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.util;

import me.archcst.agameproject.avatar.Action;
import me.archcst.agameproject.avatar.Player;

import java.awt.*;
import java.io.File;

public class GameLoader {
    Player player = Player.getInstance();

    public void initGame() {
        System.out.println(loadPlayer());
    }

    private String loadPlayer() {

        Point point = new Point(288, 0);
        Dimension dimension = new Dimension(48, 48);
        File playerFile = new File("src/me/archcst/agameproject/static/img/characters/Actor1.png");

        player.actions.put("walk_down", new Action(player,
                playerFile, point, dimension, 3));

        point.y += 48;
        player.actions.put("walk_left", new Action(player,
                playerFile, point, dimension, 3));

        point.y += 48;
        player.actions.put("walk_right", new Action(player,
                playerFile, point, dimension, 3));

        point.y += 48;
        player.actions.put("walk_up", new Action(player,
                playerFile, point, dimension, 3));

        player.setActionString("walk_down");
        player.setWalkSpeed(3);
        player.setRefreshRate(0);

        return "玩家加载成功...";
    }
}
