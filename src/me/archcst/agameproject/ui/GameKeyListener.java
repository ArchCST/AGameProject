/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.ui;

import me.archcst.agameproject.avatar.Player;

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
                // todo 死亡
            case KeyEvent.VK_X:
                player.die();
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
        }
    }
}
