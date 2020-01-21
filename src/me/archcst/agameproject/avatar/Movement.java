/*
 * 动作类，包含渲染动画所需要的坐标数组
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Movement {
    private File pictureFile; // 图片文件
    private Image image; // 图片对象
    private int frames; // 本动作的帧数
    private int[][] animationArray; // [帧][sx1, sy1, sx2, sy2]

    /**
     * 分析图片，构造动作
     * @param pictureFile 图片链接
     * @param sx 左上角 x 坐标
     * @param sy 左上角 y 坐标
     * @param width 图片宽度
     * @param height 图片高度
     * @param frames 横向重复的次数
     */
    public Movement(File pictureFile, int sx, int sy, int width, int height, int frames) {
        this.pictureFile = pictureFile;
        this.frames = frames;

        try {
            image = ImageIO.read(pictureFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        animationArray = new int[frames][4];
        for (int i = 0; i < frames; i++) {
            animationArray[i][0] = sx + width * i;
            animationArray[i][1] = sy;
            animationArray[i][2] = sx + width * (i + 1);
            animationArray[i][3] = sy + height;
        }
    }

    public void draw(Graphics g) {

    }
}
