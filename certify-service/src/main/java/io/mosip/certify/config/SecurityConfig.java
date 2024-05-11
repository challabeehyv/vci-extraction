/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package io.mosip.certify.config;

import io.mosip.certify.core.config.LocalAuthenticationEntryPoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;


import java.util.List;
import java.util.Map;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Profile(value = {"!test"})
public class SecurityConfig {

    @Autowired
    private LocalAuthenticationEntryPoint localAuthenticationEntryPoint;

    @Value("${server.servlet.path}")
    private String servletPath;

    @Value("#{${mosip.certify.security.auth.post-urls}}")
    private Map<String, List<String>> securePostUrls;

    @Value("#{${mosip.certify.security.auth.put-urls}}")
    private Map<String, List<String>> securePutUrls;

    @Value("#{${mosip.certify.security.auth.get-urls}}")
    private Map<String, List<String>> secureGetUrls;

    @Value("${mosip.esignet.vci.authn.jwk-set-uri}")
    private String jwkSetUri;

    @Value("${mosip.certify.security.ignore-auth-urls}")
    private String[] ignoreAuthUrls;

    @Value("${mosip.certify.security.ignore-csrf-urls}")
    private String[] ignoreCsrfCheckUrls;

    @Bean
    public SecurityFilterChain web(HttpSecurity http) throws Exception {

        http.csrf(httpEntry -> httpEntry.ignoringRequestMatchers(ignoreCsrfCheckUrls)
                .csrfTokenRepository(this.getCsrfTokenRepository()));

        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
                .requestMatchers(ignoreAuthUrls).permitAll()
                .anyRequest().authenticated()
        ).oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt
                        .jwkSetUri(jwkSetUri)
                )
        );
        http.exceptionHandling(exceptionConfigurer -> exceptionConfigurer.authenticationEntryPoint(localAuthenticationEntryPoint));
        http.sessionManagement(sessionConfigurer -> sessionConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        http.csrf(AbstractHttpConfigurer::disable);
//        http.authorizeHttpRequests(request -> request.anyRequest().permitAll());
        return http.build();
    }

    private CsrfTokenRepository getCsrfTokenRepository() {
        CookieCsrfTokenRepository cookieCsrfTokenRepository = CookieCsrfTokenRepository.withHttpOnlyFalse();
        cookieCsrfTokenRepository.setCookiePath("/");
        return cookieCsrfTokenRepository;
    }


    @Bean
    public WebSecurityCustomizer configure() throws Exception {
        //Nullifying security filters on userinfo endpoint.
        //Reason:
        //Even though oidc/** is part of ignore-auth-urls, bearer token is getting validated in the security filters and fails with 401 error.
        //Bearer token of the userinfo endpoint is signed with IDP keys.
        //We currently donot have a way to set 2 different authentication providers in spring security.
        return (web) -> web.ignoring().requestMatchers(servletPath+"/oidc/userinfo", servletPath+"/vci/credential");
    }

}
