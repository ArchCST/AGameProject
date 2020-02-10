/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.ui;

import me.archcst.agameproject.avatar.Player;
import me.archcst.agameproject.datacenter.DataCenter;
import me.archcst.agameproject.util.GameSettings;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameKeyListener implements KeyListener {
    // Q: private PlayerController playerController = PlayerController.getInstance();

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Player player = Player.getInstance();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                player.moveCtrl.setDirection("up", true);
                break;
            case KeyEvent.VK_A:
                player.moveCtrl.setDirection("left", true);
                break;
            case KeyEvent.VK_D:
                player.moveCtrl.setDirection("right", true);
                break;
            case KeyEvent.VK_S:
                player.moveCtrl.setDirection("down", true);
                break;
            case KeyEvent.VK_J:
                player.setAttacking(true);
                break;
            case KeyEvent.VK_SPACE:
                System.out.println("space");
                if (!Player.getInstance().getAlive()) {
                    System.out.println("new game");
                    GameFrame.jFrame.remove(GameOverPanel.getInstance());
                    GameFrame.jFrame.add(GamePanel.getInstance());
                    GameFrame.jFrame.repaint();
                    DataCenter.getInstance().newGame();
                }
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Player player = Player.getInstance();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                player.moveCtrl.setDirection("up", false);
                break;
            case KeyEvent.VK_A:
                player.moveCtrl.setDirection("left", false);
                break;
            case KeyEvent.VK_D:
                player.moveCtrl.setDirection("right", false);
                break;
            case KeyEvent.VK_S:
                player.moveCtrl.setDirection("down", false);
                break;
            case KeyEvent.VK_J:
                player.setAttacking(false);
                break;
            // 开发选项
            case KeyEvent.VK_X:
                if (GameSettings.DEV_MODE) {
                    player.changeHp(-100);
                }
                break;
            case KeyEvent.VK_1:
                if (GameSettings.DEV_MODE) {
                    GameSettings.HINT = !GameSettings.HINT;
                }
                break;
            case KeyEvent.VK_2:
                if (GameSettings.DEV_MODE) {
                    GameSettings.SHOW_AIMING = !GameSettings.SHOW_AIMING;
                }
                break;
            case KeyEvent.VK_3:
                if (GameSettings.DEV_MODE) {
                    GameSettings.DEV_SHOW_PANEL_CENTRAL_POINT = !GameSettings.DEV_SHOW_PANEL_CENTRAL_POINT;
                }
                break;
            case KeyEvent.VK_4:
                if (GameSettings.DEV_MODE) {
                    GameSettings.DEV_SHOW_AVATAR_BOX = !GameSettings.DEV_SHOW_AVATAR_BOX;
                }
                break;
            case KeyEvent.VK_5:
                if (GameSettings.DEV_MODE) {
                    GameSettings.DEV_SHOW_AVATAR_COLLISION_BOX = !GameSettings.DEV_SHOW_AVATAR_COLLISION_BOX;
                }
                break;
            case KeyEvent.VK_6:
                if (GameSettings.DEV_MODE) {
                    GameSettings.DEV_SHOW_CONFLICT_COLLISION_BOX = !GameSettings.DEV_SHOW_CONFLICT_COLLISION_BOX;
                }
                break;
            case KeyEvent.VK_7:
                if (GameSettings.DEV_MODE) {
                    GameSettings.DEV_SHOW_TEMP_COLLISION_BOX = !GameSettings.DEV_SHOW_TEMP_COLLISION_BOX;
                }
                break;
            case KeyEvent.VK_8:
                if (GameSettings.DEV_MODE) {
                    GameSettings.DEV_SHOW_BEACON = !GameSettings.DEV_SHOW_BEACON;
                }
                break;
            case KeyEvent.VK_9:
                if (GameSettings.DEV_MODE) {
                    GameSettings.MONSTER_MOVE = !GameSettings.MONSTER_MOVE;
                }
                break;
        }
    }
}
