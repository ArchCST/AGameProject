/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.map;

import me.archcst.agameproject.util.Camera;
import me.archcst.agameproject.util.GameSettings;

import java.awt.*;
import java.util.Random;

public class MapBlock {
    public static String[][] images; // 地图块的ASCII图片
    public int type;
    public Point mPoint;

    static {
        images = new String[5][3];

        images[0][0] = "╭─╮";
        images[0][1] = "│╳│";
        images[0][2] = "╰─╯";

        images[1][0] = "╭─╮";
        images[1][1] = "│┼│";
        images[1][2] = "╰─╯";

        images[2][0] = "╭─╮";
        images[2][1] = "│　│";
        images[2][2] = "╰─╯";

        images[3] = images[2];
        images[4] = images[2];

    }

    public MapBlock(Point mPoint) {
        Random r = GameSettings.r;
        type = r.nextInt(images.length); // 此地图块为一个随机样式

        this.mPoint = mPoint;
    }

    public void draw(Graphics g) {
        Camera camera = Camera.getInstance();

//        g.setFont(GameSettings.FONT);
        g.setColor(GameSettings.MAP_COLOR);
        // 随机画任意一个地图块图形
        for (int i = 0; i < images[0].length; i++) {
//            System.out.println(g.getFont());
            g.drawString(images[type][i],
                    camera.packX(mPoint.x),
                    // 字符修正
                    camera.packY(mPoint.y + (i+1) * GameSettings.FONT_SIZE));
        }

        // 开发模式画所有地图块灰色外框
//        if (GameSettings.DEV_MODE && GameSettings.DEV_SHOW_MAP_BLOCKS) {
//            g.setColor(Color.GRAY);
//            g.drawRect(camera.cameraedX(mPoint.x), camera.cameraedY(mPoint.y),
//                    dimension.width, dimension.height);
//        }
    }

    /**
     * 清除该地图块
     *
     * @param g
     */
    public void clear(Graphics g) {
        Camera camera = Camera.getInstance();
        g.setColor(GameSettings.BACKGROUND_COLOR);
        g.fillRect(camera.packX(mPoint.x), camera.packY(mPoint.y),
                GameSettings.BLOCK_SIZE, GameSettings.BLOCK_SIZE);
    }

    /*
     * setters & getters
     */
    public int x() {
        return mPoint.x;
    }

    public int y() {
        return mPoint.y;
    }

    public Point getmPoint() {
        return mPoint;
    }
}
