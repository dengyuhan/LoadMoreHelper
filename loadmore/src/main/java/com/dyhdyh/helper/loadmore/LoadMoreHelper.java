package com.dyhdyh.helper.loadmore;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.dyhdyh.helper.loadmore.paging.PagingDelegate;

/**
 * @author dengyuhan
 *         created 2017/12/20 15:00
 */
public class LoadMoreHelper {
    private final String TAG = "LoadMoreHelper";

    private PagingDelegate mPagingDelegate;
    private RefreshWrapper mRefreshWrapper;

    protected boolean mLoadMore;
    protected OnLoadMoreListener mOnLoadMoreListener;

    public LoadMoreHelper(RecyclerView rv, RefreshWrapper refreshWrapper, PagingDelegate pagingDelegate) {
        this.mRefreshWrapper = refreshWrapper;
        this.mPagingDelegate = pagingDelegate;
        rv.addOnScrollListener(new OnScrollBottomListener() {
            @Override
            protected void onScrollToBottom() {
                if (mLoadMore) {
                    Log.d(TAG, "加载更多尚未完成");
                } else {
                    if (mOnLoadMoreListener != null) {
                        mLoadMore = true;
                        mOnLoadMoreListener.onLoadMore();
                    }
                }
            }
        });
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.mOnLoadMoreListener = onLoadMoreListener;
    }

    public PagingDelegate getPagingDelegate() {
        return mPagingDelegate;
    }

    public boolean isLoadMore() {
        return mLoadMore;
    }

    public void onLoadMoreComplete() {
        mLoadMore = false;
    }
}
