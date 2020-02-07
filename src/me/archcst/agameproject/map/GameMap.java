/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.map;

import me.archcst.agameproject.avatar.Avatar;
import me.archcst.agameproject.avatar.Player;
import me.archcst.agameproject.avatar.obstacles.Obstacle;
import me.archcst.agameproject.avatar.Wall;
import me.archcst.agameproject.avatar.obstacles.ObstacleFactory;
import me.archcst.agameproject.util.Camera;
import me.archcst.agameproject.util.CollisionBox;
import me.archcst.agameproject.util.GameSettings;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameMap {
    private static GameMap gameMap = null;
    private Dimension mapSize = new Dimension(); // 地图大小（地图块个数，并非像素大小）
    private Random r; // 随机生成地图
    private MapBlock[][] mapBlocks; //地图数组
    private ArrayList<Wall> walls = new ArrayList<>(); // 所有的墙
    private ArrayList<Obstacle> obstacles = new ArrayList<>(); // 所有的障碍物
    private CollisionBox safeArea;

    private GameMap() {
        r = GameSettings.r;
        newMap();
    }

    public static GameMap getInstance() {
        if (gameMap == null) {
            gameMap = new GameMap();
        }
        return gameMap;
    }


    public void newMap() {
        generateMap();
        generateWall();


        safeArea = new CollisionBox(mapSize.width * GameSettings.BLOCK_SIZE / 2 - 30,
                mapSize.height * GameSettings.BLOCK_SIZE / 2 - 30,
                mapSize.width*GameSettings.BLOCK_SIZE/2 + 30,
                mapSize.height*GameSettings.BLOCK_SIZE/2 + 30);

        generateObstacles();
    }


    private void generateMap() {
        generateMapSize();

        mapBlocks = new MapBlock[mapSize.height][mapSize.width];

        for (int line = 0; line < mapBlocks.length; line++) {
            for (int column = 0; column < mapBlocks[0].length; column++) {
                Point mPoint = new Point(column * GameSettings.BLOCK_SIZE, line * GameSettings.BLOCK_SIZE);
                mapBlocks[line][column] = new MapBlock(mPoint);
            }
        }

    }

    private void generateMapSize() {
        // 地图的宽永远大于长
        int a = r.nextInt(GameSettings.MAP_MAX_WIDTH - GameSettings.MAP_MIN_WIDTH) + GameSettings.MAP_MIN_WIDTH;
        int b = r.nextInt(GameSettings.MAP_MAX_HEIGHT - GameSettings.MAP_MIN_HEIGHT) + GameSettings.MAP_MIN_HEIGHT;

        // 地图的长各宽都为奇数
        a = (a % 2 == 0) ? a + 1 : a;
        b = (b % 2 == 0) ? b + 1 : b;

        mapSize.width = Math.max(a, b);
        mapSize.height = Math.min(a, b);
    }

    private void generateWall() {
        for (int i = 0; i < mapSize.width; i++) {
            walls.add(new Wall(1,
                    i * GameSettings.BLOCK_SIZE,
                    -GameSettings.BLOCK_SIZE));

            walls.add(new Wall(1,
                    i * GameSettings.BLOCK_SIZE,
                    mapSize.height * GameSettings.BLOCK_SIZE));
        }


        for (int i = 0; i < mapSize.height; i++) {
            walls.add(new Wall(1,
                    -GameSettings.BLOCK_SIZE,
                    i * GameSettings.BLOCK_SIZE));

            walls.add(new Wall(1,
                    mapSize.width * GameSettings.BLOCK_SIZE,
                    i * GameSettings.BLOCK_SIZE));
        }

        // 添加四个角的墙
        walls.add(new Wall(1,
                -GameSettings.BLOCK_SIZE,
                -GameSettings.BLOCK_SIZE));
        walls.add(new Wall(1,
                mapSize.width * GameSettings.BLOCK_SIZE,
                -GameSettings.BLOCK_SIZE));
        walls.add(new Wall(1,
                -GameSettings.BLOCK_SIZE,
                mapSize.height * GameSettings.BLOCK_SIZE));
        walls.add(new Wall(1,
                mapSize.width * GameSettings.BLOCK_SIZE,
                mapSize.height * GameSettings.BLOCK_SIZE));
    }

    private void generateObstacles() {
        int amount = (int) (GameSettings.OBSTACLE_RATE * mapSize.width * mapSize.height);
        for (int i = 0; i < amount; i++) {
            Obstacle obstacle = ObstacleFactory.createObstacle(r.nextInt(ObstacleFactory.OBSTACLE_TYPE_AMOUNT));
            Location location = randomLocation(obstacle.getCollisionBox());
            obstacle.setLocation(location);
            obstacle.getCollisionBox().boxMove(location);

            obstacles.add(obstacle);
        }
    }

    /**
     * 根据碰撞箱找到可以放下物体的坐标，避免碰撞箱重合
     *
     * @param cb 传入的碰撞箱
     * @return 合适的坐标
     */
    public Location randomLocation(CollisionBox cb) {
        Location location = new Location(); // 返回值
        Random r = GameSettings.r;
        CollisionBox tempCB = null;
        do {
            location.setX(r.nextInt(mapSize.width * GameSettings.BLOCK_SIZE));
            location.setY(r.nextInt(mapSize.height * GameSettings.BLOCK_SIZE));
            tempCB = new CollisionBox(cb, location);
        } while (mapCollision(tempCB) || tempCB.equals(safeArea));

        return location;
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
            g.drawRect(camera.packX(cb.x1), camera.packY(cb.y1),
                    (int)cb.width, (int)cb.height);
        }

        boolean b = false;
        for (CollisionBox mc : getAllMapCollisionBox()) {
            if (cb.equals(mc)) {
                // DEV_MODE 画出当前碰撞箱
                if (GameSettings.DEV_MODE && GameSettings.DEV_SHOW_CONFLICT_COLLISION_BOX) {
                    Camera camera = Camera.getInstance();
                    g.setColor(Color.RED);
                    g.drawRect(camera.packX(mc.x1), camera.packY(mc.y1),
                            (int)mc.width, (int)mc.height);

                    g.drawRect(camera.packX(cb.x1), camera.packY(cb.y1),
                            (int)cb.width, (int)cb.height);
                }
                b = true;
            }
        }
        return b;
    }

    /**
     * 验证传入的碰撞箱是否和地图碰撞
     *
     * @param cb 传入的碰撞箱
     * @return 是否碰撞
     */
    public boolean mapCollision(CollisionBox cb) {
        ArrayList<CollisionBox> mc = getAllMapCollisionBox();

        for (CollisionBox c : mc) {
            if (cb.equals(c)) {
                // DEV_MODE 画出当前碰撞箱
                return true;
            }
        }
        return false;
    }

    public void draw(Graphics g) {
        for (MapBlock[] mbs : mapBlocks) {
            for (MapBlock mb : mbs) {
                mb.draw(g);
            }
        }

        for (Wall wall : walls) {
            wall.draw(g);
        }

        for (Obstacle obstacle : obstacles) {
            obstacle.draw(g);
        }
    }

    public Dimension getMapSize() {
        return mapSize;
    }

    public ArrayList<CollisionBox> getAllMapCollisionBox() {
        ArrayList<CollisionBox> mc = new ArrayList<>();

        for (Wall wall : walls) {
            mc.add(wall.getCollisionBox());
        }

        for (Obstacle obstacle : obstacles) {
            mc.add(obstacle.getCollisionBox());
        }

        return mc;
    }
}
