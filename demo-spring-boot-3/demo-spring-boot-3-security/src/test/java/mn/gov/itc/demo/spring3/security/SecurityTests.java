package mn.gov.itc.demo.spring3.security;

import mn.gov.itc.demo3.security.DemoSecurityConfig;
import mn.gov.itc.demo3.security.Security3Application;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {Security3Application.class, DemoSecurityConfig.class})
@WebMvcTest
class SecurityTests {

    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private MockMvc mockMvc;

    private String getToken(String username, String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("username", username);
        params.add("password", password);
        params.add("client_id", "demo-spring-boot-2-security");

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);
        ResponseEntity<Map> response = restTemplate.exchange("https://st.auth.itc.gov.mn/auth/realms/demo/protocol/openid-connect/token", HttpMethod.POST, entity, Map.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        return response.getBody().get("access_token").toString();
    }

    @Test
    void rest_Public_Success() throws Exception {
        this.mockMvc.perform(
                        get("/demo/security/public")
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void rest_Authenticated_Success() throws Exception {
        this.mockMvc.perform(
                        get("/demo/security/authenticated")
                                .header("Authorization", "Bearer " + getToken("demo1", "demo1@123"))
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void rest_Authenticated_NoToken() throws Exception {
        this.mockMvc.perform(
                        get("/demo/security/authenticated")
                )
                .andDo(print())
                .andExpect(result -> Assertions.assertNotEquals(status(), 200));
    }

    @Test
    void rest_Authenticated_WrongToken() throws Exception {
        this.mockMvc.perform(
                        get("/demo/security/authenticated")
                                .header("Authorization", "Bearer " + getToken("demo1", "demo1@123") + "Q")
                )
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    void rest_Admin_Success() throws Exception {
        this.mockMvc.perform(
                        get("/demo/security/admin")
                                .header("Authorization", "Bearer " + getToken("demo1", "demo1@123"))
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void rest_Admin_Not_Admin() throws Exception {
        this.mockMvc.perform(
                        get("/demo/security/admin")
                                .header("Authorization", "Bearer " + getToken("demo2", "demo2@123"))
                )
                .andDo(print())
                .andExpect(status().isForbidden());
    }

}
