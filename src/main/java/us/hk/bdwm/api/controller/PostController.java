package us.hk.bdwm.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import us.hk.bdwm.api.core.Post;

@Controller
public class PostController {
    private static final Logger LOG = LoggerFactory
            .getLogger(PostController.class);

    @RequestMapping("/post")
    public
    @ResponseBody
    Post post(
            @RequestParam(value = "board", required = true) String board,
            @RequestParam(value = "file", required = true) String file,
            @RequestParam(value = "num", required = true) String num,
            @RequestParam(value = "attach", required = false, defaultValue = "0") String attach,
            @RequestParam(value = "dig", required = false, defaultValue = "0") String dig) {
        return new Post(board, file, num, Integer.parseInt(attach), Integer.parseInt(dig));
    }
}
