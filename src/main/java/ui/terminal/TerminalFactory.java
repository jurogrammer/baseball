package ui.terminal;

import ui.Interactor;
import ui.Resolver;
import ui.UIFactory;

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
