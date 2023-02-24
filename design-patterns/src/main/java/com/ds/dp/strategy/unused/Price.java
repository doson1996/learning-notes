package com.ds.dp.strategy.unused;

/**
 * @Author ds
 * @Date 2021/3/30 16:53
 * @Description 价格管理
 */
public class Price {

    /**
     * 报价，对不同的客户，计算不同的报价
     * @param goodsPrice
     * @param customerType
     * @return
     */
    public double quote(double goodsPrice,int customerType){
        double price = goodsPrice;

        switch (customerType){
            case CustomerType.ORDINARY_CUSTOMER:
                System.out.println("普通客户没有折扣");
                break;

            case CustomerType.OLD_CUSTOMER:

                /**
                 *  System.out.println("老客户打9折");
                 *  price = price * 0.9d;
                 *
                 *  优化，每个方法都提取出来，写成这样过后，某个算法发生变化，直接修改方法就行。
                 *
                 *  缺点：有很多计算方式的时候，这个类会非常庞大，难以维护，维护和扩展都要修改已有代码
                 */
                price = calcPriceForOld(price);
                break;

            case CustomerType.NEW_CUSTOMER:
                System.out.println("新客户打8折");
                price = price * 0.8d;
                break;

            case CustomerType.IMPORTANT_CUSTOMERS:
                System.out.println("新用户打7折");
                price = price * 0.7d;
                break;

            default:
                System.out.println("其余情况不打折");

        }

        return price;
    }

    /**
     * 计算老客户价格
     * @param price
     * @return
     */
    private double calcPriceForOld(double price){
        System.out.println("老客户打9折");
        return price * 0.9d;
    }
}
