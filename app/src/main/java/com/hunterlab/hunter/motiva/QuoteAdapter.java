package com.hunterlab.hunter.motiva;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hunter on 17/10/17.
 */

public class QuoteAdapter extends RecyclerView.Adapter<QuoteAdapter.ViewHolder> {
    private ArrayList<String> aDataSet;

    public QuoteAdapter(ArrayList<String> dataSet) {
        aDataSet = dataSet;
    }
    @Override
    public QuoteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;
        // if (viewType == CHAT_END) {
        //    v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_chat_end, parent, false);
        //  } else {
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.quoteslistrow,parent,false);

        return new QuoteAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(QuoteAdapter.ViewHolder holder, int position) {
        String quote = aDataSet.get(position);
        holder.mTextView.setText(quote);
    }

    @Override
    public int getItemCount() {
        return aDataSet.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        ViewHolder(View v) {
            super(v);
            mTextView = (TextView) itemView.findViewById(R.id.quoteTview);
        }
    }
}
