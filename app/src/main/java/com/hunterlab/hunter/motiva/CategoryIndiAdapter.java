package com.hunterlab.hunter.motiva;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL;

/**
 * Created by hunter on 3/12/17.
 */

public class CategoryIndiAdapter extends RecyclerView.Adapter<CategoryIndiAdapter.ViewHolder> {
    private ArrayList<String> aDataSet;
    private Context context;

    public CategoryIndiAdapter(ArrayList<String> dataSet) {
        aDataSet = dataSet;
    }

    @Override
    public CategoryIndiAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;
        // if (viewType == CHAT_END) {
        //    v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_chat_end, parent, false);
        //  } else {
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_indi_layout,parent,false);
        context=parent.getContext();
        return new CategoryIndiAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CategoryIndiAdapter.ViewHolder holder, int position) {
        String quote = aDataSet.get(position);
        //StringBuilder stringBuilder=new StringBuilder();
       // stringBuilder.append("\"");
        //stringBuilder.append(quote);
        //stringBuilder.append("\"");
        //Toast.makeText(context,quote,Toast.LENGTH_SHORT).show();
       // holder.setText(stringBuilder.toString());
        Glide.with(holder.Imageview.getContext())
                .load(quote)
                .into(holder.Imageview);

    }

    @Override
    public int getItemCount() {
        return aDataSet.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView Imageview;

        ViewHolder(View v) {
            super(v);
            Imageview = (ImageView) itemView.findViewById(R.id.hello);
        }
    }
}
