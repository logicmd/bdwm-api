package us.hk.bdwm.api.servlet;

import org.springframework.stereotype.Component;
import us.hk.bdwm.api.core.Top;
import us.hk.bdwm.api.util.Downloader;
import us.hk.bdwm.api.util.Parser;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class HotServlet extends HttpServlet {

    @Resource
    private String topUrl;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String n = req.getParameter("n");
        if (n == null) {
            n = "20";
        }

        String url = topUrl;

        String body = Downloader.get(url);
        Top top = Parser.getTop(body, Integer.parseInt(n));

        resp.setContentType("application/json;charset=UTF-8");
        top.toJson(resp.getWriter());
    }
}
