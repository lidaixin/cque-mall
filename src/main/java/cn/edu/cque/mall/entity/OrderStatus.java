package cn.edu.cque.mall.entity;

import lombok.Getter;

/**
 * @ClassName OrderStatue
 * @Description
 * @Author YoungWinter
 * @Date 2020/9/25 15:35
 * @Version 1.0
 **/
@Getter
public enum OrderStatus {
    NON_PAYMENT(1, "未付款"),
    PAYMENT(2, "已付款");

    private int code;
    private String info;

    OrderStatus(int code, String info) {
        this.code = code;
        this.info = info;
    }
}
