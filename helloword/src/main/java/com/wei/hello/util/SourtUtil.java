package com.wei.hello.util;

import com.wei.hello.entity.Goods;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by weiwei on 2021/4/3.
 *
 * @author weiwei
 */
public class SourtUtil {
    public static void sortTest(String[] args) {
        List<Goods> goodsList = new ArrayList(10);
        Goods goods;
        for (int i = 0; i < 10; i++) {
            goods = new Goods();
            goods.setId(i);
            goods.setGoodsName("商品" + i);
            goods.setPrise(new BigDecimal(Math.random() * 100));
            goodsList.add(i, goods);
        }
        sourt(goodsList);
        for (int i = 0; i < goodsList.size(); i++) {
            System.out.println(goodsList.get(i).getGoodsName() + ":" + goodsList.get(i).getPrise());
        }
        System.out.println("==============\n==========");
        compares(goodsList);
        for (int i = 0; i < goodsList.size(); i++) {
            System.out.println(goodsList.get(i).getGoodsName() + ":" + goodsList.get(i).getPrise());
        }
    }

    /**
     * 冒泡排序
     *
     * @param goodsList
     * @return
     */
    public static List<Goods> sourt(List<Goods> goodsList) {
        for (int i = 0; i < goodsList.size() - 1; i++) {
            for (int j = 0; j < goodsList.size() - 1 - i; j++) {
                if (!compareForMax(goodsList.get(j), goodsList.get(j + 1))) {
                    Goods tmp = goodsList.get(j + 1);
                    goodsList.set(j + 1, goodsList.get(j));
                    goodsList.set(j, tmp);
                }
            }
        }
        return goodsList;
    }

    /**
     * 比较商品价格,第一个是否比第二个大
     *
     * @param goodsOne
     * @param goodsTwo
     * @return
     */
    public static boolean compareForMax(Goods goodsOne, Goods goodsTwo) {
        if (goodsTwo == null) {
            return true;
        }
        if (goodsOne == null) {
            return false;
        }
        BigDecimal tmp = goodsOne.getPrise().max(goodsTwo.getPrise());
        if (goodsOne.getPrise().equals(tmp)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 继承类集合camparator 排序
     *
     * @param goodsList
     */
    public static void compare(List<Goods> goodsList) {
        GoodsComparator comparator = new GoodsComparator();
        Collections.sort(goodsList, comparator);
    }

    /**
     * 接口集合camparator 排序
     *
     * @param goodsList
     */
    public static void compares(List<Goods> goodsList) {
        Collections.sort(goodsList, new Comparator<Goods>() {
            @Override
            public int compare(Goods o1, Goods o2) {
                return o1.getPrise().compareTo(o2.getPrise());
            }
        });
    }

    /**
     * 数组camparator 排序
     *
     * @param t1
     */
    public static int[] comparator(int[] t1) {
        Arrays.sort(t1);
        return t1;
    }

    /**
     * 数组camparator 排序
     */
    public static void comparatorTest() {
        int[] t1 = new int[]{1, 87, 43, 2, 3, 4, 5, 6, 7, 8, 3, 9};
        int[] t2 = comparator(t1);
        for (int i = 0; i < t2.length; i++) {
            System.out.println("第" + (i+1) + "个：" + t2[i]);
        }
        System.out.println("总共" + t1.length + "个。");
    }

    public static void main(String[] args) {
        comparatorTest();
    }
}
