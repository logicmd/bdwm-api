package us.hk.bdwm.api.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HttpClientFactory {
    private static HttpClientFactory instance = null;
    private static HttpClient client;

    private HttpClientFactory() {
        client = new HttpClient();
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
        method.setRequestHeader("Content-Type", "text/html;encoding=gb18030");

        try {
            client.executeMethod(method);

            if (method.getStatusCode() == 200) {
                StringBuffer temp = new StringBuffer();
                InputStream in = method.getResponseBodyAsStream();
                BufferedReader buffer = new BufferedReader(new InputStreamReader(in, "gb18030"));
                for(String tempstr = ""; (tempstr = buffer.readLine()) != null;)
                    temp = temp.append(tempstr);

                buffer.close();
                in.close();
                body = temp.toString().trim();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return body;
    }
}
