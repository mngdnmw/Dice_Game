package meng.dice_game;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.Serializable;

import meng.dice_game.model.DiceRollHistory;

public class RollHistoryActivity extends AppCompatActivity {

    private DiceRollHistory mDiceRollHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roll_history);
        mDiceRollHistory = DiceRollHistory.get(this);

    }


}
