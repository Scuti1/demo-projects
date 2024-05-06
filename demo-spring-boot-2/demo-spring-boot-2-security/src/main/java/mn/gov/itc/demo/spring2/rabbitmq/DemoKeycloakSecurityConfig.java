package mn.gov.itc.demo.spring2.rabbitmq;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

/**
 * Keycloak болон Spring Security хэрхэн хоршиж ажиллах заавар бүхий тохиргооны класс.
 *
 * @KeycloakConfiguration annotation уг класс нь keyclaok-н тохиргооны класс гэдэгийг тодорхойлно.
 *
 * @EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) тусламжтайгаар @PreAuth болон @Secure
 * annotation-уудыг ажиллах боломжтой болгно.
 *
 * @Import(KeycloakSpringBootConfigResolver.class) тусламжтайгаар keycloak-ийн өөрийн тохиргоог дуудаж өгнө.
 *
 * KeycloakWebSecurityConfigurerAdapter класс нь keycloak-н үндсэн суурь тохиргоог хийдэг бөгөөд шаардлагатай тохиргоог
 * override хийх байдлаар дахин тодорхойлох шаардлагатай байдаг.
 */
@KeycloakConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Import(KeycloakSpringBootConfigResolver.class)
public class DemoKeycloakSecurityConfig extends KeycloakWebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        KeycloakAuthenticationProvider authenticationProvider = new KeycloakAuthenticationProvider();
        SimpleAuthorityMapper grantedAuthoritiesMapper = new SimpleAuthorityMapper();
        grantedAuthoritiesMapper.setConvertToUpperCase(true);
        authenticationProvider.setGrantedAuthoritiesMapper(grantedAuthoritiesMapper);
        auth.authenticationProvider(authenticationProvider);
    }

    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new NullAuthenticatedSessionStrategy();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http
                .authorizeRequests().anyRequest().permitAll();
    }
}
