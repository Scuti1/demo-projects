package mn.gov.itc.demo3.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableWebSecurity
public class DemoSecurityConfig {

    private final KeycloakJwtConverter jwtConverter;

    public DemoSecurityConfig(KeycloakJwtConverter jwtConverter) {
        this.jwtConverter = jwtConverter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.NEVER));
        http.authorizeHttpRequests(registry -> registry.anyRequest().permitAll());
        http.oauth2ResourceServer(oauth2ResourceServerCustomizer -> oauth2ResourceServerCustomizer.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtConverter)));
        return http.build();
    }
}
