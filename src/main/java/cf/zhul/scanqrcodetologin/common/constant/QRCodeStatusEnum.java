package cf.zhul.scanqrcodetologin.common.constant;

public enum QRCodeStatusEnum {
    INIT(0),
    SUCCESS(1),
    EXPIRE(2),
    FAIL(-1);

    int status;

    QRCodeStatusEnum(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
