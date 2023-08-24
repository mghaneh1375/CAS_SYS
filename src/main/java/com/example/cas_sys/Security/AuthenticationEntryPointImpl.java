package com.example.cas_sys.Security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl extends BasicAuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx)
            throws IOException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getOutputStream().println("{\"status\": " + HttpServletResponse.SC_UNAUTHORIZED + ", \"message\": \"" + authEx.getMessage() + "\" }");
    }

    @Override
    public void afterPropertiesSet() {
        setRealmName("api-services");
        super.afterPropertiesSet();
    }

}