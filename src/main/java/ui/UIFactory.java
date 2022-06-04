package ui;

public interface UIFactory {
    Interactor createInteractor();
    Resolver createResolver();
}
