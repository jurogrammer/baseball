package ui;

import ui.interactor.Interactor;
import ui.interactor.TerminalInteractor;
import ui.resolver.Resolver;
import ui.resolver.TerminalResolver;

public class TerminalFactory implements UIFactory {

    @Override
    public Interactor createInteractor() {
        return new TerminalInteractor();
    }

    @Override
    public Resolver createResolver() {
        return new TerminalResolver();
    }
}
