package us.hk.bdwm.api.core;

public class ThreadMeta {

    private String board;

    private String title;

    private String url;

    public ThreadMeta(String board, String title, String url) {
        this.board = board;
        this.title = title;
        this.url = url;
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
}
