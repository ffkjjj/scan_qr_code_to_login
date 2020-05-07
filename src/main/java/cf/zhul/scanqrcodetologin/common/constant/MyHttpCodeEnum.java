package cf.zhul.scanqrcodetologin.common.constant;

import lombok.Getter;

/**
 * http 请求的业务状态码
 * @author zhulei
 */
@Getter
public enum MyHttpCodeEnum {

    SUCCESS(0, "success"),

    TOKEN_ERROR(101, "token 错误");

    int code;
    String msg;

    MyHttpCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
