package us.hk.bdwm.api.core;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Top {

    private static Gson gson = new Gson();

    private ArrayList<ThreadMeta> threadMetas;

    public Top() {
        threadMetas = new ArrayList<ThreadMeta>();
    }

    public void append(ThreadMeta threadMeta) {
        threadMetas.add(threadMeta);
    }

    public ArrayList<ThreadMeta> getThreadMetas() {
        return threadMetas;
    }

    public void toJson(Appendable writer) {
        gson.toJson(this, writer);
    }
}
