package cf.zhul.scanqrcodetologin.config;

import cf.zhul.scanqrcodetologin.filter.ResultExceptionTranslationFilter;
import cf.zhul.scanqrcodetologin.filter.TokenAuthenticationFilter;
import cf.zhul.scanqrcodetologin.filter.TokenAuthenticationProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@Order(1)
public class TokenSecurityConfigurer extends WebSecurityConfigurerAdapter {

    private final TokenAuthenticationFilter tokenAuthenticationFilter;
    private final TokenAuthenticationProvider tokenAuthenticationProvider;
    private final ResultExceptionTranslationFilter resultExceptionTranslationFilter;

    public TokenSecurityConfigurer(TokenAuthenticationFilter tokenAuthenticationFilter,
                                   TokenAuthenticationProvider tokenAuthenticationProvider,
                                   ResultExceptionTranslationFilter resultExceptionTranslationFilter) {
        this.tokenAuthenticationFilter = tokenAuthenticationFilter;
        this.tokenAuthenticationProvider = tokenAuthenticationProvider;
        this.resultExceptionTranslationFilter = resultExceptionTranslationFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(tokenAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/api/v1/**")
                .addFilterAfter(tokenAuthenticationFilter, BasicAuthenticationFilter.class)
                .addFilterAfter(resultExceptionTranslationFilter, ExceptionTranslationFilter.class)
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
