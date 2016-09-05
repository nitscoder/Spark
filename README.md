# Spark
This Project mainly aims to provide easy way to do the things, hiding complexity in Android and mainly speed/spark the development. It is very helpful and time reducing.



Download
--------

Grab via Maven:
```xml
<dependency>
  <groupId>com.nitscoder</groupId>
  <artifactId>spark</artifactId>
  <version>0.0.1_beta</version>
</dependency>
```
or Gradle:
```groovy
compile 'com.nitscoder:spark:0.0.1_beta'
```
#How to Use

Defining SparkRecyclerView in xml
```xml
<com.nitesh.spark.customview.SparkRecyclerView
        android:id="@+id/list"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>
```
Adapter should extend <b>SparkRecyclerView.EasyAdapter</b> for Example : 
 
```xml
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nitesh.spark.customview.SparkRecyclerView;

import java.util.List;

public class MoviesAdapter extends SparkRecyclerView.EasyAdapter {
 
    private List<String> list;

    public MoviesAdapter(Context context,List list) {
        super(context,list);
    }

    @Override
    protected void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder)holder).title.setText(getList().get(position).toString());
    }

    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent, int position) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.string_list_row,null);
        return new MyViewHolder(view);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.text);
        }
    }

}
```
Code Sample from Activity or Fragment : 

```xml
//get spark recycler view
SparkRecyclerView recyView = (SparkRecyclerView) findViewById(R.id.list);
adapter = new MoviesAdapter(this,list);

//set headerview if need
adapter.setHeaderView(header);
LinearLayoutManager manager = new LinearLayoutManager(this);
recyView.setLayoutManager(manager);
recyView.setAdapter(adapter);
//set onItemClickListener if need
recyView.addOnItemClickListener(this);
//set LoadMoreListener if need , This should be called after setAdapter
recyView.addLoadMoreListener(this);
```
License
=======

    Copyright 

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

