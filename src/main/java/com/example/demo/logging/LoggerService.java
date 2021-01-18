package com.example.demo.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class LoggerService {

    private static final Logger LOG = LoggerFactory.getLogger("RestLogger");

    public void request(HttpServletRequest request) {
        StringBuilder requestLogMessage = new StringBuilder();

        requestLogMessage.append("\n*** REQUEST ************************************************************************\n")
                .append("* IP      : ").append(request.getRemoteAddr()).append("\n")
                .append("* URI     : ").append(request.getRequestURI()).append("\n")
                .append("* METHOD  : ").append(request.getMethod()).append("\n")
                .append("* QUERY   : ").append(request.getQueryString() != null ? request.getQueryString() : "<none>").append("\n")
                .append("* HEADERS : ").append(getHeaders(request)).append("\n")
                .append("************************************************************************************\n");

        LOG.info(requestLogMessage.toString());
    }

    public void response() {
        StringBuilder responseLogMessage = new StringBuilder();

        responseLogMessage.append("*** RESPONSE ************************************************/n")
                .append("* REQUEST URI")
                .append("* STATUS")
                .append("* HEADERS")
                .append("* BODY")
                .append("\n************************************************************");

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
//private String getHeaders(HttpServletRequest request) {
//    final var headers = new HashMap<String, String>();
//    request.getHeaderNames().asIterator()
//            .forEachRemaining(headerName -> headers.put(headerName, request.getHeader(headerName)));
//    return headers.keySet().stream().map(key -> key + "=" + headers.get(key))
//            .collect(Collectors.joining("\n", "{\n", "\n}"));
//}

}
