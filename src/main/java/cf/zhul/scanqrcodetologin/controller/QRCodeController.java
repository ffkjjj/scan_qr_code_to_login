package cf.zhul.scanqrcodetologin.controller;

import cf.zhul.scanqrcodetologin.common.constant.EnvProperties;
import cf.zhul.scanqrcodetologin.common.constant.QRCodeStatusEnum;
import cf.zhul.scanqrcodetologin.common.util.Result;
import cf.zhul.scanqrcodetologin.common.vo.QRCodeInfoRspVo;
import cf.zhul.scanqrcodetologin.common.vo.QRCodeStatusRspVo;
import cf.zhul.scanqrcodetologin.service.QRCodeService;
import cf.zhul.scanqrcodetologin.service.RedisMockService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/qrcode")
public class QRCodeController {

    private final RedisMockService redisMockService;

    private final QRCodeService qrCodeService;

    private final EnvProperties envProperties;

    public QRCodeController(QRCodeService qrCodeService, RedisMockService redisMockService, EnvProperties envProperties) {
        this.qrCodeService = qrCodeService;
        this.redisMockService = redisMockService;
        this.envProperties = envProperties;
    }

    @RequestMapping("/new/base64")
    public Result<QRCodeInfoRspVo> createQRCode() {
        String uuid = UUID.randomUUID().toString();
        redisMockService.put(uuid, null);
        String qrCodeWithBase64;
        String content = qrCodeService.wrapQRCodeWithLoginUrl(uuid);
        try {
            qrCodeWithBase64 = qrCodeService.createQRCodeWithBase64(content);
        } catch (IOException e) {
            return Result.of(e);
        }
        return Result.of(new QRCodeInfoRspVo(qrCodeWithBase64, uuid, envProperties.getUserToken()));
    }

    @RequestMapping("/status/{uuid}")
    public Result<?> queryStatus(@PathVariable("uuid") String uuid) {
        QRCodeStatusRspVo rsp = new QRCodeStatusRspVo();
        // 加入更多状态的判断
        int status;
        if (redisMockService.containsValueWithKey(uuid)) {
            rsp.setStatus(QRCodeStatusEnum.SUCCESS.getStatus());
            rsp.setToken(redisMockService.get(uuid));
            // 删除缓存中的认证信息, 防止重复使用
            redisMockService.delete(uuid);
        } else {
            rsp.setStatus(QRCodeStatusEnum.INIT.getStatus());
        }
        return Result.of(rsp);
    }

    @RequestMapping("/login/{uuid}/{token}")
    public Result<?> loginFromQRCode(@PathVariable("uuid") String uuid, @PathVariable("token") String token) {
        redisMockService.put(uuid, token);
        return Result.success();
    }
}
