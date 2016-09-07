package com.nitesh.sparksample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.nitesh.spark.customview.SparkRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HeaderFooterSampleActivity extends AppCompatActivity {

    private SparkRecyclerView listView;
    private List newsList;
    private NewsAdapter adapter;

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

        //set Header View
        View view = LayoutInflater.from(this).inflate(R.layout.header_view,null);
        adapter.setHeaderView(view);

        //set Footer View
        View view1 = LayoutInflater.from(this).inflate(R.layout.header_view,null);
        adapter.setFooterView(view1);
    }

    private List getNewsList() {
        List temp = new ArrayList();
        for(int i=0 ;i<20;i++)
            temp.add("Assam Chief Minister Sarbananda Sonowal, who was elected from Majuli in the Assembly elections, today announced several developmental progammes for the constituency which is Asia's largest riverine island.");
        return temp;
    }
}
