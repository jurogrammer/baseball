package ui.http;

import ui.exception.UIException;

import java.util.Set;

public enum Action {
    GAME_PAGE(Set.of("/", ""), Method.GET),
    INFER(Set.of("/infer"), Method.POST),
    RESTART(Set.of("/restart"), Method.POST)
    ;


    private final Set<String> urls;
    private final Method method;

    Action(Set<String> urls, Method method) {
        this.urls = urls;
        this.method = method;
    }

    public Set<String> getUrls() {
        return urls;
    }

    public static Action valueOf(String url, Method method) {
        for (Action action : values()) {
            if (action.getUrls().contains(url) && action.method == method) {
                return action;
            }
        }

        throw new UIException(String.format("적절하지 않은 요청입니다. url: %s, method: %s", url, method));
    }
}