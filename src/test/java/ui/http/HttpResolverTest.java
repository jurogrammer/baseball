package ui.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class HttpResolverTest {

    @Test
    void startMessage() {
        HttpResolver httpResolver = new HttpResolver(new ObjectMapper(), new RequestParser());
        System.out.println("startMessage = " + httpResolver.startMessage());
    }
}