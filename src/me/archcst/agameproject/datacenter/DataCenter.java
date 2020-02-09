/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.datacenter;

import me.archcst.agameproject.avatar.*;
import me.archcst.agameproject.avatar.monsters.Monster;
import me.archcst.agameproject.avatar.monsters.MonsterFactory;
import me.archcst.agameproject.avatar.weapons.Bullet;
import me.archcst.agameproject.avatar.weapons.Weapon;
import me.archcst.agameproject.avatar.weapons.Weapon_Player;
import me.archcst.agameproject.avatar.weapons.Weapon_Monster;
import me.archcst.agameproject.map.DPoint;
import me.archcst.agameproject.map.GameMap;
import me.archcst.agameproject.util.Camera;
import me.archcst.agameproject.util.CollisionBox;
import me.archcst.agameproject.util.GameSettings;
import me.archcst.agameproject.util.Target;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class DataCenter {
    private static DataCenter dataCenter = null;
    private GameMap gameMap; // 游戏地图
    private Player player; // 玩家
    private ArrayList<Monster> monsters; // 所有怪物
    public Vector<Bullet> playBullets; // 所有的玩家子弹
    public Vector<Bullet> monsterBullets; // 所有的怪物子弹
    private long startTime; // 启动游戏的时间

    public static DataCenter getInstance() {
        if (dataCenter == null) {
            dataCenter = new DataCenter();
        }
        return dataCenter;
    }

    private DataCenter() {
        Random r = GameSettings.r;
        gameMap = GameMap.getInstance();
        player = Player.getInstance();
        monsters = new ArrayList<>();
        playBullets = new Vector<>();
        monsterBullets = new Vector<>();
        Weapon weapon = new Weapon_Player();
        weapon.setAvatar(player);

        // 生成怪物
        for (int i = 0; i < 10; i++) {
            Monster monster = MonsterFactory.createMonster(r.nextInt(MonsterFactory.MONSTER_TYPE_AMOUNT));
            monster.initLocation();
            Weapon monsterWeapon = new Weapon_Monster();
            monsterWeapon.setAvatar(monster);
            monsters.add(monster);
        }
        startTime = System.currentTimeMillis();
    }

    public void drawFrame(Graphics g) {
        // 更新摄像机坐标
        Camera.getInstance().updateCamera();

        // 画地图
        gameMap.draw(g);

        // 画怪物
        if (monsters.size() > 0) {
            for (Monster m:monsters) {
                m.draw(g);
            }
        }

        // 画玩家
        player.draw(g);
        Target.getInstance().draw(g);

        // 画玩家子弹
        if (playBullets.size() > 0) {
            for (Bullet b:playBullets) {
                b.draw(g);
            }
        }

        // 画怪物子弹
        if (monsterBullets.size() > 0) {
            for (Bullet b:monsterBullets) {
                b.draw(g);
            }
        }


        // 测试画出地图所有碰撞箱
        if (GameSettings.DEV_MODE) {
            // 画出地图所有碰撞箱
            if (GameSettings.DEV_SHOW_MAP_COLLISION_BOX) {
                for (CollisionBox cb:gameMap.getAllMapCollisionBox()) {
                    cb.draw(g);
                }
            }
            // 画出beacon位置
            if (GameSettings.DEV_SHOW_BEACON) {
                Camera camera = Camera.getInstance();
                DPoint beacon = camera.getBeacon();

                g.setColor(Color.MAGENTA);
                g.fillRect(camera.packX(beacon.x() - 2), camera.packY(beacon.y() - 2), 5, 5);
            }

        }
    }

    public void gameProcess(Graphics g) {
        // 游戏胜利
        if (monsters.size() == 0) {
            win();
        }

        // 玩家移动控制
        player.moveCtrl.move(g);

        // 玩家武器控制
        player.attack();

        // 怪物移动和射击控制
        for (Monster m:monsters) {
            m.moveCtrl.move(g);
            m.attack();
        }


        // 玩家子弹位移
        // todo 有没有必要套if？foreach中如果size为0是不是不会执行循环体？
        if (playBullets.size() > 0) {
            for (int i = playBullets.size() - 1; i >= 0 ; i--) {
                Bullet b = playBullets.get(i);
                // 计算下一个坐标
                b.nextLocation();
                // 如果碰墙，移除子弹
                if (b.hitWall()) {
                    playBullets.remove(i);
                    continue;
                }
                // 计算是否碰撞到怪物
                for (int j = monsters.size() - 1; j >=0 ; j--) {
                    Monster m = monsters.get(j);
                    // 怪物的受击体为它的碰撞箱
                    CollisionBox box = m.getCollisionBox();
                    if (b.hitBox(box)) {
                        // 怪物掉血
                        m.changeHp(-b.getDamage());
                        if (m.getHp() == 0) {
                            m.die(); // 怪物死亡函数
                            monsters.remove(m);
                        }
                        playBullets.remove(b);
                        break;
                    }
                }
            }
        }

        // 怪物子弹位移
        if (monsterBullets.size() > 0) {
            for (int i = monsterBullets.size() - 1; i>=0; i--) {
                // 计算下一个坐标
                Bullet b = monsterBullets.get(i);
                b.nextLocation();
                // 如果碰墙，移除子弹
                if (b.hitWall()) {
                    monsterBullets.remove(i);
                    continue;
                }
                // 计算是否碰撞到玩家
                if (b.hitBox(player.getAttackBox())) {
                    player.changeHp(-b.getDamage());
                    monsterBullets.remove(b);
                }
            }
        }

        // 玩家怪物的碰撞伤害
        for (Monster m: monsters) {
            if (m.getCollisionBox().equals(player.getAttackBox())) {
                // 碰撞怪物扣血10
                player.changeHp(-10);
            }

        }

        // 更新玩家的目标
        Target.getInstance().refreshTarget();
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public void gameOver() {
        System.out.println("游戏失败");
    }

    private void win() {
        System.out.println("游戏胜利");
        long recode = System.currentTimeMillis() - startTime; // 用时
    }
}
