package cf.zhul.scanqrcodetologin.common.util;

import cf.zhul.scanqrcodetologin.common.constant.MyHttpCodeEnum;
import cf.zhul.scanqrcodetologin.exception.HttpBaseException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    private static final int SUCCESS = MyHttpCodeEnum.SUCCESS.getCode();
    private static final int FAIL = -1;

    private int code = MyHttpCodeEnum.SUCCESS.getCode();
    private String msg = MyHttpCodeEnum.SUCCESS.getMsg();
    private T data;

    public Result(Boolean success, String msg) {
        code = (success == null || !success) ? FAIL : SUCCESS;
        this.msg = msg;
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(T data) {
        this.data = data;
    }

    public Result(Throwable e) {
        this.msg = e.getMessage();
        this.code = FAIL;
    }

    public static<T> Result<T> of(T data) {
        return new Result<>(data);
    }

    public static <T> Result<T> of(boolean success, String msg) {
        return new Result<T>(success, msg);
    }

    public static <T> Result<T> of(Throwable e) {
        return new Result<T>(e);
    }

    public static <T> Result<T> success() {
        return new Result<>(true, null);
    }

    public static Result<?> fail(String msg) {
        return new Result<>(false, msg);
    }

    public static <T> Result<T> fail(HttpBaseException e) {
        return new Result<T>(e.getCode(), e.getMsg());
    }

    public static Result<?> fail(MyHttpCodeEnum myHttpCodeEnum) {
        return new Result<>(myHttpCodeEnum.getCode(), myHttpCodeEnum.getMsg(), null);
    }
}