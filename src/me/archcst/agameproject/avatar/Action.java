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
            animationArray[i][0] = point.x + avatar.getsSize().width * i;
            animationArray[i][1] = point.y;
            animationArray[i][2] = point.x + avatar.getsSize().width * (i + 1);
            animationArray[i][3] = point.y + avatar.getsSize().height;
        }
    }

    /*
     * setters & getters
     */
    public int getFrames() {
        return frames;
    }

    public Image getImage() {
        return image;
    }

    public int sx1(int frame) {
        return animationArray[frame][0];
    }

    public int sy1(int frame) {
        return animationArray[frame][1];
    }

    public int sx2(int frame) {
        return animationArray[frame][2];
    }

    public int sy2(int frame) {
        return animationArray[frame][3];
    }
}
