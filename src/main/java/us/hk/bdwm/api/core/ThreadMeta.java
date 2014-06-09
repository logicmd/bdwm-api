package us.hk.bdwm.api.core;

public class ThreadMeta {

    private String board;

    private String title;

    private String url;

    private String apiUrl;

    public ThreadMeta(String board, String title, String url, String apiUrl) {
        this.board = board;
        this.title = title;
        this.url = url;
        this.apiUrl = apiUrl;

    }

    public String getBoard() {
        return board;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getApiUrl() {
        return  apiUrl;
    }
}
