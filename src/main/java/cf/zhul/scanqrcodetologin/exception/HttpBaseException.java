package cf.zhul.scanqrcodetologin.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class HttpBaseException extends RuntimeException {

    protected int code;

    protected String msg;
}
