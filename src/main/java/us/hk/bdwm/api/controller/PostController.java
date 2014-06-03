package us.hk.bdwm.api.controller;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import us.hk.bdwm.api.core.Post;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class PostController {
    private static final Logger LOG = LoggerFactory
            .getLogger(PostController.class);

    private static final String postUrlPrefix = "http://www.bdwm.net/bbs/bbscon.php?";

    private HttpClient client;

    private DocumentBuilder docBuilder;

    public PostController() throws ParserConfigurationException {
        client = new HttpClient();
        client.getHostConfiguration().setProxy("proxy.logicmd.net", 8484);
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        docBuilder = docBuilderFactory.newDocumentBuilder();
    }

    @RequestMapping("/post")
    public
    @ResponseBody
    Post post(
            @RequestParam(value = "board", required = true) String board,
            @RequestParam(value = "file", required = true) String file,
            @RequestParam(value = "num", required = true) String num,
            @RequestParam(value = "attach", required = false, defaultValue = "0") String attach,
            @RequestParam(value = "dig", required = false, defaultValue = "0") String dig) {

        String rawContent = null;
        String url = postUrlPrefix + board + "/" + file + "/" + num + "/" + attach + "/" + dig;

        HttpMethod method = new GetMethod(url);
        try {
            client.executeMethod(method);

            if (method.getStatusCode() == 200) {
                String body = method.getResponseBodyAsString();

                Document doc = null;

                doc = docBuilder.parse(new InputSource(new ByteArrayInputStream(body.getBytes())));


                rawContent = doc.getElementsByTagName("pre").item(0).getNodeValue();
                //Pattern p = Pattern.compile("<pre>(.*?)<\\/pre>", Pattern.DOTALL);
                //Matcher m = p.matcher(body);

//                if (m.find()) {
//                    rawContent = m.group(1);
//                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return new Post(rawContent);
    }
}
