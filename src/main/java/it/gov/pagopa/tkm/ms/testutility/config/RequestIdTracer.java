package it.gov.pagopa.tkm.ms.testutility.config;

import org.springframework.beans.factory.annotation.*;
import brave.*;
import org.springframework.core.annotation.*;
import org.springframework.lang.*;
import org.springframework.stereotype.*;
import org.springframework.web.filter.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

@Component
@Order(1)
public class RequestIdTracer extends OncePerRequestFilter {

    @Autowired
    private Tracer tracer;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        servletResponse.setHeader("Request-Id", tracer.currentSpan().context().traceIdString());
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
