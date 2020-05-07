package cf.zhul.scanqrcodetologin.exception;

import cf.zhul.scanqrcodetologin.common.constant.MyHttpCodeEnum;
import lombok.Getter;

@Getter
public class TokenNotFoundException extends HttpBaseException {

    public TokenNotFoundException() {
        this.code = MyHttpCodeEnum.TOKEN_ERROR.getCode();
        this.msg = MyHttpCodeEnum.TOKEN_ERROR.getMsg();
    }
}
