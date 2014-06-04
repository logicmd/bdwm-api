package us.hk.bdwm.api.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;

public class HttpClientFactory {
    private static HttpClientFactory instance = null;
    private static HttpClient client;

    private HttpClientFactory() {
        client = new HttpClient();
        client.getHostConfiguration().setProxy("proxy.logicmd.net", 8484);
    }

    public static HttpClientFactory get() {
        if(null == instance) {
            instance = new HttpClientFactory();
        }

        return instance;
    }

    public String download(String url) {
        String body = null;

        HttpMethod method = new GetMethod(url);

        try {
            client.executeMethod(method);

            if (method.getStatusCode() == 200) {
                body = method.getResponseBodyAsString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return body;
    }
}
