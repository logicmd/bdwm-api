package us.hk.bdwm.api.core;

import com.google.gson.Gson;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Post {

    private static Gson gson = new Gson();

    private static final String postPattern =
                "发信人: ([^\\s]*) .*?, 信区: ([^\n]*)" +
                "标  题: ([^\n]*)" +
                "发信站: 北大未名站 \\((.*?)\\), .*?" +
                "([\\s\\S]*)--";

    private static final String replyUrlPattern =
                "<th class=foot><a href=\"([^\"]*?)\">回文章</a></th></tr>";

    private static final String mailUrlPattern =
                "<th class=foot><a href=\"([^\"]*?)\">.信给作者</a></th></tr>";

    private String author;

    private String board;

    private String title;

    private String time;

    private String content;

    private String replyUrl;

    private String mailUrl;


    public Post(String rawBody) {
        Pattern p = Pattern.compile(postPattern);
        Matcher m = p.matcher(rawBody);

        if (m.find()) {
            author = m.group(1);
            board = m.group(2);
            title = m.group(3);
            time = m.group(4);
            content = m.group(5).trim();
        }

        p = Pattern.compile(replyUrlPattern);
        m = p.matcher(rawBody);

        if (m.find()) {
            replyUrl = m.group(1);
        }

        p = Pattern.compile(mailUrlPattern);
        m = p.matcher(rawBody);

        if (m.find()) {
            mailUrl = m.group(1);
        }
    }

    public void toJson(Appendable writer) {
        gson.toJson(this, writer);
    }
}
