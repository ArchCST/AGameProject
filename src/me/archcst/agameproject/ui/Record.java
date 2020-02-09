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

        String resultString = "Score: "+score+"            "+m+":"+s+"            " + endTimeString;
        return resultString;
    }

    @Override
    public int compareTo(Record o) {
        return this.score - o.score;
    }
}
