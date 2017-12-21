package com.dyhdyh.helper.loadmore.example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dengyuhan
 *         created 2017/12/20 14:24
 */
public class ExampleData {


    public static List<String> textData(String tag) {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < ExamplePagingDelegate.DEFAULT_PAGECOUNT; i++) {
            data.add(tag + i);
        }
        return data;
    }
}
