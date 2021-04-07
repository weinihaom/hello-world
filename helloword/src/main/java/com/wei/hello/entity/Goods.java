package com.wei.hello.entity;

import java.math.BigDecimal;

/**
 * Created by weiwei on 2021/4/3.
 *
 * @author weiwei
 */
public class Goods {
    /**
     * 商品Id
     */
    private Integer Id;
    /**
     * 商品名字
     */
    private String goodsName;
    /**
     * 商品价格
     */
    private BigDecimal prise;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public BigDecimal getPrise() {
        return prise;
    }

    public void setPrise(BigDecimal prise) {
        this.prise = prise;
    }

}
