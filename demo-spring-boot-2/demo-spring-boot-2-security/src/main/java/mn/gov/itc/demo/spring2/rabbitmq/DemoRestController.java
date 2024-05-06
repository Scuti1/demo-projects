package mn.gov.itc.demo.spring2.rabbitmq;

import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo/security")
public class DemoRestController {

    @GetMapping(value = "authenticated", produces = "text/plain")
    @PreAuthorize("isAuthenticated()")
    public String restAuthenticated() {
        StringBuilder sb = new StringBuilder("Success Authenticated\n\n");
        KeycloakAuthenticationToken authentication = (KeycloakAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        KeycloakSecurityContext cred = (KeycloakSecurityContext) authentication.getCredentials();

        System.out.println("cred.getToken().getOtherClaims().get(\"email\") = " + cred.getToken().getEmail());

        authentication
                .getAuthorities().forEach(a -> sb.append(a.getAuthority() + "\n"));

        sb.append("\n" + authentication.getPrincipal());
        Object credentials = authentication.getCredentials();
        sb.append("\n" + credentials);

        return sb.toString();
    }

    @GetMapping(value = "admin", produces = "text/plain")
//    @PreAuthorize("hasAnyRole('ADMIN')")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
//    @Secured("ROLE_ADMIN")
    public String restAdmin() {
        return "Success Admin";
    }

    @GetMapping(value = "public", produces = "text/plain")
    public String restPublic() {
        return "Success Public";
    }
}
