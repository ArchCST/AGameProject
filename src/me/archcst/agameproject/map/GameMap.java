/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.map;

import me.archcst.agameproject.avatar.Player;
import me.archcst.agameproject.util.CollisionBox;
import me.archcst.agameproject.util.GameSettings;

import java.awt.*;

public class GameMap {
    public static final int BLOCK_SIZE = 48; // 地图块大小（px）
    private static Dimension offset = new Dimension(); // player 移动时地图的偏移量
    private static MapBlock[][] mapBlocks; // 地图数组

    public void newMap() {
        mapBlocks = new MapGenerator().generateMap();
        offset.width = Player.getInstance().getpPoint().x+24-mapBlocks[0].length * BLOCK_SIZE / 2;
        offset.height = Player.getInstance().getpPoint().y+24-mapBlocks.length * BLOCK_SIZE / 2;
    }

    public void draw(Graphics g) {
        for (int line = 0; line < mapBlocks.length; line++) {
            for (int column = 0; column < mapBlocks[0].length; column++) {
                mapBlocks[line][column].draw(g, offset);
            }
        }
    }

    public Boolean playerCollision(Graphics g, Dimension tempOffset) {
        CollisionBox playerCB = Player.getInstance().getCollisionBox();
        CollisionBox tempCB;
        Boolean b = false;
        for (MapBlock[] mbs: mapBlocks) {
            for (MapBlock mb: mbs) {
                tempCB = new CollisionBox(mb.getCollisionBox(), tempOffset);
                // DEV_MODE 画出当前碰撞箱
                if (GameSettings.DEV_MODE) {
                    g.setColor(Color.WHITE);
                    g.drawRect(tempCB.x1, tempCB.y1,
                    tempCB.width, tempCB.height);
                }
                if (tempCB.equals(playerCB)) {
                    if (GameSettings.DEV_MODE) {
                        g.setColor(Color.RED);
                        g.drawRect(playerCB.x1, playerCB.y1,
                                playerCB.width, playerCB.height);
                        g.drawRect(tempCB.x1, tempCB.y1,
                                tempCB.width, tempCB.height);
                    }

                    b = true;
                }
            }
        }
        return b;
    }

    /*
     * setters & getters
     */
    public Dimension getOffset() {
        return offset;
    }

    public void setOffset(Dimension offset) {
        GameMap.offset = offset;
    }

    private static GameMap gameMap = null;
    private GameMap() {
        if (mapBlocks == null) {
            newMap();
        }
    }
    public static GameMap getInstance() {
        if (gameMap == null) {
            gameMap = new GameMap();
        }
        return gameMap;
    }
}
