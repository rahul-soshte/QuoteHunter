package com.hunterlab.hunter.motiva;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hunter on 12/10/17.
 */

public class AuthorAdapter extends RecyclerView.Adapter<AuthorAdapter.ViewHolder> {


    private ArrayList<Author> aDataSet;
    private String aId;
    public AuthorAdapter(ArrayList<Author> dataSet) {
        aDataSet = dataSet;
    }
    @Override
    public AuthorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
       // if (viewType == CHAT_END) {
        //    v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_chat_end, parent, false);
      //  } else {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrowauthors,parent,false);

        return new ViewHolder(v);
    }
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Author author = aDataSet.get(position);
            holder.mTextView.setText(author.getAuthorname());
        }

        @Override
        public int getItemCount() {
            return aDataSet.size();
        }

        /**
         * Inner Class for a recycler view
         */
        class ViewHolder extends RecyclerView.ViewHolder {
            TextView mTextView;

            ViewHolder(View v) {
                super(v);
                mTextView = (TextView) itemView.findViewById(R.id.textAuthor);
            }
        }

    public void updateList(ArrayList<Author> list){
        aDataSet = list;
        notifyDataSetChanged();
    }
}
