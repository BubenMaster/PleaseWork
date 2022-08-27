package com.yoj.collections.level3.part10.classes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


import java.util.List;

@XmlRootElement
@XmlType(name = "shop")
public class Shop {

    @XmlElement(name = "goods")
    public Goods goods;

    @XmlElement(name = "count", type = Integer.class)
    public int count;

    @XmlElement(name = "profit", type = Double.class)
    public double profit;

    @XmlElement(name = "secretData")
    public String[] secretData;



    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public void setCount(int count) {
        this.count = count;
    }


    public void setProfit(double profit) {
        this.profit = profit;
    }

    public void setSecretData(String[] secretData) {
        this.secretData = secretData;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "goods=" + goods +
                ", count=" + count +
                ", profit=" + profit +
                ", secretData=" + secretData +
                '}';
    }


        public static class Goods{

        @XmlElement(name = "names")
        public List<String> items;

            public Goods(List<String> items) {
                this.items = items;
            }

            @Override
            public String toString() {
                return "Goods{" +
                        "items=" + items +
                        '}';
            }
        }
}
