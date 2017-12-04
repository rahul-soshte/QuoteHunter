package com.hunterlab.hunter.motiva;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hunter on 26/11/17.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {


    private ArrayList<Category> aDataSet;
    private String aId;
    public CategoryAdapter(ArrayList<Category> dataSet) {
        aDataSet = dataSet;
    }
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        // if (viewType == CHAT_END) {
        //    v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_chat_end, parent, false);
        //  } else {
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_name_row,parent,false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Category category = aDataSet.get(position);
        holder.mTextView.setText(category.getCategory_name());
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
            mTextView = (TextView) itemView.findViewById(R.id.categoryname);
        }
    }

    public void updateList(ArrayList<Category> list){
        aDataSet = list;
        notifyDataSetChanged();
    }
}
