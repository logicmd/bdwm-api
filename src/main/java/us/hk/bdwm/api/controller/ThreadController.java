package us.hk.bdwm.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import us.hk.bdwm.api.core.Post;
import us.hk.bdwm.api.util.HttpClientFactory;
import us.hk.bdwm.api.util.Parser;

import java.util.ArrayList;

import us.hk.bdwm.api.core.Thread;


@Controller
public class ThreadController {
    private static final Logger LOG = LoggerFactory
            .getLogger(ThreadController.class);

    private static final String postUrlPrefix = "http://www.bdwm.net/bbs/bbscon.php?";

    private static final String threadUrlPrefix = "http://www.bdwm.net/bbs/bbstcon.php?";

    @RequestMapping("/thread")
    public
    @ResponseBody
    Thread thread(
            @RequestParam(value = "board", required = true) String board,
            @RequestParam(value = "threadid", required = true) String threadid,
            @RequestParam(value = "num", required = false, defaultValue = "0") String num) {

        Thread thread = new Thread();

        String url = threadUrlPrefix + "board=" + board + "&threadid=" + threadid + "&num=" + num;

        HttpClientFactory httpClientFactory = HttpClientFactory.get();
        String body = httpClientFactory.download(url);

        ArrayList<String> params = Parser.getPostUrls(body);

        for(String param : params) {
            String postUrl = postUrlPrefix + param;
            String postBody = httpClientFactory.download(postUrl);
            Post post = new Post(Parser.getPostContent(postBody));
            thread.appendPost(post);
        }

        return thread;
    }
}
