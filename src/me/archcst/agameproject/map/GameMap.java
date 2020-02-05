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
import java.util.Random;

public class GameMap {
    public static final int BLOCK_SIZE = 48; // 地图块大小（px）
    private Point offset = new Point(); // 地图的整体偏移量
    private static MapBlock[][] mapBlocks; // 地图数组

    public void newMap() {
        mapBlocks = new MapGenerator().generateMap();
        offset = new Point(Player.getInstance().getLocation().x + 24 - mapBlocks[0].length * BLOCK_SIZE / 2,
                Player.getInstance().getLocation().y + 24 - mapBlocks.length * BLOCK_SIZE / 2);
        mapMove(offset);
    }

    public void draw(Graphics g) {
        for (MapBlock[] mbs : mapBlocks) {
            for (MapBlock mb : mbs) {
                mb.draw(g);
            }
        }
    }

    /**
     * 移动整张地图
     * @param offset 偏移量
     */
    public void mapMove(Point offset) {
        for (MapBlock[] mbs : mapBlocks) {
            for (MapBlock mb : mbs) {
                mb.blockMove(offset);
            }
        }
        // 设置地图偏移量
        this.offset.x += offset.x;
        this.offset.y += offset.y;
    }

    /**
     * 验证玩家与地图的碰撞
     *
     * @param g            开发模式测试用画笔
     * @param displacement 移动的距离
     * @return
     */
    public boolean playerCollision(Graphics g, Point displacement) {
        CollisionBox playerCB = Player.getInstance().getCollisionBox();
        CollisionBox tempCB;
        boolean b = false;

        for (MapBlock[] mbs : mapBlocks) {
            for (MapBlock mb : mbs) {
                tempCB = new CollisionBox(mb.getCollisionBox(), displacement);
                // DEV_MODE 画出所有地图块的碰撞箱
                if (GameSettings.DEV_MODE && GameSettings.DEV_SHOW_MAP_COLLISION_BOX) {
                    g.setColor(Color.WHITE);
                    g.drawRect(tempCB.x1, tempCB.y1,
                            tempCB.width, tempCB.height);
                }
                if (tempCB.equals(playerCB)) {
                    // 画出冲突的碰撞箱
                    if (GameSettings.DEV_MODE && GameSettings.DEV_SHOW_AVATAR_COLLISION_BOX) {
                        g.setColor(Color.RED);
                        g.drawRect(playerCB.x1, playerCB.y1,
                                playerCB.width, playerCB.height);
                        g.setColor(Color.RED);
                        g.drawRect(tempCB.x1, tempCB.y1,
                                tempCB.width, tempCB.height);
                    }

                    b = true;
                }
            }
        }
        return b;
    }

    /**
     * 验证传入的碰撞箱是否和地图碰撞
     *
     * @param g  测试用画笔
     * @param cb 传入的碰撞箱
     * @return 是否碰撞
     */
    public boolean validateCollision(Graphics g, CollisionBox cb) {
        if (GameSettings.DEV_MODE) {
            g.setColor(Color.WHITE);
            g.drawRect(cb.x1, cb.y1,
                    cb.width, cb.height);
        }

        boolean b = false;
        for (MapBlock[] mbs : mapBlocks) {
            for (MapBlock mb : mbs) {
                // DEV_MODE 画出当前碰撞箱
                if (cb.equals(mb.getCollisionBox())) {
                    if (GameSettings.DEV_MODE && GameSettings.DEV_SHOW_CONFLICT_COLLISION_BOX) {
                        g.setColor(Color.RED);
                        g.drawRect(mb.getCollisionBox().x1, mb.getCollisionBox().y1,
                                mb.getCollisionBox().width, mb.getCollisionBox().height);
                        g.setColor(Color.BLUE);
                        g.drawRect(cb.x1, cb.y1,
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
        Random r = new Random();
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

    /*
     * setters & getters
     */
    public Point getOffset() {
        return offset;
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
