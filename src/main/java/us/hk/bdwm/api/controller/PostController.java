package us.hk.bdwm.api.controller;

import org.apache.commons.httpclient.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import us.hk.bdwm.api.core.Post;
import us.hk.bdwm.api.util.HttpClientFactory;
import us.hk.bdwm.api.util.Parser;

@Controller
public class PostController {
    private static final Logger LOG = LoggerFactory
            .getLogger(PostController.class);

    private static final String postUrlPrefix = "http://www.bdwm.net/bbs/bbscon.php?";

    private HttpClient client;

    @RequestMapping("/post")
    public
    @ResponseBody
    Post post(
            @RequestParam(value = "board", required = true) String board,
            @RequestParam(value = "file", required = true) String file,
            @RequestParam(value = "num", required = true) String num,
            @RequestParam(value = "attach", required = false, defaultValue = "0") String attach,
            @RequestParam(value = "dig", required = false, defaultValue = "0") String dig) {

        String url = postUrlPrefix + "board=" + board + "&file=" + file + "&num=" + num + "&attach=" + attach + "&dig=" + dig;

        HttpClientFactory httpClientFactory = HttpClientFactory.getHttpClient();
        String body = httpClientFactory.download(url);

        return new Post(Parser.getPostContent(body));
    }
}
