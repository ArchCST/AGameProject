/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.ui;

import me.archcst.agameproject.datacenter.PlayerController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameKeyListener implements KeyListener {
    // Q: private PlayerController playerController = PlayerController.getInstance();

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        PlayerController playerController = PlayerController.getInstance();
        System.out.println("KeyPressed");
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
                // todo 死亡
            case KeyEvent.VK_X:
                playerController.die();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        PlayerController playerController = PlayerController.getInstance();
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
