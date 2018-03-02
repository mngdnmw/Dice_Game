package meng.dice_game.model;

import java.io.Serializable;

/**
 * Created by meng on 01/03/2018.
 */

public class DieItem implements Serializable {
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
