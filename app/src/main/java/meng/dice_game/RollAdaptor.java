package meng.dice_game;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import meng.dice_game.model.DiceRoll;
import meng.dice_game.model.DiceRollHistory;
import meng.dice_game.model.DieItem;

/**
 * Created by meng on 02/03/2018.
 */

public class RollAdaptor extends RecyclerView.Adapter<RollAdaptor.ViewHolder> {

    private List<DiceRoll> mRollList;
    private DiceRollHistory mDiceRollHistory;
    private Context mContext;

    public RollAdaptor(DiceRollHistory diceRollHistory, Context context) {
        this.mDiceRollHistory = diceRollHistory;
        this.mRollList = this.mDiceRollHistory.getRollList();
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.individual_roll, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DiceRoll roll = mRollList.get(position);

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        String reportDate = df.format(roll.getDate());

        holder.mTxtVDate.setText(reportDate);

        holder.mRVRolls.setHasFixedSize(true);
        holder.mRVRolls.setLayoutManager(new GridLayoutManager(mContext, 2));

        DiceAdaptor diceAdapter = new DiceAdaptor(roll.getRollResults(), mContext);
        holder.mRVRolls.setAdapter(diceAdapter);

    }


    @Override
    public int getItemCount() {
        return mRollList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTxtVDate;
        public RecyclerView mRVRolls;

        public ViewHolder(View rollView) {
            super(rollView);
            mTxtVDate = rollView.findViewById(R.id.txtViewRollDate);
            mRVRolls = rollView.findViewById(R.id.rvRolledDice);
        }
    }
}
