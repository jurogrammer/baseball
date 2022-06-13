package ui.clientserver;

import ui.Interactor;

public interface ClientServerUIFactory {
    Interactor createInteractor();

    ClientServerResolver createResolver();
}
