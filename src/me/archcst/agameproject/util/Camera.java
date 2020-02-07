/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.util;

import me.archcst.agameproject.avatar.Player;
import me.archcst.agameproject.map.Location;
import me.archcst.agameproject.ui.GamePanel;

public class Camera {
    private static Camera camera = null;

    private Camera() {
//        cameraThread = new Thread(this);
        beacon = new Location(Player.getInstance().getLocation().x() - 12,
                Player.getInstance().getLocation().y());
        location = new Location(beacon);
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

    private Location location; // 相机的指向
    private Location beacon; // 信标的坐标

    public int packX(double x) {
        int panelCenterX = GamePanel.getInstance().getWidth() / 2;
        return panelCenterX - (int) (location.x() - x) - GameSettings.BLOCK_SIZE / 2;
    }

    public int packY(double y) {
        int panelCenterY = GamePanel.getInstance().getHeight() / 2;
        return panelCenterY - (int) (location.y() - y) - GameSettings.BLOCK_SIZE / 2;
    }

    public void setBeacon(Location location) {
        beacon.setX(location.x());
        beacon.setY(location.y());
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
        if (location.x() != beacon.x()) {
            if (beacon.x() > location.x()) {
                if (beacon.x() >= location.x()  + GameSettings.CAMERA_SPEED) {
                    location.moveX(GameSettings.CAMERA_SPEED);
                } else {
                    location.setX(beacon.x());
                }
            }
            if (beacon.x() < location.x()) {
                if (beacon.x() <= location.x() - GameSettings.CAMERA_SPEED) {
                    location.moveX(-GameSettings.CAMERA_SPEED);
                } else {
                    location.setX(beacon.x());
                }
            }
        }

        if (location.y() != beacon.y()) {
            if (beacon.y() > location.y()) {
                if (beacon.y() >= location.y()  + GameSettings.CAMERA_SPEED) {
                    location.moveY(GameSettings.CAMERA_SPEED);
                } else {
                    location.setY(beacon.y());
                }
            }
            if (beacon.y() < location.y()) {
                if (beacon.y() <= location.y() - GameSettings.CAMERA_SPEED) {
                    location.moveY(-GameSettings.CAMERA_SPEED);
                } else {
                    location.setY(beacon.y());
                }
            }
        }
    }
}
