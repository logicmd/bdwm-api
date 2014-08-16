package us.hk.bdwm.api.core;

import java.util.ArrayList;
import com.google.gson.Gson;


public class Thread {

    private static Gson gson = new Gson();

    private ArrayList<Post> posts;

    public Thread() {
        posts = new ArrayList<Post>();
    }

    public void append(Post post) {
        posts.add(post);
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void toJson(Appendable writer) {
        gson.toJson(this, writer);
    }
}
