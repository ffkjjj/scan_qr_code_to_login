package cf.zhul.scanqrcodetologin.common.constant;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class EnvProperties {

    @Value("${user.token}")
    private String userToken;

    @Value("${server.localhost.address}")
    private String serverLocalhostAddress;
}
