package us.hk.bdwm.api.core;

import java.util.ArrayList;

public class Top {

    private ArrayList<ThreadMeta> threadMetas;

    public Top() {
        threadMetas = new ArrayList<ThreadMeta>();
    }

    public void AppendThreadMeta(ThreadMeta threadMeta) {
        threadMetas.add(threadMeta);
    }

    public ArrayList<ThreadMeta> getThreadMetas() {
        return threadMetas;
    }
}
