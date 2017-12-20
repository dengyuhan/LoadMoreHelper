package com.dyhdyh.helper.loadmore.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dyhdyh.helper.loadmore.example.adapter.ExampleAdapter;

public class RecyclerViewActivity extends AppCompatActivity {
    RecyclerView rv;

    private ExampleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        rv = findViewById(R.id.rv);

        mAdapter = new ExampleAdapter(ExampleData.textData("Item"));
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(mAdapter);
    }

}
