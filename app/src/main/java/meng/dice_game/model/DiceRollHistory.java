package meng.dice_game.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by meng on 01/03/2018.
 */

public class DiceRollHistory {
    private static DiceRollHistory sDiceRollHistory;
    private List<DiceRoll> mRollList;


    public static DiceRollHistory get(Context context) {
        if (sDiceRollHistory == null) {
            sDiceRollHistory = new DiceRollHistory(context);
        }
        return sDiceRollHistory;
    }

    private DiceRollHistory(Context context) {
        mRollList = new ArrayList<>();

    }

    public List<DiceRoll> getRollList() {
        return mRollList;
    }

    public void setRollList(List<DiceRoll> rollList) {
        mRollList = rollList;
    }

    public DiceRoll getRoll(UUID id) {
        for (DiceRoll roll : mRollList) {
            if (roll.getId().equals(id)) {
                return roll;
            }
        }
        return null;
    }
}
