package ui;

import ui.interactor.Interactor;
import ui.resolver.Resolver;

public interface UIFactory {
    Interactor createInteractor();
    Resolver createResolver();
}
