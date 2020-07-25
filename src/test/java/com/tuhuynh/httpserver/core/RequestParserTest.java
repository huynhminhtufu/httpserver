package com.tuhuynh.httpserver.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tuhuynh.httpserver.core.RequestBinder.HttpResponse;
import com.tuhuynh.httpserver.core.RequestBinder.RequestContext;

public class RequestParserTest {
    @Test
    @DisplayName("Parse Request Test")
    void parseRequestTest() {
        String[] request = {"GET /test HTTP/1.1", "Host: localhost", "User-Agent: Mozilla/5.0"};
        String body = "SampleBody";
        RequestContext context = RequestParser.parseRequest(request, body);
        assertEquals("/test", context.getPath(), "Get Path");
        assertEquals("Mozilla/5.0", context.getHeader().get("user-agent"), "Get Header");
        assertEquals("SampleBody", context.getBody(), "Get Body");
    }

    @Test
    @DisplayName("Parse Response Test")
    void parseResponseTest() {
        HttpResponse response = HttpResponse.of("Hello World");
        assertEquals(200, response.getHttpStatusCode(), "Get HTTP Status Code");
        String responseString = RequestParser.parseResponse(response);
        assertEquals("HTTP/1.1 200 OK\n\nHello World\n", responseString, "Get response string");
    }
}
