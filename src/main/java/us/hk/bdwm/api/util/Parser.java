package us.hk.bdwm.api.util;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public static String getPostContent(String body) {
        Pattern p = Pattern.compile("<pre>(.*?)</pre>", Pattern.DOTALL);
        Matcher m = p.matcher(body);

        if (m.find()) {
            return m.group(1);
        }
        return null;
    }

    public static ArrayList<String> getPostUrls(String body) {
        Pattern p = Pattern.compile("<th class=\"postNew\"><a href=\"bbspst\\.php\\?(.*?)\">回文章</a></th></tr>");
        Matcher m = p.matcher(body);
        ArrayList<String> urls = new ArrayList<String>();

        while (m.find()) {
            urls.add(m.group(1));
        }
        return urls;
    }


}