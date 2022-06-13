package ui.reactive.terminal;

import ui.Interactor;
import ui.reactive.ReactiveResolver;
import ui.reactive.ReactiveUIFactory;

public class TerminalFactory implements ReactiveUIFactory {

    @Override
    public Interactor createInteractor() {
        return new TerminalInteractor();
    }

    @Override
    public ReactiveResolver createResolver() {
        return new TerminalResolver();
    }
}
