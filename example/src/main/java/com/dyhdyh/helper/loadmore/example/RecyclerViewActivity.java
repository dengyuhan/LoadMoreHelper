package com.dyhdyh.helper.loadmore.example;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.dyhdyh.helper.loadmore.LoadMoreHelper;
import com.dyhdyh.helper.loadmore.OnLoadMoreListener;
import com.dyhdyh.helper.loadmore.example.adapter.ExampleAdapter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RecyclerViewActivity extends AppCompatActivity {
    RecyclerView rv;
    SwipeRefreshLayout refresh;

    private ExampleAdapter mAdapter;
    private LoadMoreHelper mLoadMoreHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        rv = findViewById(R.id.rv);
        refresh = findViewById(R.id.refresh);
        mLoadMoreHelper = new LoadMoreHelper(rv, new ExamplePagingDelegate());

        mAdapter = new ExampleAdapter(ExampleData.textData("Item "));
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(mAdapter);


        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //刷新要重置页码
                mLoadMoreHelper.getPagingDelegate().resetPage();
                Log.d("---------------->", "刷新");
                requestData();
            }
        });

        mLoadMoreHelper.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                Log.d("---------------->", "加载更多");
                requestData();
            }
        });
    }

    /**
     * 模拟请求数据
     */
    private void requestData() {
        Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(ObservableEmitter<List<String>> e) throws Exception {
                Thread.sleep(2000);

                int page = mLoadMoreHelper.getPagingDelegate().getPage();
                e.onNext(ExampleData.textData(page + " - Item "));
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> newData) throws Exception {
                        if (refresh.isRefreshing()) {
                            mAdapter.getData().clear();
                            mAdapter.getData().addAll(newData);
                            mAdapter.notifyDataSetChanged();

                            mLoadMoreHelper.getPagingDelegate().nextPage();

                            refresh.setRefreshing(false);
                        } else if (mLoadMoreHelper.isLoadMore()) {
                            int positionStart = mAdapter.getData().size();
                            mAdapter.getData().addAll(newData);
                            mAdapter.notifyItemRangeInserted(positionStart, newData.size());

                            mLoadMoreHelper.getPagingDelegate().nextPage();

                            mLoadMoreHelper.onLoadMoreComplete();
                        }


                    }
                });
    }

}
