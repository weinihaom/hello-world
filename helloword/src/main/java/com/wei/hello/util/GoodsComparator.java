package com.wei.hello.util;

import com.wei.hello.entity.Goods;

import java.util.Comparator;

/**
 * Created by weiwei on 2021/4/3.
 *
 * @author weiwei
 */
public class GoodsComparator implements Comparator<Goods> {

    @Override
    public int compare(Goods o1, Goods o2) {
        return o1.getPrise().compareTo(o2.getPrise());
    }
}
