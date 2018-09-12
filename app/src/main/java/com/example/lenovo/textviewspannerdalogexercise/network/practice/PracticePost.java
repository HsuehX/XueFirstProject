package com.example.lenovo.textviewspannerdalogexercise.network.practice;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static android.provider.Telephony.Mms.Addr.CHARSET;

/**
 * Created by xue on 2018/6/20.
 */

public class PracticePost {
    private static final String TAG = "PracticePostClient";

    public static String post(String url, NameValuePair... params) {//这三个点（省略号）表示 参数是多个
        try {
            //建立一个NameValuePair集合，用于存储欲传送的参数
            List<NameValuePair> paramsList = new ArrayList<NameValuePair>();//NameValuePair:简单名称值对节点类型
            for (NameValuePair p : params) {
                paramsList.add(p);
            }
            //UrlEncodedFormEntity可以把输入数据编码成合适的内容
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramsList, CHARSET);//把内容转换成字符集

            //创建POST请求
            HttpPost request = new HttpPost(url);
            request.setEntity(entity);//设置实体

            //发送请求
            HttpClient client = PracticeHttpClient.getHttpClient();//调用PracticeHttpClient中写的getHttpClient方法
            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {//判断页面请求状态码是否正确
                throw new RuntimeException("请求失败");
            }

            HttpEntity resEntity = response.getEntity();//获取返回的实体
            return (resEntity == null) ? null : EntityUtils.toString(resEntity, CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Log.e(TAG, e.getMessage());
            return null;
        } catch (ClientProtocolException e) {
            Log.e(TAG, e.getMessage());
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("链接失败", e);
        }

    }
}



//    public static boolean onLinkNetPost(String url, List<NameValuePair> nvpsList) {
//        try {
//            // 判断传递进来的url连接地址是否为空
//            if (null == url) {
//                return false;
//            }
//            /* 1.1 创建POST请求，并设置Url地址的名值对及其编码格式，然后设置Entity */
//            Log.e("post", url);
//            HttpPost httpPost = new HttpPost(url);
//            if (null != nvpsList) {
//                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(
//                        nvpsList, "UTF-8");
//                httpPost.setEntity(entity);
//            }
//            /* 1.2获取HttpClient对象，并发送请求，得到响应 */
//            HttpClient httpClient = getHttpClient();
//
//            // 1.3发送请求，获取服务器返回的相应对象
//            HttpResponse httpResponse = httpClient.execute(httpPost);
//            /* 1.4 从响应中获取数据 */
//            if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
//                return false;
//            }
//            HttpEntity httpEntity = httpResponse.getEntity();
//
//            String object = (httpEntity == null) ? null : (EntityUtils
//                    .toString(httpEntity, "UTF-8"));
//            Log.e("object", object);
//            return true;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            Log.e("Exception=", e.getMessage() + "");
//            Log.e("e", e.getMessage() + "");
//            return false;
//        }
//    }





//    /**
//     * 所有Post 请求底层调用方法
//     *
//     * @param url 请求url
//     * @param values 传递的参数
//     * @return byte[] 返回数据 or null
//     */
//    public static byte[] doPost(String url, List<NameValuePair> values) {
//        System.out.println("url = " + url);
//        byte[] bytes = null;
//        HttpResponse response;
//        InputStream inputStream = null;
//        CookieManager manager = CookieManager.getInstance();
//        if (url != null && url.length() != 0) {
//            URL myurl = URL.parseString(url);
//            Cookie[] cookies = manager.getCookies(myurl);
//            HttpPost post = new HttpPost(url);
//            if (cookies != null && cookies.length > 0) {
//                StringBuilder sb = new StringBuilder();
//                for (Cookie ck : cookies) {
//                    sb.append(ck.name).append('=').append(ck.value).append(";");
//                }
//                String sck = sb.toString();
//                if (sck.length() > 0) {
//                    post.setHeader("Cookie", sck);
//                }
//
//            }
//            post.setHeader("Accept-Encoding", "gzip, deflate");
//            post.setHeader("Accept-Language", "zh-CN");
//            post.setHeader("Accept", "application/json, application/xml, text/html, text/*, image/*, */*");
//            DefaultHttpClient client = new DefaultHttpClient();
//            try {
//                if (values != null && values.size() > 0) {
//                    post.setEntity(new UrlEncodedFormEntity(values, HTTP.UTF_8));
//                }
//                response = client.execute(post);
//                if (response != null) {
//                    int statusCode = response.getStatusLine().getStatusCode();
//                    if (statusCode == 200 || statusCode == 403) {
//                        Header[] headers = response.getHeaders("Set-Cookie");
//                        if (headers != null && headers.length > 0) {
//                            for (Header header : headers) {
//                                manager.setCookie(myurl, header.getValue());
//                            }
//                        }
//                        inputStream = response.getEntity().getContent();
//                    }
//                }
//
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            if (inputStream != null) {
//                bytes = ResourceUtil.readStream(inputStream);
//            }
//        }
//        return bytes;
//    }
//
//    /**
//     * PUT基础请求
//     *
//     * @param url 请求地址
//     * @param values 提交参数
//     * @return byte[] 请求成功后的结果
//     */
//    public static byte[] doPut(String url, List<NameValuePair> values) {
//        byte[] ret = null;
//
//        CookieManager manager = CookieManager.getInstance();
//        if (url != null && url.length() > 0) {
//            URL myUrl = URL.parseString(url);
//            StringBuilder sb = new StringBuilder();
//            Cookie[] cookies = manager.getCookies(myUrl);
//            if (cookies != null && cookies.length > 0) {
//                for (Cookie cookie : cookies) {
//                    sb.append(cookie.name).append("=").append(cookie.value).append(";");
//                }
//
//            }
//            HttpPut request = new HttpPut(url);
//            String sck = sb.toString();
//            if (sck.length() > 0) {
//                request.setHeader("Cookie", sck);
//            }
//            request.setHeader("Accept-Encoding", "gzip, deflate");
//            request.setHeader("Accept-Language", "zh-CN");
//            request.setHeader("Accept", "application/json, application/xml, text/html, text/*, image/*, */*");
//
//            DefaultHttpClient client = new DefaultHttpClient();
//            if (values != null && values.size() > 0) {
//                try {
//                    UrlEncodedFormEntity entity;
//                    entity = new UrlEncodedFormEntity(values);
//                    request.setEntity(entity);
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//            }
//            try {
//                HttpResponse response = client.execute(request);
//                if (response != null) {
//                    StatusLine statusLine = response.getStatusLine();
//                    int statusCode = statusLine.getStatusCode();
//                    if (statusCode == 200 || statusCode == 403) {
//                        Header[] headers = response.getHeaders("Set-Cookie");
//                        if (headers != null && headers.length > 0) {
//                            for (Header header : headers) {
//                                manager.setCookie(myUrl, header.getValue());
//                            }
//                        }
//                        HttpEntity entity = response.getEntity();
//                        InputStream inputStream = entity.getContent();
//                        if (inputStream != null) {
//                            ret = ResourceUtil.readStream(inputStream);
//                            inputStream.close();
//                        }
//                    }
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return ret;
//    }
