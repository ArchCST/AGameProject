/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar.monsters;

public class MonsterFactory {
    public static final int MONSTER_TYPE_AMOUNT = 3;
    public static Monster createMonster(int type, int level) {
        Monster monster = null;
        switch (type) {
            case 0:
                monster = new Monster_BulletSpeed(level);
                break;
            case 1:
                monster = new Monster_Damage(level);
                break;
            case 2:
                monster = new Monster_WalkSpeed(level);
                break;
        }

        return monster;
    }
}
