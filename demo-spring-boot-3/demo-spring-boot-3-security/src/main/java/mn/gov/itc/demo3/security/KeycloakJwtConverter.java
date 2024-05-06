package mn.gov.itc.demo3.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class KeycloakJwtConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    @Value("${demo.keycloak.client-id}")
    private String clientId;
    @Value("${demo.keycloak.principle}")
    private String principleClaim;

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {

        return new JwtAuthenticationToken(jwt, convertResourceRoles(jwt), getPrinciple(jwt));
    }

    private String getPrinciple(Jwt jwt) {
        return jwt.getClaim(principleClaim);
    }

    private Collection<? extends GrantedAuthority> convertResourceRoles(Jwt jwt) {
        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
        if (resourceAccess == null) return List.of();

        Map<String, Object> resource = (Map<String, Object>) resourceAccess.get(clientId);
        if (resource == null) return List.of();

        List<String> roles = (List<String>) resource.get("roles");
        if (roles == null) return List.of();
        return roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase(Locale.ROOT))).collect(Collectors.toList());
    }
}
