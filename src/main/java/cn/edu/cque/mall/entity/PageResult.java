package cn.edu.cque.mall.entity;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @ClassName PageResult
 * @Description 分页结果对象
 * @Author YoungWinter
 * @Date 2020/9/23 8:51
 * @Version 1.0
 **/
@Data
@ToString
public class PageResult<T> {
    private List<T> list;
    private int totalPage;
    private int totalNumber;
    private int currentPage;
}
