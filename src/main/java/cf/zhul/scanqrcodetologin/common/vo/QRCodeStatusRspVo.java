package cf.zhul.scanqrcodetologin.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QRCodeStatusRspVo {

    private int status;

    private String token;
}
