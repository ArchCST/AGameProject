/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.ui;

import java.io.Serializable;
import java.text.SimpleDateFormat;

public class Record implements Serializable, Comparable<Record> {
    private int score;
    private long startTime;
    private long endTime;
    private int duration;

    public Record() {

    }

    public Record(int score, long startTime, long endTime) {
        this.score = score;
        this.startTime = startTime;
        this.endTime = endTime;
        duration = (int) (endTime - startTime);
    }

    public String formatScore() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String endTimeString = sdf.format(endTime);

        String m = "" + duration / (1000 * 60);
        String s = "" + duration / (1000) % 60;

        s = s.length() == 2 ? s : "0" + s;
        if (m.length() < 5) {
            int zeros = 5 - m.length();
            for (int i = 0; i < zeros; i++) {
                m = " "+m;
            }
        }
        String scr = score + "";

        if (scr.length() < 5) {
            int zeros = 5 - scr.length();
            for (int i = 0; i < zeros; i++) {
                scr = " " + scr;
            }
        }


        String resultString = "Score: "+scr+"        "+m+":"+s+"          " + endTimeString;
        return resultString;
    }

    @Override
    public int compareTo(Record o) {
        return o.score - this.score;
    }
}
