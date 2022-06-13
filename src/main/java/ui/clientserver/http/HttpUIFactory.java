package ui.clientserver.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import ui.Interactor;
import ui.clientserver.ClientServerResolver;
import ui.clientserver.ClientServerUIFactory;
import ui.reactive.ReactiveUIFactory;

public class HttpUIFactory implements ClientServerUIFactory {
    @Override
    public Interactor createInteractor() {
        return new HttpInteractor();
    }

    @Override
    public ClientServerResolver createResolver() {
        return new HttpResolver(new ObjectMapper(), new RequestParser());
    }
}
