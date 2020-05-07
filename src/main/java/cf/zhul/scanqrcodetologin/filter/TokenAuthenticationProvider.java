package cf.zhul.scanqrcodetologin.filter;

import cf.zhul.scanqrcodetologin.common.constant.EnvProperties;
import cf.zhul.scanqrcodetologin.exception.TokenNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    private final EnvProperties evnProperties;

    public TokenAuthenticationProvider(EnvProperties evnProperties) {
        this.evnProperties = evnProperties;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication.isAuthenticated()) {
            return authentication;
        }

        // 从 TokenAuthentication 中获取 token
        String token = authentication.getCredentials().toString();
        if (StringUtils.isEmpty(token)) {
            return authentication;
        }

        // 假装 token 不正确的情况
        if (!token.equals(evnProperties.getUserToken())) {
            throw new TokenNotFoundException();
        }

        TokenAuthentication user = new TokenAuthentication(token);

        // 返回新的认证信息，带上 token 和反查出的用户信息
        Authentication auth = new PreAuthenticatedAuthenticationToken(user, token, user.getAuthorities());
        auth.setAuthenticated(true);
        return auth;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return (TokenAuthentication.class.isAssignableFrom(aClass));
    }
}