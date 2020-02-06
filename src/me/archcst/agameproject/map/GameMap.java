/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.map;

import me.archcst.agameproject.avatar.Avatar;
import me.archcst.agameproject.ui.GamePanel;
import me.archcst.agameproject.util.Camera;
import me.archcst.agameproject.util.CollisionBox;
import me.archcst.agameproject.util.GameSettings;

import java.awt.*;
import java.util.Random;

public class GameMap {
    public static final int BLOCK_SIZE = 48; // 地图块大小（px）
    private static MapBlock[][] mapBlocks; // 地图数组

    public void newMap() {
        mapBlocks = new MapGenerator().generateMap();
    }

    public void draw(Graphics g) {
        for (MapBlock[] mbs : mapBlocks) {
            for (MapBlock mb : mbs) {
                mb.draw(g);
            }
        }
    }

    /**
     * 验证传入的碰撞箱是否和地图碰撞
     *
     * @param cb 传入的碰撞箱
     * @return 是否碰撞
     */
    public boolean mapCollision(Graphics g, CollisionBox cb) {
        if (GameSettings.DEV_MODE && GameSettings.DEV_SHOW_TEMP_COLLISION_BOX) {
            Camera camera = Camera.getInstance();
            g.setColor(Color.WHITE);
            g.drawRect(camera.cameraedX(cb.x1), camera.cameraedY(cb.y1),
                    cb.width, cb.height);
        }

        boolean b = false;
        for (MapBlock[] mbs : mapBlocks) {
            for (MapBlock mb : mbs) {
                if (cb.equals(mb.getCollisionBox())) {
                    // DEV_MODE 画出当前碰撞箱
                    if (GameSettings.DEV_MODE && GameSettings.DEV_SHOW_CONFLICT_COLLISION_BOX) {
                        Camera camera = Camera.getInstance();
                        g.setColor(Color.RED);
                        g.drawRect(camera.cameraedX(mb.getCollisionBox().x1), camera.cameraedY(mb.getCollisionBox().y1),
                                mb.getCollisionBox().width, mb.getCollisionBox().height);

                        g.drawRect(camera.cameraedX(cb.x1), camera.cameraedY(cb.y1),
                                cb.width, cb.height);
                    }
                    b = true;
                }
            }
        }
        return b;
    }

    public Point randomEmptyBlock() {
        Point point = new Point(); // 返回值
        Random r = GameSettings.r;
        int x, y;
        do {
            x = r.nextInt(mapBlocks[0].length);
            y = r.nextInt(mapBlocks.length);
            if (mapBlocks[y][x].getCollisionBox().isEmpty()) {
                point.x = x * BLOCK_SIZE;
                point.y = y * BLOCK_SIZE;
            }
        } while (!mapBlocks[y][x].getCollisionBox().isEmpty());
        return point;
    }


    /**
     * 获取一个随机能放下怪物的坐标
     * @return
     */
    public Point randomLocation(Graphics g, Avatar avatar) {
        Point point = new Point(); // 返回值
        Random r = GameSettings.r;
        CollisionBox tempCB = null;
        do {
            point.x = (r.nextInt(mapBlocks[0].length - 5) + 2) * GameSettings.BLOCK_SIZE;
            point.y = (r.nextInt(mapBlocks.length - 3) + 1) * GameSettings.BLOCK_SIZE;
            tempCB = new CollisionBox(avatar.getCollisionBox(), point);
        } while (mapCollision(g, tempCB));

        return point;
    }

    /*
     * setters & getters
     */
//    public Point getOffset() {
//        return offset;
//    }


    public Dimension getMapSize() {
        Dimension mapSize = new Dimension();
        mapSize.width = mapBlocks[0].length;
        mapSize.height = mapBlocks.length;
        return mapSize;
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
