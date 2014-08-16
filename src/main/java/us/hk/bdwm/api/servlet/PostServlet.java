package us.hk.bdwm.api.servlet;

import org.springframework.stereotype.Component;
import us.hk.bdwm.api.core.Post;
import us.hk.bdwm.api.util.HttpClientFactory;
import us.hk.bdwm.api.util.Parser;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class PostServlet extends HttpServlet {

    @Resource
    private String postUrlPrefix;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String board = req.getParameter("board");
        String file = req.getParameter("file");
        String num = req.getParameter("num");
        String attach = req.getParameter("attach");
        if (attach == null) {
            attach = "0";
        }
        String dig = req.getParameter("dig");
        if (dig == null) {
            dig = "0";
        }

        String url = postUrlPrefix + "board=" + board + "&file=" + file + "&num=" + num + "&attach=" + attach + "&dig=" + dig;

        HttpClientFactory httpClientFactory = HttpClientFactory.get();
        String body = httpClientFactory.download(url);


        Post post = Parser.getPost(body);
        resp.setContentType("application/json;charset=UTF-8");
        post.toJson(resp.getWriter());
    }
}
