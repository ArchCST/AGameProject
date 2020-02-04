/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.map;

import me.archcst.agameproject.util.CollisionBox;
import me.archcst.agameproject.util.GameSettings;

import javax.imageio.ImageIO;
import javax.sql.PooledConnection;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class MapGenerator {
    private Dimension mapSize = new Dimension(); // 地图大小
    private Random r = null; // 随机生成地图
    private Image mapImage = null;
    private static final int BLOCK_SIZE = 48;

    // 地图大小常数
    private static final int minWidth = 10;
    private static final int maxWidth = 27;
    private static final int minHeight = 10;
    private static final int maxHeight = 21;

    public MapGenerator() {
        r = new Random();
    }

    public MapGenerator(long seed) {
        r = new Random(seed);
    }

    public MapBlock[][] generateMap() {
        generateMapSize();
        loadEnvironment(r.nextInt());

        MapBlock[][] mapBlocks = new MapBlock[mapSize.height][mapSize.width];

        for (int line = 0; line < mapBlocks.length; line++) {
            for (int column = 0; column < mapBlocks[0].length; column++) {
                mapBlocks[line][column] = generateMapBlock(line, column);
            }
        }

        return mapBlocks;
    }

    private MapBlock generateMapBlock(int line, int column) {
        Dimension dimension = new Dimension(BLOCK_SIZE, BLOCK_SIZE);
        Point mPoint = new Point(column * BLOCK_SIZE, line * BLOCK_SIZE);
        Point sPoint = new Point(96, 0); // 默认为路图
        CollisionBox collisionBox = new CollisionBox();
        boolean obstacleAble = false;

        // 外墙
        if (line == 0 || line == mapSize.height - 2 || column == 0 || column == mapSize.width - 1) {
            sPoint.x = 0;
            sPoint.y = 0;

            collisionBox.setCollisionBox(mPoint.x, mPoint.y,
                    mPoint.x + dimension.width, mPoint.y + dimension.height);

        }

        // 外墙阴影
        if (line == mapSize.height - 1 || line == 1 && column != 0 && column != mapSize.width - 1) {
            sPoint.x = 48;
            sPoint.y = 0;

            collisionBox.setCollisionBox(mPoint.x, mPoint.y,
                    mPoint.x + dimension.width, mPoint.y + dimension.height);
        }

        // 正中间的一格不能放障碍物
        if (column != mapSize.width / 2 || line != mapSize.height / 2) {
            obstacleAble = column > 1 && line > 2 && column < mapSize.width - 2 && line < mapSize.height - 3;
        }

        // 一格障碍物
        if (obstacleAble && r.nextInt(10000) < GameSettings.CHANCE_OF_OBSTACLE_1) {
            sPoint.x = r.nextInt(10) * 48;
            sPoint.y = 48;

            collisionBox.setCollisionBox(mPoint.x, mPoint.y,
                    mPoint.x + dimension.width, mPoint.y + dimension.height);
        }

        return new MapBlock(mapImage,
                new Dimension(48, 48),
                mPoint,
                sPoint,
                collisionBox);

    }

    private void generateMapSize() {
        // 地图的宽永远大于长
        int a = r.nextInt(maxWidth - minWidth) + minWidth;
        int b = r.nextInt(maxHeight - minHeight) + minHeight;

        // 地图的长各宽都为奇数
        a = (a % 2 == 0) ? a + 1 : a;
        b = (b % 2 == 0) ? b + 1 : b;

        mapSize.width = Math.max(a, b);
        mapSize.height = Math.min(a, b);
    }

    private void loadEnvironment(int random) {
        ArrayList<File> evm = new ArrayList<>();
        evm.add(new File("src/me/archcst/agameproject/static/img/map/grassLand.png"));

        loadImage(evm.get(random % evm.size()));
    }

    private void loadImage(File imageFile) {
        try {
            mapImage = ImageIO.read(imageFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
