package com.example.lenovo.textviewspannerdalogexercise.network.practice;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import java.net.URLEncoder;
import java.util.Map;

import static com.example.lenovo.textviewspannerdalogexercise.network.practice.PracticeHttpClient.getHttpClient;


/**
 * Created by xue on 2018/6/21.
 */

public class PracticeGet {
    private static final String TAG = "PracticeGetClient";

    public static boolean onLinkNetGet(String url, Map<String, String> params) {
        try {
            // GET方式参数拼接在URL结尾
            StringBuilder sb = new StringBuilder();
            sb.append(url).append("?");
            if (params != null && params.size() != 0) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    // 如果请求参数中有中文，需要进行URLEncoder编码
                    sb.append(entry.getKey())
                            .append("=")
                            .append(URLEncoder.encode(entry.getValue(), "utf-8"));
                    sb.append("&");
                }
                sb.deleteCharAt(sb.length() - 1);
                System.out.println(sb.toString());
                Log.d("sb", sb.toString());
            }

        /* 1.1 创建Get请求，并设置Url地址 */
            HttpGet httpGet = new HttpGet(sb.toString());
            Log.e("get", sb.toString());
        /* 1.2 获取HttpClient对象，并发送请求，得到响应 */
            HttpClient httpClient = getHttpClient();
            // 1.3发送请求，获取服务器返回的相应对象
            HttpResponse httpResponse = httpClient.execute(httpGet);
        /* 1.4 从响应中获取数据 */
            if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                return false;
            }
            HttpEntity httpEntity = httpResponse.getEntity();
            String object = (httpEntity == null) ? null : (EntityUtils
                    .toString(httpEntity, "UTF-8"));
            System.out.println(object);
            Log.e("object", object);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Exception=", e.getMessage() + "");
            Log.e("e", e.getMessage() + "");
            return false;
        }

    }

}



//    /**
//     * 所有get 请求底层调用方法
//     *
//     * @param url 请求url
//     * @return byte[] response data
//     */
//    public static byte[] doGet(String url) {
//        InputStream in;
//        byte[] bre = null;
//        HttpResponse response;
//        CookieManager manager = CookieManager.getInstance();
//        if (url != null && url.length() != 0) {
//            URL myURL = URL.parseString(url);
//            Cookie[] cookies = manager.getCookies(myURL);
//            HttpGet httpGet = new HttpGet(url);
//            if (cookies != null && cookies.length > 0) {
//                StringBuilder sb = new StringBuilder();
//                for (Cookie ck : cookies) {
//                    sb.append(ck.name).append('=').append(ck.value).append(";");
//                }
//                String sck = sb.toString();
//                if (sck.length() > 0) {
//                    httpGet.setHeader("Cookie", sck);
//                }
//
//            }
//            httpGet.setHeader("Accept-Encoding", "gzip, deflate");
//            httpGet.setHeader("Accept-Language", "zh-CN");
//            httpGet.setHeader("Accept", "application/json, application/xml, text/html, text/*, image/*, */*");
//            try {
//                response = new DefaultHttpClient().execute(httpGet);
//                if (response != null) {
//                    int statusCode = response.getStatusLine().getStatusCode();
//                    if (statusCode == 200 || statusCode == 403) {
//                        Header[] headers = response.getHeaders("Set-Cookie");
//                        if (headers != null && headers.length > 0) {
//                            for (Header header : headers) {
//                                manager.setCookie(myURL, header.getValue());
//                            }
//                        }
//                        in = response.getEntity().getContent();
//                        if (in != null) {
//                            bre = ResourceUtil.readStream(in);
//                        }
//
//                    }
//                }
//
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//
//        return bre;
//
//    }