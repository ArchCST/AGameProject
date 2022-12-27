/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.ui;

import me.archcst.agameproject.util.GameSettings;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class GameOverPanel extends JPanel {
    private static GameOverPanel gamePanel;
    private ArrayList<Record> records = new ArrayList<>();
    private Record thisRecord = new Record();

    public static GameOverPanel getInstance() {
        if (gamePanel == null) {
            gamePanel = new GameOverPanel();
        }
        return gamePanel;
    }

    private GameOverPanel() {
        this.setSize(GameSettings.GAME_WIDTH, GameSettings.GAME_HEIGHT);
        this.setLocation(0, 0);
        this.setVisible(true);
        this.setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        String[] gameOver = new String[5];
        gameOver[0] = "　████　　　█　　　　█　█　 █████　　　　███　　█　　　█　█████　████ ";
        gameOver[1] = "█　　　　　　█　█ 　█　█　█　█　　　　　　　█ 　　█　█　　　█ █　　　　　█　　　█";
        gameOver[2] = "█　　██ █████ █　█　█　███ 　　　　█ 　　█　█　　　█　████　　████　";
        gameOver[3] = "█　　　█ █　　　█　█ █ █　█　　　　　　　█ 　　█　　█ █　　█　　　　　█　　█ ";
        gameOver[4] = "　████　█　　　█　█ █　█ █████　　　　███　　　　█　　　█████　█　　　█";

        g.setColor(Color.WHITE);
        for (int i = 0; i < gameOver.length; i++) {
            g.drawString(gameOver[i], 320, 90 + i * 14);
        }

        // 当前成绩
        g.setFont(new Font("Monaco", Font.BOLD, 20));
        g.drawString(thisRecord.formatScore(), 320, 230);

        // 历史成绩
        g.setFont(new Font("Monaco", Font.BOLD, 16));
        for (int i = 0; i < 10; i++) {
            if (i == records.size()) break;
            if (records.get(i) == thisRecord) {
                g.drawString("▶", 360, 318 + 20 * i);
            }
            g.drawString(records.get(i).formatScore(), 380, 320 + 20 * i);
        }

        // 新游戏

        g.setFont(new Font("Monaco", Font.BOLD, 20));
        g.drawString("PRESS [SPACE] TO RESTART", 500, 620);


        // 测试模式画中心点
        if (GameSettings.DEV_MODE && GameSettings.DEV_SHOW_PANEL_CENTRAL_POINT) {
            g.setColor(Color.CYAN);
//            g.drawString("╋", getWidth() / 2 - 8, getHeight() / 2 + 8);
            g.fillRect(getWidth() / 2 - 2, getHeight() / 2 - 2, 6, 6);
        }
    }

    public void newScore(int score, long startTime, long endTime) {
        thisRecord = new Record(score, startTime, endTime);
        if (GameSettings.RECORD_FILE.length() > 0) {
            readRecords();
        }
        records.add(thisRecord);
        Collections.sort(records);
        writeRecords();
    }

    private void writeRecords() {
        OutputStream outputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            outputStream = new FileOutputStream(GameSettings.RECORD_FILE);
            objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(records);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void readRecords() {
        InputStream inputStream = null;
        ObjectInputStream objectInputStream = null;

        try {
            inputStream = new FileInputStream(GameSettings.RECORD_FILE);
            objectInputStream = new ObjectInputStream(inputStream);
            records = (ArrayList<Record>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
