package us.hk.bdwm.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import us.hk.bdwm.api.core.Post;
import us.hk.bdwm.api.core.Top;
import us.hk.bdwm.api.util.HttpClientFactory;
import us.hk.bdwm.api.util.Parser;

import java.util.ArrayList;

import us.hk.bdwm.api.core.Thread;


@Controller
public class TopController {
    private static final Logger LOG = LoggerFactory
            .getLogger(TopController.class);

    private static final String topUrl = "http://www.bdwm.net/bbs/ListPostTops.php?halfLife=7";


    @RequestMapping("/top")
    public
    @ResponseBody
    Top top(
            @RequestParam(value = "n", required = false, defaultValue = "20") String n) {

        String url = topUrl;

        HttpClientFactory httpClientFactory = HttpClientFactory.get();
        String body = httpClientFactory.download(url);

        return Parser.getTop(body, Integer.parseInt(n));
    }
}
