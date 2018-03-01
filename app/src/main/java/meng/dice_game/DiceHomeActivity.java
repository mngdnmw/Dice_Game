package meng.dice_game;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Random;

import meng.dice_game.model.DiceRoll;
import meng.dice_game.model.DiceRollHistory;
import meng.dice_game.model.DieItem;

public class DiceHomeActivity extends AppCompatActivity {

    private final Integer MAX_NO_OF_DICE = 6;
    private ImageButton mBtnHistory;
    private Spinner mNoOfDice;
    private Button mBtnRoll;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<DieItem> mDieItemList;
    private ArrayList<DiceRoll> mDiceRolls;
    private DiceRollHistory mDiceRollHistory;
    private static final String STATE_ITEMS = "DICE_ITEMS";
    private int mNumRolls = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mDieItemList = new ArrayList<>();
        mDiceRolls = new ArrayList<>();
        mDiceRollHistory = DiceRollHistory.get(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_home);

        //Saves the list of dice chosen as a serialisable
        if (savedInstanceState != null) {
            savedInstanceState.getSerializable(STATE_ITEMS);
        }

        //Linking history button to history activity
        mBtnHistory = findViewById(R.id.btnHistory);
        mBtnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DiceHomeActivity.this, RollHistoryActivity.class);
                startActivity(intent);
            }
        });
        if (0 >= mNumRolls)
            mBtnHistory.setEnabled(false);

        mBtnRoll = findViewById(R.id.btnRoll);
        mBtnRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickRoll();
            }
        });

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

    private void clickRoll() {
        if (!mBtnHistory.isEnabled()) {
            mBtnHistory.setEnabled(true);
        }

        mBtnRoll.setEnabled(false);


        Runnable runnable = new Runnable() {
            Handler handler = new Handler();
            int count = 0;
            @Override
            public void run() {

                if(count==6)
                {
                    mBtnRoll.setEnabled(true);
                    addNewRollToHistory();

                    return;
                }
                else {
                    for (int i = 0; i < mDieItemList.size(); i++) {
                        int roll = new Random().nextInt(6) + 1;
                        DieItem diceRoll = new DieItem();
                        diceRoll.setValue(roll);
                        mDieItemList.set(i, diceRoll);
                        updateDiceGrid();
                    }
                    handler.postDelayed(this, 75L);
                    count++;
                }

            }

        };
        runnable.run();


    }

    private void addNewRollToHistory(){

    }

    private void updateDiceGrid() {
        mAdapter = new DiceAdaptor(mDieItemList, this);
        mRecyclerView.setAdapter(mAdapter);
    }


//    private void roll(int one, int two) {
//        mNumRolls++;
//
//        DiceRoll result = new DiceRoll();
//        result.setResult1(one);
//        result.setResult2(two);
//
//        // Calculates the rolls
//        int roll1 = result.getResult1();
//        int roll2 = result.getResult2();
//        int sum = roll1+roll2;
//
//        // Adds a TextView to the LinearLayout of the history
//        TextView tView = new TextView(this);
//        tView.setText("Roll " + numRolls + ": " + roll1 + " + " + roll2 + " Sum: " + sum);
//        listHistory.addView(tView);
//
//        // Vibrates the device
//        Vibrator vibrate = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
//        vibrate.vibrate(250);
//    }

    private void setDice(int number, ImageView imageView) {
        switch (number) {
            case 1:
                imageView.setImageResource(R.drawable.d1);
                break;
            case 2:
                imageView.setImageResource(R.drawable.d2);
                break;
            case 3:
                imageView.setImageResource(R.drawable.d3);
                break;
            case 4:
                imageView.setImageResource(R.drawable.d4);
                break;
            case 5:
                imageView.setImageResource(R.drawable.d5);
                break;
            case 6:
                imageView.setImageResource(R.drawable.d6);
                break;
            default:
                break;
        }
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


    //Add the number of dice chosen to the spinner
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
            } else {
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
