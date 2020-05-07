package cf.zhul.scanqrcodetologin.service;

import cf.zhul.scanqrcodetologin.common.constant.EnvProperties;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;

@Service
public class QRCodeService {

    private final EnvProperties evnProperties;

    public QRCodeService(EnvProperties evnProperties) {
        this.evnProperties = evnProperties;
    }

    public String createQRCodeWithBase64(String content) throws IOException {
        int width = 200;
        int height = 200;
        if (!StringUtils.isEmpty(content)) {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            @SuppressWarnings("rawtypes")
            HashMap<EncodeHintType, Comparable> hints = new HashMap<>();
            // 指定字符编码为“utf-8”
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            // 指定二维码的纠错等级为中级
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
            // 设置图片的边距
            hints.put(EncodeHintType.MARGIN, 2);

            try {
                QRCodeWriter writer = new QRCodeWriter();
                BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, width, height, hints);

                BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
                ImageIO.write(bufferedImage, "png", os);
                /*
                 * 原生转码前面没有 data:image/png;base64 这些字段，返回给前端是无法被解析，可以让前端加，也可以在下面加上
                 */
                return "data:image/png;base64," + Base64.getEncoder().encodeToString(os.toByteArray());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    /**
     * 由于没有App进行扫码并在请求上加入 token, 所以使用手机自带相机进行扫码.
     * 所以使用此方法对返回的二维码内容进行封装, 包装成手机可直接跳转的 url 链接.
     * 实际业务中, app 可以直接拿二维码中的 token 封装在请求中使用(根据具体认证逻辑进行使用)
     *
     * @param uuid uuid
     * @return 包装后的内容
     */
    public String wrapQRCodeWithLoginUrl(String uuid) {
        return String.format("http://%s/qrcode/login/%s/%s",
                evnProperties.getServerLocalhostAddress(), uuid, evnProperties.getUserToken());
    }
}
