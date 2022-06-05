package ui.http;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseBuilderTest {

    @Test
    public void builderTest() {
        ResponseBuilder responseBuilder = new ResponseBuilder();
        String build = responseBuilder
                .ok()
                .addHeader("Content-Type", "text/html; charset=utf-8")
                .body("hello, world!")
                .build();
        System.out.println("build = \n" + build);
        System.out.println("=== = ");
    }

}