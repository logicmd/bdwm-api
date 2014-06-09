package us.hk.bdwm.api.util;

import us.hk.bdwm.api.core.Post;
import us.hk.bdwm.api.core.ThreadMeta;
import us.hk.bdwm.api.core.Top;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public static Post getPost(String body) {
        Pattern p = Pattern.compile("<pre>(.*?)</pre>", Pattern.DOTALL);
        Matcher m = p.matcher(body);

        if (m.find()) {
            return new Post(m.group(1));
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

    public static Top getTop(String body, int limit) {
        Top top = new Top();

        Pattern p = Pattern.compile(
                "<td class='hidden'>1</td>.*" +
                "<td><a href='bbsdoc.php?.+'>.+</a></td>.*" +
                "<td><a href='bbstop.php?.+'>(.*)</a></td>.*" +
                "<td><a href='bbsqry.php?.+'>.*</a></td>.*" +
                "<td><a href='(bbstcon.php?.+)'>(.*)</a></td>.*" +
                "</tr>",
                Pattern.DOTALL
        );
        Matcher m = p.matcher(body);

        while (m.find() && limit-- > 0) {
            String board = m.group(1);
            String title = m.group(2);
            String url = "http://www.bdmw.net/bbs/" + m.group(3);
            ThreadMeta threadMeta = new ThreadMeta(board, title, url);
            top.append(threadMeta);
        }
        return top;
    }


}
