/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.datacenter;

public class Framer {
    private static Framer framer = null;
    private int frame = 0;

    private Framer() {}

    public static Framer getInstance() {
        if (framer == null) {
            synchronized (Framer.class) {
                if (framer == null) {
                    framer = new Framer();
                }
            }
        }
        return framer;
    }

    public synchronized int nextFrame() {
        if (++frame > 250) {
            frame = 0;
        }
        return frame;
    }

    public int getFrame() {
        return frame;
    }

    /**
     * 获取当前应该播放第几帧动画
     * @param speed 多少帧切换动画
     * @return
     */
    public int getFrame(int speed) {
        return frame / speed;
    }
}
