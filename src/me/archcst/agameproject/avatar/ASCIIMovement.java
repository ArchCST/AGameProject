/*
 * 动作类，包含渲染动画所需要的坐标数组
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar;

import me.archcst.agameproject.datacenter.Framer;

import java.awt.*;
import java.util.ArrayList;

public class ASCIIMovement {
    ArrayList<String[]> arrayList = new ArrayList<>(); // ArrayList 每一位是一帧
    private Color color;

    private String s1 = "" +
            " O  \n" +
            "/|\\\n" +
            "/ >   ";

    private String s2 = "" +
            " O  \n" +
            "/|\\\n" +
            " >\\  ";

    public ASCIIMovement() {
        String[] strings = s1.split("\n");
        arrayList.add(strings);
        strings = s2.split("\n");
        arrayList.add(strings);
        color = Color.WHITE;
    }

    public ASCIIMovement(String...strings) {
        for (int i = 0; i < strings.length; i++) {
            arrayList.add(strings[i].split("\n"));
        }
    }

    public void draw(Graphics g) {
        int frame = Framer.getInstance().getFrame(3);
        frame = frame % arrayList.size();

        g.setColor(color);
        for (int i = 0; i < arrayList.get(frame).length; i++) {
            String prt = arrayList.get(frame)[i];
            g.drawString(prt, 80, 80 + i * 16);
        }
    }
}
