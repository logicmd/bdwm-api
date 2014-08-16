package us.hk.bdwm.api.core;

import com.google.gson.Gson;

public class Post {

    private static Gson gson = new Gson();

    private String content;

    public Post(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void toJson(Appendable writer) {
        gson.toJson(this, writer);
    }
}
