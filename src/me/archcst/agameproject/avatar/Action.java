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
    private String[][] animate;
    private int frames;

    public Action(String[][] animate) {
        this.animate = animate;
        frames = animate.length;
    }

    /*
     * setters & getters
     */

    public int getFrames() {
        return frames;
    }

    public String[] getAnimateByFrame(int frame) {
        return animate[frame];
    }
}
