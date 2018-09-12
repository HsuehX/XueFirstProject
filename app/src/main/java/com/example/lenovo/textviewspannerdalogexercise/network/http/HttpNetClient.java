package com.example.lenovo.textviewspannerdalogexercise.network.http;//package com.tencent.myapplication.network.http;
//
//import org.apache.http.*;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpDelete;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.methods.HttpPut;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.protocol.HTTP;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.UnsupportedEncodingException;
//import java.util.List;
//
///**
// * Created by xue on 2018/6/21.
// */
//
//public class HttpNetClient {
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
//
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
//
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
//}
