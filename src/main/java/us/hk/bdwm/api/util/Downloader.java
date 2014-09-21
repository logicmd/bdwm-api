package us.hk.bdwm.api.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Downloader {
    private static HttpClient client;

    public static String get(String url) {
        String body = null;
        if (null == client) {
            client = new HttpClient();
        }

        HttpMethod method = new GetMethod(url);
        method.setRequestHeader("Content-Type", "text/html;encoding=gb18030");

        String b = "";

        try {
            client.executeMethod(method);

            if (method.getStatusCode() == 200) {
                StringBuffer temp = new StringBuffer();
                b = method.getResponseBody().toString();
                InputStream in = method.getResponseBodyAsStream();
                BufferedReader buffer = new BufferedReader(new InputStreamReader(in, "gb18030"));
                for (String tempStr; (tempStr = buffer.readLine()) != null; )
                    temp = temp.append(tempStr);

                buffer.close();
                in.close();
                body = temp.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //return body;
        return b;
    }
}
