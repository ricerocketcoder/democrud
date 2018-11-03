package com.gary.demo.crud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtClaimsSetVerifier;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.util.matcher.RequestMatcher;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

@Configuration
@EnableResourceServer
public class ResourceConfig extends ResourceServerConfigurerAdapter {
    @Value("${security.oauth2.resource.id}")
    private String resourceId;

    //@Autowired
    //private DefaultTokenServices tokenServices;

    //@Autowired
    //private TokenStore tokenStore;
    @Autowired
    private SecretKeyProvider keyProvider;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.DELETE).access("hasAnyRole('ADMIN')")
                .antMatchers("/gary/demo/crud/v1/person").access("hasAnyRole('USER')");
    }


    private static class OAuthRequestedMatcher implements RequestMatcher {
        public boolean matches(HttpServletRequest request) {
            String auth = request.getHeader("Authorization");
            // Determine if the client request contained an OAuth Authorization
            boolean haveOauth2Token = (auth != null) && auth.startsWith("Bearer");
            boolean haveAccessToken = request.getParameter("access_token")!=null;
            return haveOauth2Token || haveAccessToken;
        }
    }

    //setting up the bean to share the same keystore
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(resourceId)
                .tokenServices(tokenServices());
    }




    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setTokenEnhancer(accessTokenConverter());
        return defaultTokenServices;
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        try {
            converter.setSigningKey(keyProvider.getKey());
            converter.setJwtClaimsSetVerifier(customJwtClaimVerifier());
        } catch (URISyntaxException | KeyStoreException | NoSuchAlgorithmException | IOException | UnrecoverableKeyException | CertificateException e) {
            e.printStackTrace();
        }

        return converter;
    }



    @Bean
    public JwtClaimsSetVerifier customJwtClaimVerifier() {
        return new CustomClaimVerifier();
    }
}