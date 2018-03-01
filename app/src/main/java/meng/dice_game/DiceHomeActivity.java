package meng.dice_game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.ArrayList;

import meng.dice_game.model.DieItem;

public class DiceHomeActivity extends AppCompatActivity {

    private final Integer MAX_NO_OF_DICE = 6;
    private ImageButton mHistoryButton;
    private Spinner mNoOfDice;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<DieItem> mDieItemList = new ArrayList<>();
    private static final String STATE_ITEMS = "DICE_ITEMS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_home);

        if (savedInstanceState != null) {
            savedInstanceState.getSerializable(STATE_ITEMS);
        }

        //Populate the spinner
        mNoOfDice = findViewById(R.id.noOfDice);
        setNoOfDice();

        //Places a listener on the dice selection spinner
        diceSelection();

        //Initialising the recycler view
        mRecyclerView = findViewById(R.id.rvDiceArea);
        //All items have the same size
        mRecyclerView.setHasFixedSize(true);
        //One columns in the grid
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        drawDice();


    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putSerializable(STATE_ITEMS, mDieItemList);
    }


    private void setNoOfDice() {
        //Makes a new int array that is the max number of dice that can be used
        Integer[] diceNo = new Integer[MAX_NO_OF_DICE];
        for (int d = 0; d < MAX_NO_OF_DICE; d++) {
            diceNo[d] = d + 1;
        }

        //Puts the array that contains the number of dice that can be used into an adapter
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, diceNo);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mNoOfDice.setAdapter(adapter);

    }

    private void diceSelection() {

        mNoOfDice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                int num = Integer.parseInt(parentView.getItemAtPosition(position).toString());
                addingDiceToList(num);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // TODO
            }

        });
    }

    private void drawDice() {

        if (mDieItemList == null) {
            //Set only one die in the array to be put in the grid because default selection if 1 die in spinner
            addingDiceToList(1);
        } else {

            mRecyclerView.removeAllViews();

            GridLayoutManager layoutManager;

            //Portrait
            if (getResources().getConfiguration().orientation == 1) {
                layoutManager = new GridLayoutManager(this, 2);
            }
            else{
                layoutManager = new GridLayoutManager(this, 3);
            }

            //If it's odd number of dice chosen, then last spans the whole two columns
            //TODO can't get to work atm
//            if (mDieItemList.size() % 2 != 0) {
//                layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//                    @Override
//                    public int getSpanSize(int position) {
//                        return mDieItemList.size() - position == 0 ? 2 : 1;
//                    }
//                });
            mRecyclerView.setLayoutManager(layoutManager);
//            }
        }

        mAdapter = new DiceAdaptor(mDieItemList, this);
        mRecyclerView.setAdapter(mAdapter);

    }

    private void addingDiceToList(int noOfDice) {
        if (!mDieItemList.isEmpty()) {
            mDieItemList.clear();
        }
        for (int i = 0; i < noOfDice; i++) {
            DieItem oneDie = new DieItem();
            oneDie.setValue(i);
            mDieItemList.add(oneDie);
        }
        drawDice();
    }
}
