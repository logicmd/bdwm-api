package us.hk.bdwm.api.core;

import java.util.ArrayList;

public class Thread {

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

}
