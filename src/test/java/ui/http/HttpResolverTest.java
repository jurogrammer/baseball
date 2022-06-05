package ui.http;

import org.junit.jupiter.api.Test;

class HttpResolverTest {

    @Test
    void startMessage() {
        HttpResolver httpResolver = new HttpResolver();
        System.out.println("startMessage = " + httpResolver.startMessage());
    }
}