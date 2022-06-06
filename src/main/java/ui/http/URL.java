package ui.http;

public enum URL {
    INDEX_1("/"),
    INDEX_2(""),
    INFER("/infer");

    private final String url;

    URL(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public static boolean containURL(String url) {
        for (URL existURL : values()) {
            if (existURL.getUrl().equals(url)) {
                return true;
            }
        }
        return false;
    }
}