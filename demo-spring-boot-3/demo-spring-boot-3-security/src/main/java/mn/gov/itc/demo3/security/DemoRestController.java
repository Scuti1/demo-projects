package mn.gov.itc.demo3.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication
                .getAuthorities().forEach(a -> sb.append(a.getAuthority()).append("\n"));

        sb.append("\n").append(authentication.getPrincipal());
        Object credentials = authentication.getCredentials();
        sb.append("\n").append(credentials);

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
