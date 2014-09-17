package us.hk.bdwm.api.servlet;

import org.springframework.stereotype.Component;
import us.hk.bdwm.api.core.Thread;
import us.hk.bdwm.api.core.Post;
import us.hk.bdwm.api.util.Downloader;
import us.hk.bdwm.api.util.Parser;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class ThreadServlet extends HttpServlet {

    @Resource
    private String postUrlPrefix;

    @Resource
    private String threadUrlPrefix;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String board = req.getParameter("board");
        String threadid = req.getParameter("threadid");
        String num = req.getParameter("num");
        if (num == null) {
            num = "0";
        }

        String url = threadUrlPrefix + "board=" + board + "&threadid=" + threadid + "&num=" + num;

        Thread thread = new Thread();

        String body = Downloader.get(url);

        ArrayList<String> params = Parser.getPostUrls(body);

        for (String param : params) {
            String postUrl = postUrlPrefix + param;
            String postBody = Downloader.get(postUrl);
            Post post = Parser.getPost(postBody);
            thread.append(post);
        }

        resp.setContentType("application/json;charset=UTF-8");
        thread.toJson(resp.getWriter());
    }
}
