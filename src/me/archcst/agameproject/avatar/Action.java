/*
 * 动作类，包含渲染动画所需要的坐标数组
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar;

import me.archcst.agameproject.ui.GamePanel;
import me.archcst.agameproject.util.GameSettings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Action {
    private final Avatar avatar; // 所有者
    private Image image; // 图片对象

    private int frames; // 本动作的帧数
    private int[][] animationArray; // [帧][sx1, sy1, sx2, sy2]

    /**
     * 分析图片，构造动作
     * @param avatar 所有者
     * @param pictureFile 图片链接
     * @param point 素材图上的起始坐标
     * @param frames 帧数: 横向重复的次数
     */
    public Action(Avatar avatar, File pictureFile, Point point, int frames) {
        frames++;
        this.avatar = avatar;
        this.frames = frames;

        try {
            image = ImageIO.read(pictureFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        animationArray = new int[frames][4];
        for (int i = 0; i < frames; i++) {
            // 素材的第二帧是动画的第四帧
            if (i == 3) {
                animationArray[i] = animationArray[1];
                break;
            }
            animationArray[i][0] = point.x + avatar.getDimension().width * i;
            animationArray[i][1] = point.y;
            animationArray[i][2] = point.x + avatar.getDimension().width * (i + 1);
            animationArray[i][3] = point.y + avatar.getDimension().height;
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
                avatar.getpPoint().x, avatar.getpPoint().y,
                avatar.getpPoint().x + avatar.getDimension().width, avatar.getpPoint().y + avatar.getDimension().height,
                animationArray[frame][0], animationArray[frame][1],
                animationArray[frame][2], animationArray[frame][3],
                GamePanel.getInstance());
    }

    /**
     * 画出该动作，可放大
     * @param g 画笔
     * @param frame 画第几帧
     * @param zoom 放大倍数
     */
    public void draw(Graphics g, int frame, double zoom) {
        /*
         * todo 自定义异常
         */

        frame = frame % frames;
        g.drawImage(image,
                avatar.getpPoint().x, avatar.getpPoint().y,
                avatar.getpPoint().x + (int) (avatar.getDimension().width * zoom), avatar.getpPoint().y + (int) (avatar.getDimension().height * zoom),
                animationArray[frame][0], animationArray[frame][1],
                animationArray[frame][2], animationArray[frame][3],
                GamePanel.getInstance());

        if (GameSettings.DEV_MODE) {
            g.setColor(Color.GREEN);
            g.drawRect(avatar.getCollisionBox().x1, avatar.getCollisionBox().y1,
                    avatar.getCollisionBox().width, avatar.getCollisionBox().height);
        }
    }
}
