package us.hk.bdwm.api.core;

public class Post {

    public static final String postUrlPrefix = "http://www.bdwm.net/bbs/bbscon.php?";

    private String author;

    private String content;

    public Post(String rawContent) {
        author = "在这里Parse Author";
        content = "在这里Parse 帖子内容";
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

}
