/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.map;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class MapElements {
    private MapBlock[] mapBlocks = new MapBlock[30];

    private Image mapImage = null;

    public static final HashMap<String, Integer> type = new HashMap<>(); // 地图字典

    static {
        type.put("wall", 0);
        type.put("wall_dark", 1);
        type.put("road", 2);
        type.put("road_whole", 3);
        type.put("water_small", 4);
        type.put("obstacle_1", 5);
        type.put("obstacle_2", 6);
        type.put("obstacle_3", 7);
        type.put("obstacle_4", 8);
        type.put("obstacle_5", 9);
        type.put("obstacle_6", 10);
        type.put("obstacle_7", 11);
        type.put("obstacle_8", 12);
        type.put("obstacle_9", 13);
        type.put("obstacle_10", 14);
        type.put("obstacle_2_1", 15);
        type.put("obstacle_2_2", 16);
        type.put("obstacle_2_3", 17);
        type.put("obstacle_2_4", 18);
        type.put("obstacle_2_5", 19);
        type.put("water_big", 20);
        type.put("obstacle_4_1", 21);
        type.put("obstacle_4_2", 22);
        type.put("door_close", 23);
        type.put("door_open", 24);
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

    private void loadMap(int random) {
        loadEnvironment(random);
        Dimension d11 = new Dimension(24, 24);
        Dimension d12 = new Dimension(24, 48);
        Dimension d22 = new Dimension(48, 48);
//        mapElements[type.get("wall")] = new MapElement(mapImage, d11, 0, 0);
//        mapElements[type.get("wall_dark")] = new MapElement(mapImage, d11, 48, 0);
//        mapElements[type.get("road")] = new MapElement(mapImage, d11, 96, 0);
//        mapElements[type.get("road_whole")] = new MapElement(mapImage, d11, 144, 0);
//        mapElements[type.get("water_small")] = new MapElement(mapImage, d11, 192, 0);
//        mapElements[type.get("obstacle_1")] = new MapElement(mapImage, d11, 0, 48);
//        mapElements[type.get("obstacle_2")] = new MapElement(mapImage, d11, 48, 48);
//        mapElements[type.get("obstacle_3")] = new MapElement(mapImage, d11, 96, 48);
//        mapElements[type.get("obstacle_4")] = new MapElement(mapImage, d11, 144, 48);
//        mapElements[type.get("obstacle_5")] = new MapElement(mapImage, d11, 192, 48);
//        mapElements[type.get("obstacle_6")] = new MapElement(mapImage, d11, 240, 48);
//        mapElements[type.get("obstacle_7")] = new MapElement(mapImage, d11, 288, 48);
//        mapElements[type.get("obstacle_8")] = new MapElement(mapImage, d11, 336, 48);
//        mapElements[type.get("obstacle_9")] = new MapElement(mapImage, d11, 384, 48);
//        mapElements[type.get("obstacle_10")] = new MapElement(mapImage, d11, 432, 48);
//        mapElements[type.get("obstacle_2_1")] = new MapElement(mapImage, d12, 0, 96);
//        mapElements[type.get("obstacle_2_2")] = new MapElement(mapImage, d12, 48, 96);
//        mapElements[type.get("obstacle_2_3")] = new MapElement(mapImage, d12, 96, 96);
//        mapElements[type.get("obstacle_2_4")] = new MapElement(mapImage, d12, 144, 96);
//        mapElements[type.get("obstacle_2_5")] = new MapElement(mapImage, d12, 192, 96);
//        mapElements[type.get("water_big")] = new MapElement(mapImage, d22, 0, 384);
//        mapElements[type.get("obstacle_4_1")] = new MapElement(mapImage, d22, 96, 384);
//        mapElements[type.get("obstacle_4_2")] = new MapElement(mapImage, d22, 192, 384);
//        mapElements[type.get("door_close")] = new MapElement(mapImage, d22, 288, 384);
//        mapElements[type.get("door_open")] = new MapElement(mapImage, d22, 384, 384);
    }
}
