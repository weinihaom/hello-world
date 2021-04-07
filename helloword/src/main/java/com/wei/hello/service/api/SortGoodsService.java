package com.wei.hello.service.api;

import com.wei.hello.entity.Goods;

import java.util.List;

/**
 * Created by weiwei on 2021/4/3.
 *
 * @author weiwei
 */
public interface SortGoodsService {
    /**
     * 商品
     * @param param
     * @return
     */
    List<Goods> sortgoods(List<Goods> param);
}
