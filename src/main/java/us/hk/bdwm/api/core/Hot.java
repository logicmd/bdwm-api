package us.hk.bdwm.api.core;

import com.google.gson.Gson;

public class Hot {

    private static Gson gson = new Gson();

    public void toJson(Appendable writer) {
        gson.toJson(this, writer);
    }
}
