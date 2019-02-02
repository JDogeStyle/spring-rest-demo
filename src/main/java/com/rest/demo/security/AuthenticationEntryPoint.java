package com.rest.demo.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.demo.exception.ResponseError;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        response.setHeader("WWW-Authenticate", "Basic realm=\"" + getRealmName() + "\"");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

        PrintWriter writer = response.getWriter();
        String error = "The resource owner could not be authenticated due to missing or invalid credentials.";
        ResponseError responseError = new ResponseError(HttpStatus.UNAUTHORIZED, authException.getLocalizedMessage(), error);
        writer.println(objectMapper.writeValueAsString(responseError));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setRealmName("Realm");
        super.afterPropertiesSet();
    }

}