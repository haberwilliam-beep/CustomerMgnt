package com.cms.filter;

import com.cms.util.CorrelationIdUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorrelationIdFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String correlationId = httpRequest.getHeader(CorrelationIdUtil.HEADER_NAME);
        if (correlationId == null || correlationId.isEmpty()) {
            correlationId = CorrelationIdUtil.generate();
        }

        CorrelationIdUtil.setCorrelationId(correlationId);
        ThreadContext.put("correlationId", correlationId);
        httpResponse.setHeader(CorrelationIdUtil.HEADER_NAME, correlationId);

        try {
            chain.doFilter(request, response);
        } finally {
            CorrelationIdUtil.clear();
            ThreadContext.remove("correlationId");
        }
    }
}
