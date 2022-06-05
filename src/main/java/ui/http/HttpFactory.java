package ui.http;

import ui.Interactor;
import ui.Resolver;
import ui.UIFactory;

public class HttpFactory implements UIFactory {
    @Override
    public Interactor createInteractor() {
        return new HttpInteractor();
    }

    @Override
    public Resolver createResolver() {
        return new HttpResolver();
    }
}
