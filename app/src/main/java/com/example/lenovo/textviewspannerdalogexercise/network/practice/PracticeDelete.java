package com.example.lenovo.textviewspannerdalogexercise.network.practice;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.util.EntityUtils;

import java.net.URLEncoder;
import java.util.Map;

import static com.example.lenovo.textviewspannerdalogexercise.network.practice.PracticeHttpClient.getHttpClient;


/**
 * Created by xue on 2018/6/21.
 */

public class PracticeDelete {
    /**
     * HttpClient delete请求方式
     */
    public static boolean deletePractice(String url, Map<String, String> params) {
        try {
            // delete方式参数拼接在URL结尾
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
            HttpDelete httpDelete = new HttpDelete(sb.toString());//创建delete请求，并设置Url地址  url在sb中
            Log.e("delete", sb.toString());
            HttpClient httpClient = getHttpClient();//发送请求，获取服务器返回的相应对象    (封装在了PracticeHttpClient的getHttpClient()方法中)
            HttpResponse httpResponse = httpClient.execute(httpDelete);//执行，发送请求，获取服务器返回的相应对象
            if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                return false;
            }
            HttpEntity httpEntity = httpResponse.getEntity();//获取返回的实体
            String object = (httpEntity == null) ? null : (EntityUtils.toString(httpEntity, "UTF-8"));//判空，将结果转换为UTF-8
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
//     * Delete基础请求
//     *
//     * @param url 请求地址
//     * @return 请求成功后的结果
//     */
//    public static byte[] doDelete(String url) {
//
//        InputStream in;
//        byte[] bre = null;
//        HttpResponse response;
//        CookieManager manager = CookieManager.getInstance();
//        if (url != null && url.length() != 0) {
//            URL myurl = URL.parseString(url);
//            Cookie[] cookies = manager.getCookies(myurl);
//            HttpDelete delete = new HttpDelete(url);
//            if (cookies != null && cookies.length > 0) {
//                StringBuilder sb = new StringBuilder();
//                for (Cookie ck : cookies) {
//                    sb.append(ck.name).append('=').append(ck.value).append(";");
//                }
//                String sck = sb.toString();
//                if (sck.length() > 0) {
//                    delete.setHeader("Cookie", sck);
//                }
//
//            }
//            delete.setHeader("Accept-Encoding", "gzip, deflate");
//            delete.setHeader("Accept-Language", "zh-CN");
//            delete.setHeader("Accept", "application/json, application/xml, text/html, text/*, image/*, */*");
//            try {
//                response = new DefaultHttpClient().execute(delete);
//                if (response != null) {
//                    int statusCode = response.getStatusLine().getStatusCode();
//                    if (statusCode == 200 || statusCode == 403) {
//                        Header[] headers = response.getHeaders("Set-Cookie");
//                        if (headers != null && headers.length > 0) {
//                            for (Header header : headers) {
//                                manager.setCookie(myurl, header.getValue());
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
//    }


//        String response = null;
//        HttpClient client = new DefaultHttpClient();
//        HttpDelete delete = new HttpDelete(url);
//        HttpResponse httpResponse;
//        try {
//            delete.setHeader("authorization", "Bearer " + this.token);
//            httpResponse = client.execute(delete);
//            int statusCode = httpResponse.getStatusLine().getStatusCode();
//            if (statusCode == HttpStatus.SC_OK) {
//                response = EntityUtils.toString(httpResponse.getEntity());
//            } else {
//                response = "返回码：" + statusCode;
//            }
//
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//            response = "timeOut";
//        } catch (IOException e) {
//            e.printStackTrace();
//            response = "timeOut";
//        }