package com.io.Promo.model;

import java.util.List;

public class Orderlist {
    private List<Order> orderList;

    public Orderlist() {
    }

    public Orderlist(List<Order> orderList) {
        this.orderList = orderList;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
