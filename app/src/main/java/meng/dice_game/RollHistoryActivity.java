package meng.dice_game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import meng.dice_game.model.DiceRollHistory;

public class RollHistoryActivity extends AppCompatActivity {

    private DiceRollHistory mDiceRollHistory;
    private RecyclerView mRecyclerView;
    private ImageButton mBtnBack;
    private RollAdaptor mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roll_history);
        mDiceRollHistory = DiceRollHistory.get(this);

        //Initialising the recycler view
        mRecyclerView = findViewById(R.id.rVHistory);
        mRecyclerView.removeAllViews();
        //All items have the same size
        mRecyclerView.setHasFixedSize(true);
        //One column in the grid
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RollAdaptor(mDiceRollHistory, this);
        mRecyclerView.setAdapter(mAdapter);


        mBtnBack = findViewById(R.id.btnBack);
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


}
