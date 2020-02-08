/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.util;

import me.archcst.agameproject.avatar.Player;
import me.archcst.agameproject.map.DPoint;
import me.archcst.agameproject.ui.GamePanel;

public class Camera {
    private static Camera camera = null;

    private Camera() {
//        cameraThread = new Thread(this);
        beacon = new DPoint(Player.getInstance().getLocation().x() - 12,
                Player.getInstance().getLocation().y());
//        beacon = Player.getInstance().getCenter();
        cLocation = new DPoint(beacon);
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

    private DPoint cLocation; // 相机的指向
    private DPoint beacon; // 信标的坐标

    public int packX(double x) {
        int panelCenterX = GamePanel.getInstance().getWidth() / 2;
        return panelCenterX - (int) (cLocation.x() - x) - GameSettings.BLOCK_SIZE / 2;
    }

    public int packY(double y) {
        int panelCenterY = GamePanel.getInstance().getHeight() / 2;
        return panelCenterY - (int) (cLocation.y() - y) - GameSettings.BLOCK_SIZE / 2;
    }

    public void setBeacon(DPoint DPoint) {
        beacon.setX(DPoint.x());
        beacon.setY(DPoint.y());
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
        if (cLocation.x() != beacon.x()) {
            if (beacon.x() > cLocation.x()) {
                if (beacon.x() >= cLocation.x()  + GameSettings.CAMERA_SPEED) {
                    cLocation.moveX(GameSettings.CAMERA_SPEED);
                } else {
                    cLocation.setX(beacon.x());
                }
            }
            if (beacon.x() < cLocation.x()) {
                if (beacon.x() <= cLocation.x() - GameSettings.CAMERA_SPEED) {
                    cLocation.moveX(-GameSettings.CAMERA_SPEED);
                } else {
                    cLocation.setX(beacon.x());
                }
            }
        }

        if (cLocation.y() != beacon.y()) {
            if (beacon.y() > cLocation.y()) {
                if (beacon.y() >= cLocation.y()  + GameSettings.CAMERA_SPEED) {
                    cLocation.moveY(GameSettings.CAMERA_SPEED);
                } else {
                    cLocation.setY(beacon.y());
                }
            }
            if (beacon.y() < cLocation.y()) {
                if (beacon.y() <= cLocation.y() - GameSettings.CAMERA_SPEED) {
                    cLocation.moveY(-GameSettings.CAMERA_SPEED);
                } else {
                    cLocation.setY(beacon.y());
                }
            }
        }
    }
}
