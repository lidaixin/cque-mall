package cn.edu.cque.mall.entity;

import lombok.Data;

/**
 * @ClassName InfoResult
 * @Description 用于传递简单信息的类
 * @Author YoungWinter
 * @Date 2020/9/24 9:51
 * @Version 1.0
 **/
@Data
public class InfoResult {

    // 编码
    private int code;
    // 消息
    private String info;

    public InfoResult() {
    }

    public InfoResult(int code, String info) {
        this.code = code;
        this.info = info;
    }

    public static InfoResult ERROR(String info) {
        return new InfoResult(0, info);
    }

    public static InfoResult OK() {
        return new InfoResult(1, "成功");
    }

    public static InfoResult OK(String info) {
        return new InfoResult(1, info);
    }


}
