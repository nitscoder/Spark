package com.nitesh.sparksample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nitesh.spark.customview.SparkRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nitesh Singh(killer) on 9/7/2016.
 */
public class NewsAdapter extends SparkRecyclerView.EasyAdapter {

    public NewsAdapter(Context context, List list) {
        super(context, list);
    }

    @Override
    protected void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder)holder).textView.setText(((ArrayList<String>)getList()).get(position));
    }

    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,null);
        return new MyViewHolder(view);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}
