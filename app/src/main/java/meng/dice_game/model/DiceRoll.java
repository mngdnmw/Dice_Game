package meng.dice_game.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by meng on 01/03/2018.
 */
public class DiceRoll {
    private UUID mId;
    private List<Integer> mRollResults;
    private Date mDate;


    public DiceRoll() {
        mId = UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public List<Integer> getRollResults() {
        return mRollResults;
    }

    public void setRollResults(List<Integer> rollResults) {
        mRollResults = rollResults;
    }

}
