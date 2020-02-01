/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.ui;

import me.archcst.agameproject.avatar.Player;
import me.archcst.agameproject.datacenter.DataCenter;
import me.archcst.agameproject.datacenter.PlayerController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameKeyListener implements KeyListener {
    PlayerController playerController = PlayerController.getInstance();

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                playerController.setDirections("up", true);
                break;
            case KeyEvent.VK_A:
                playerController.setDirections("left", true);
                break;
            case KeyEvent.VK_D:
                playerController.setDirections("right", true);
                break;
            case KeyEvent.VK_S:
                playerController.setDirections("down", true);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                playerController.setDirections("up", false);
                break;
            case KeyEvent.VK_A:
                playerController.setDirections("left", false);
                break;
            case KeyEvent.VK_D:
                playerController.setDirections("right", false);
                break;
            case KeyEvent.VK_S:
                playerController.setDirections("down", false);
                break;
        }
    }
}
