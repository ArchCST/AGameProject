/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.util;

import me.archcst.agameproject.avatar.Player;
import me.archcst.agameproject.ui.GamePanel;

import java.awt.*;

public class Camera {
    private static Camera camera = null;
    private Thread cameraThread;

    private Camera() {
//        cameraThread = new Thread(this);
        beacon = new Point(Player.getInstance().getLocation().x - 12,
                Player.getInstance().getLocation().y);
        cPoint = new Point(beacon);
//        cameraThread.start();
    }

    public static Camera getInstance() {
        if (camera == null) {
            synchronized (Camera.class) {
                if (camera == null) {
                    camera = new Camera();
                }
            }
        }
        return camera;
    }

    private Point cPoint; // 相机的指向
    private Point beacon; // 信标的坐标

    public int packX(int x) {
        int panelCenterX = GamePanel.getInstance().getWidth() / 2;
        return panelCenterX - cPoint.x + x - GameSettings.BLOCK_SIZE / 2;
    }

    public int packY(int y) {
        int panelCenterY = GamePanel.getInstance().getHeight() / 2;
        return panelCenterY - cPoint.y + y - GameSettings.BLOCK_SIZE / 2;
    }

    public Point cameraedPoint(Point point) {
        return new Point(packX(point.x), packY(point.y));
    }

    public void setBeacon(Point point) {
        beacon.x = point.x;
        beacon.y = point.y;
    }

//    @Override
//    public void run() {
//        while (true) {
//            try {
//                Thread.sleep(16);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            updateCameraPoint();
//        }
//    }

    public void updateCamera() {
        if (cPoint.x != beacon.x) {
            if (beacon.x >= cPoint.x + GameSettings.CAMERA_SPEED) {
                cPoint.x += GameSettings.CAMERA_SPEED;
            } else if (beacon.x <= cPoint.x - GameSettings.CAMERA_SPEED) {
                cPoint.x -= GameSettings.CAMERA_SPEED;
            }
        }

        if (cPoint.y != beacon.y) {
            if (beacon.y >= cPoint.y + GameSettings.CAMERA_SPEED) {
                cPoint.y += GameSettings.CAMERA_SPEED;
            } else if (beacon.y <= cPoint.y - GameSettings.CAMERA_SPEED) {
                cPoint.y -= GameSettings.CAMERA_SPEED;
            }
        }
    }
}
