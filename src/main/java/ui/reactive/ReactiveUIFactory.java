package ui.reactive;

import ui.Interactor;

public interface ReactiveUIFactory {
    Interactor createInteractor();

    ReactiveResolver createResolver();
}
