package youyanzu.seu.restaurant02.utils;

import youyanzu.seu.restaurant02.entity.Result;
import youyanzu.seu.restaurant02.enums.ResultEnum;

/**
 * @author CoderXshuai
 * @date 2023/3/16 21:42
 * @description 返回工具类
 */

public class ResultUtil {
    /**
     * 返回成功
     *
     * @param data 返回数据
     * @param <T>  返回数据类型
     * @return Result 返回对象
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "success", data, 1);
    }

    /**
     * 返回成功
     *
     * @param data  返回数据
     * @param count 返回数据条数
     * @param <T>   返回数据类型
     * @return Result 返回对象
     */
    public static <T> Result<T> success(T data, int count) {
        return new Result<>(200, "success", data, count);
    }

    /**
     * 返回失败
     *
     * @param code 状态码
     * @param msg  返回信息
     * @param <T>  返回数据类型
     * @return Result 返回对象
     */
    public static <T> Result<T> error(int code, String msg) {
        return new Result<>(code, msg, null, 0);
    }

    /**
     * 返回失败
     *
     * @param resultEnum 返回枚举
     * @param <T>        返回数据类型
     * @return Result 返回对象
     */
    public static <T> Result<T> error(ResultEnum resultEnum) {
        return new Result<>(resultEnum.getCode(), resultEnum.getMsg(), null, 0);
    }
}
