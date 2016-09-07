package com.nitesh.sparksample;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.nitesh.spark.customview.SparkRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LoadMoreSampleActivity extends AppCompatActivity {

    private SparkRecyclerView listView;
    private List newsList;
    private NewsAdapter adapter;
    private ArrayList temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (SparkRecyclerView) findViewById(R.id.listView);
        listView.setLayoutManager(new LinearLayoutManager(this));

        newsList = getNewsList();
        adapter = new NewsAdapter(this, newsList);
        listView.setAdapter(adapter);

        listView.addOnItemClickListener(new SparkRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //TODO Perform action on Item click
            }
        });

        listView.addLoadMoreListener(new SparkRecyclerView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                loadMoreData();
            }
        });

    }

    private List getNewsList() {
        temp = new ArrayList();
        for(int i=0 ;i<20;i++)
            temp.add("Assam Chief Minister Sarbananda Sonowal, who was elected from Majuli in the Assembly elections, today announced several developmental progammes for the constituency which is Asia's largest riverine island.");
        return temp;
    }


    public void loadMoreData(){
        //This is just an Prototype for API call
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Suppose here API completed , add data
                for(int i=0 ;i<4;i++)
                    temp.add("Assam Chief Minister Sarbananda Sonowal, who was elected from Majuli in the Assembly elections, today announced several developmental progammes for the constituency which is Asia's largest riverine island.");
                adapter.notifyDataSetChanged();
                listView.setDataLoadingFromServerCompleted(); //This should be definetly called after adapter.notifyDataSetChanged();
            }
        },2000);
    }
}
