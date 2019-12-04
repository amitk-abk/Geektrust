package com.gt.cricket.game;

import com.gt.cricket.player.Player;

public interface Batsman {

    String playsDelivery();
    boolean isOnStrike();
    void rotateStrike();
    void setOffStrike();

    default Player player() {
        return (Player)this;
    }
}
