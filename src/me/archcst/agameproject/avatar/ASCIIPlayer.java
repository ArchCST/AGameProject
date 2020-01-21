/*
 *
 * @author ArchCST
 * Copyright (c) 2020.
 */

package me.archcst.agameproject.avatar;

import java.util.HashMap;

public class ASCIIPlayer {
    private static ASCIIPlayer asciiPlayer = null;
    public static ASCIIPlayer getInstance() {
        if (asciiPlayer == null) {
            synchronized (ASCIIPlayer.class) {
                if (asciiPlayer == null) {
                    asciiPlayer = new ASCIIPlayer();
                }
            }
        }
        return asciiPlayer;
    }

    public HashMap<String, ASCIIMovement> moves = new HashMap<>();

    private ASCIIPlayer() {
        ASCIIMovement move = new ASCIIMovement();
        moves.put("walk", move);
    }

    //
}
