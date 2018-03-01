package meng.dice_game;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import meng.dice_game.model.DieItem;

/**
 * Created by meng on 01/03/2018.
 */

public class DiceAdaptor extends RecyclerView.Adapter<DiceAdaptor.ViewHolder> {

    private List<DieItem> mDiceList;
    private Context mContext;

    public DiceAdaptor(List<DieItem> mDiceList, Context context) {
        this.mDiceList = mDiceList;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.individual_die, parent, false);
        int width = parent.getMeasuredWidth();
        return new ViewHolder(view, width);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DieItem die = mDiceList.get(position);
        switch (die.getValue()){
            case 0:
                holder.mDie.setBackgroundResource(R.drawable.d1);
                break;
            case 1:
                holder.mDie.setBackgroundResource(R.drawable.d2);
                break;
            case 2:
                holder.mDie.setBackgroundResource(R.drawable.d3);
                break;
            case 3:
                holder.mDie.setBackgroundResource(R.drawable.d4);
                break;
            case 4:
                holder.mDie.setBackgroundResource(R.drawable.d5);
                break;
            default:
                holder.mDie.setBackgroundResource(R.drawable.d6);
        }

    }

    @Override
    public int getItemCount() {
        return mDiceList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mDie;

        public ViewHolder(View dieView, int width) {
            super(dieView);
            mDie = dieView.findViewById(R.id.imgDie);
            if (dieView.getId()==mDiceList.size()){
                mDie.getLayoutParams().width = width;
            }

        }
    }


}

