/*
 * 动作类，包含渲染动画所需要的坐标数组
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar;

import me.archcst.agameproject.ui.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Action {
    private final Avatar avatar; // 所有者
    private File pictureFile; // 图片文件
    private Image image; // 图片对象

    private int frames; // 本动作的帧数
    private int[][] animationArray; // [帧][sx1, sy1, sx2, sy2]
    private Dimension dimension; // 原始大小

    /**
     * 分析图片，构造动作
     * @param avatar 所有者
     * @param pictureFile 图片链接
     * @param sx 图片左上角 x 坐标
     * @param sy 图片左上角 y 坐标
     * @param width 图片宽度
     * @param height 图片高度
     * @param frames 帧数: 横向重复的次数
     */
    public Action(Avatar avatar, File pictureFile, int sx, int sy, int width, int height, int frames) {
        /* todo 添加 todo 标签 Q:
         * Point point = new Point(sx, sy);
         * Dimension dimension = new Dimension(width, height);
         * Q: this(avatar, pictureFile, point, dimension, frames);
         */
        this.avatar = avatar;
        this.pictureFile = pictureFile;
        this.frames = frames;
        this.dimension = new Dimension(width, height);

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
            System.out.println(Arrays.toString(animationArray[i]));
        }
    }

    /**
     * 分析图片，构造动作
     * @param avatar 所有者
     * @param pictureFile 图片链接
     * @param point 起始坐标
     * @param dimension 单帧的长度和宽度
     * @param frames 帧数: 横向重复的次数
     */
    public Action(Avatar avatar, File pictureFile, Point point, Dimension dimension, int frames) {
        frames++;
        this.avatar = avatar;
        this.pictureFile = pictureFile;
        this.frames = frames;
        this.dimension = dimension;

        try {
            image = ImageIO.read(pictureFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        animationArray = new int[frames][4];
        for (int i = 0; i < frames; i++) {
            // 素材第二帧也是第四帧
            if (i == 3) {
                animationArray[i] = animationArray[1];
                break;
            }
            animationArray[i][0] = point.x + dimension.width * i;
            animationArray[i][1] = point.y;
            animationArray[i][2] = point.x + dimension.width * (i + 1);
            animationArray[i][3] = point.y + dimension.height;
        }
    }


    /**
     * 画出该动作
     * @param g 画笔
     * @param frame 画第几帧
     * @param point GamePanel 上的坐标
     */
    public void draw(Graphics g, int frame, Point point) {
        /*
         * todo 自定义异常
         */

        frame = frame % frames;
        g.drawImage(image,
                avatar.getPoint().x, avatar.getPoint().y, avatar.getPoint().x + dimension.width, avatar.getPoint().y + dimension.height,
                animationArray[frame][0], animationArray[frame][1],
                animationArray[frame][2], animationArray[frame][3],
                GamePanel.getInstance());
    }

    /**
     * 画出该动作
     * @param g 画笔
     * @param frame 画第几帧
     * @param point GamePanel 上的坐标
     * @param zoom 放大倍数
     */
    public void draw(Graphics g, int frame, Point point, double zoom) {
        /*
         * todo 自定义异常
         */

        frame = frame % frames;
        g.drawImage(image,
                avatar.getPoint().x, avatar.getPoint().y,
                avatar.getPoint().x + (int) (dimension.width * zoom), avatar.getPoint().y + (int) (dimension.height * zoom),
                animationArray[frame][0], animationArray[frame][1],
                animationArray[frame][2], animationArray[frame][3],
                GamePanel.getInstance());
    }
}
