package ui.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import ui.clientserver.http.HttpResolver;
import ui.clientserver.http.RequestParser;

class HttpResolverTest {

    @Test
    void startMessage() {
        HttpResolver httpResolver = new HttpResolver(new ObjectMapper(), new RequestParser());
        System.out.println("startMessage = " + httpResolver.startMessage());
    }
}