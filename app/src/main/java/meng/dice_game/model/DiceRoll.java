package meng.dice_game.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by meng on 01/03/2018.
 */
public class DiceRoll {
    private UUID mId;
    private List<DieItem> mRollResults;

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    private Date mDate;


    public DiceRoll() {
        mId = UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public List<DieItem> getRollResults() {
        return mRollResults;
    }

    public void setRollResults(List<DieItem> rollResults) {
        mRollResults = rollResults;
    }

}
