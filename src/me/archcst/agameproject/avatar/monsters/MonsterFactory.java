/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar.monsters;

public class MonsterFactory {
    public static final int MONSTER_TYPE_AMOUNT = 3;
    public static Monster createMonster(int type) {
        Monster monster = null;
        switch (type) {
            case 0:
                monster = new Monster_Slime(1, 1);
                break;
            case 1:
                monster = new Monster_Mice(1 ,1);
                break;
            case 2:
                monster = new Monster_Face(1, 1);
                break;
        }

        return monster;
    }
}
