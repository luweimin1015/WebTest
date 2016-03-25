package pers.lwm.base.util;

import org.springframework.util.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.DeflaterInputStream;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by lwm on 2016/2/18.
 */
public class HttpUtil {

    public static String getContent(String url) {
        String result = "";
        // 定义一个缓冲字符输入流
        BufferedReader bufferedReader = null;
        try {
            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            // 初始化 BufferedReader输入流来读取URL的响应
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            // 用来临时存储抓取到的每一行的数据
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String sendGet(String url,String cookie) throws Exception{
        Map<String, String> map = null;
        if (!StringUtil.isNullOrEmpty(cookie)) {
            map = new HashMap<>();
            map.put("Cookie", cookie);
        }
        return send(url, "GET", null, map);
    }

    public static String sendPost(String url,String cookie) throws Exception{
        Map<String, String> map = null;
        if (null != cookie && "" != cookie) {
            map = new HashMap<>();
            map.put("Cookie", cookie);
        }
        return send(url, "POST", null, map);
    }
    private static String send(String url, String method, String param, Map<String, String> headers) throws Exception {
        String result = null;
        HttpURLConnection conn = getConnection(url, method, param, headers);
        String charset = conn.getHeaderField("Content-Type");
        charset = detectCharset(charset);
        InputStream input = getInputStream(conn);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        int count;
        byte[] buffer = new byte[4096];
        while ((count = input.read(buffer, 0, buffer.length)) > 0) {
            output.write(buffer, 0, count);
        }
        input.close();
        // 若已通过请求头得到charset，则不需要去html里面继续查找
        if (charset == null || charset.equals("")) {
            charset = detectCharset(output.toString());
            // 若在html里面还是未找到charset，则设置默认编码为utf-8
            if (charset == null || charset.equals("")) {
                charset = "utf-8";
            }
        }
        output.flush();
        result = output.toString(charset);
        output.close();
        return result;
    }

    private static HttpURLConnection getConnection(String url, String method, String param, Map<String, String> header) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) (new URL(url)).openConnection();
        conn.setRequestMethod(method);

        // 设置通用的请求属性
        conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        conn.setRequestProperty("Connection", "keep-alive");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.117 Safari/537.36");
        conn.setRequestProperty("Accept-Encoding", "gzip,deflate");

        String ContentEncoding = null;
        if (header != null) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                if (entry.getKey().equalsIgnoreCase("Content-Encoding"))
                    ContentEncoding = entry.getValue();
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }

        if ("POST".equals(method)) {
            conn.setDoOutput(true);
            conn.setDoInput(true);
            if (param != null && !param.equals("")) {
                OutputStream output = conn.getOutputStream();
                if (ContentEncoding != null) {
                    if (ContentEncoding.indexOf("gzip") > 0) {
                        output=new GZIPOutputStream(output);
                    }
                    else if(ContentEncoding.indexOf("deflate") > 0) {
                        output=new DeflaterOutputStream(output);
                    }
                }
                output.write(param.getBytes());
            }
        }
        // 建立实际的连接
        conn.connect();
        return conn;
    }

    private static String detectCharset(String input) {
        Pattern pattern = Pattern.compile("charset=\"?([\\w\\d-]+)\"?;?", Pattern.CASE_INSENSITIVE);
        if (input != null && !input.equals("")) {
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                return matcher.group(1);
            }
        }
        return null;
    }

    private static InputStream getInputStream(HttpURLConnection conn) throws Exception {
        String ContentEncoding = conn.getHeaderField("Content-Encoding");
        if (ContentEncoding != null) {
            ContentEncoding = ContentEncoding.toLowerCase();
            if (ContentEncoding.indexOf("gzip") != 1)
                return new GZIPInputStream(conn.getInputStream());
            else if (ContentEncoding.indexOf("deflate") != 1)
                return new DeflaterInputStream(conn.getInputStream());
        }

        return conn.getInputStream();
    }

}
