package com.dyhdyh.helper.loadmore;

/**
 * @author dengyuhan
 *         created 2017/12/20 11:25
 */
public interface PagingDelegate {
    int getStartPage();

    void resetPage();

    void nextPage();

    int getPage();

    int getPageCount();

    boolean isExistNextPage();

    boolean isLastPage();
}
