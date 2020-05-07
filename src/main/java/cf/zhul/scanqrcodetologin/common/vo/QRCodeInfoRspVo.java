package cf.zhul.scanqrcodetologin.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QRCodeInfoRspVo {

    private String info;

    private String uuid;

    private String token;
}
