package cf.zhul.scanqrcodetologin.common.constant;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    API;

    @Override
    public String getAuthority() {
        return name();
    }
}
