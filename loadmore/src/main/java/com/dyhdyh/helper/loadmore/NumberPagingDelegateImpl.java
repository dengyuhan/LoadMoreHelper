package com.dyhdyh.helper.loadmore;

/**
 * @author dengyuhan
 *         created 2017/12/20 11:30
 */
public class NumberPagingDelegateImpl extends AbstractNumberPagingDelegate {

    @Override
    public int getPageCount() {
        return 0;
    }

    @Override
    public boolean isExistNextPage() {
        return false;
    }

    @Override
    public boolean isLastPage() {
        return false;
    }
}
