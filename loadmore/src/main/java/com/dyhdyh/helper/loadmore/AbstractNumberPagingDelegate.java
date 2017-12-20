package com.dyhdyh.helper.loadmore;

/**
 * @author dengyuhan
 *         created 2017/12/20 11:34
 */
public abstract class AbstractNumberPagingDelegate implements PagingDelegate {
    public static final int DEFAULT_START_PAGE = 0;
    private int mPage;

    public AbstractNumberPagingDelegate() {
        resetPage();
    }

    @Override
    public int getStartPage() {
        return DEFAULT_START_PAGE;
    }

    @Override
    public void resetPage() {
        mPage = getStartPage();
    }

    @Override
    public void nextPage() {
        mPage++;
    }

    @Override
    public int getPage() {
        return mPage;
    }
}
