package us.hk.bdwm.api.core;

public class Post {

    private String content;

    public Post(String rawContent) {
        content = rawContent;
    }

    public String getContent() {
        return content;
    }

}
