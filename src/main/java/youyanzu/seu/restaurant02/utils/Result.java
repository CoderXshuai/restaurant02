package youyanzu.seu.restaurant02.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @param <T> 返回对象类型
 * @author CoderXshuai
 * @date 2023/3/16 21:08
 * @description: 通用返回对象
 */
@Data
@AllArgsConstructor
public class Result<T> {
    /**
     * 状态码
     */
    private int code;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 返回数据
     */
    private T data;
    /**
     * 返回数据条数
     */
    private int count;
}
