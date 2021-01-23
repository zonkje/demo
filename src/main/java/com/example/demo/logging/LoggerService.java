package com.example.demo.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class LoggerService {

    private static final Logger LOG = LoggerFactory.getLogger("RestLogger");

    public void request(HttpServletRequest request, Object body) {
        StringBuilder requestLogMessage = new StringBuilder();

        requestLogMessage.append("\n*** REQUEST ************************************************************************\n")
                .append("* IP      : ").append(request.getRemoteAddr()).append("\n")
                .append("* URI     : ").append(request.getRequestURI()).append("\n")
                .append("* METHOD  : ").append(request.getMethod()).append("\n")
                .append("* QUERY   : ").append(request.getQueryString() != null ? request.getQueryString() : "<none>").append("\n")
                .append("* HEADERS : ").append(getHeaders(request)).append("\n")
                .append(body!=null ? "* BODY        : "+"\n\t"+body+"\n" : "")
                .append("************************************************************************************\n");

        LOG.info(requestLogMessage.toString());
    }

    public void response(ServletServerHttpResponse response, ServletServerHttpRequest request, Object responseBody) {
        StringBuilder responseLogMessage = new StringBuilder();
        final var servletResponse = response.getServletResponse();

        /*
        ISSUE:
        - In Postman I can see ~17 headers in response.
        - response (type ServletServerHttpResponse) return empty collection
        - servletResponse (type HttpServletResponse) return 6 headers with key "Vary"
        and values "Origin" for all of them (which doesn't match Postman's output)
        */

        responseLogMessage.append("\n*** RESPONSE ************************************************************************\n")
                .append("* REQUEST URI : ").append(request.getURI()).append("\n")
                .append("* STATUS CODE : ").append(servletResponse.getStatus()).append("\n")
                .append("* HEADERS     : ").append(getHeaders(servletResponse)).append("\n")
                .append(responseBody!=null ? "* BODY        : "+"\n\t"+responseBody+"\n" : "")
                .append("************************************************************************************\n");


        LOG.info(responseLogMessage.toString());
    }

    private String getHeaders(HttpServletRequest request) {
        StringBuilder headers = new StringBuilder();
        request.getHeaderNames().asIterator().forEachRemaining(headerName -> headers.append("\n")
                .append("\t- ")
                .append(headerName.toUpperCase())
                .append(" : ")
                .append(request.getHeader(headerName))
        );
        return headers.toString();
    }
    private String getHeaders(HttpServletResponse response) {
        StringBuilder headers = new StringBuilder();
        response.getHeaderNames().forEach(headerName -> headers.append("\n")
                .append("\t- ")
                .append(headerName.toUpperCase())
                .append(" : ")
                .append(response.getHeader(headerName)));
        return headers.toString();
    }
}
