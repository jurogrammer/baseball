package ui.http;

import org.junit.jupiter.api.Test;
import ui.clientserver.http.Response;

class ResponseBuilderTest {

    @Test
    public void builderTest() {
        String build = Response
                .builder()
                .ok()
                .addHeader("Content-Type", "text/html; charset=utf-8")
                .body("hello, world!")
                .build()
                .toString();
        System.out.println("build = \n" + build);
        System.out.println("=== = ");
    }

}