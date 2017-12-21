package com.dyhdyh.helper.loadmore.example;

import com.dyhdyh.helper.loadmore.paging.AbstractNumberPagingDelegate;

/**
 * @author dengyuhan
 *         created 2017/12/20 11:30
 */
public class ExamplePagingDelegate extends AbstractNumberPagingDelegate {
    public static final int DEFAULT_PAGECOUNT = 12;

    @Override
    public int getPageCount() {
        return DEFAULT_PAGECOUNT;
    }

    @Override
    public boolean isExistNextPage() {
        //假设第三页是最后一页
        if (getPage() == 3) {
            return false;
        }
        return true;
    }

}
