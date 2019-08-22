package http;

import org.apache.commons.lang.StringUtils;
import settings.UserAgentPool;
import util.DateUtil;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public class HttpHelper {

    public static String get(String target, Map<String, String> headers) throws IOException {
        URL url = new URL(target);

        HttpURLConnection conn = (HttpURLConnection)url.openConnection();

        conn.setRequestMethod("GET");

        conn.setRequestProperty("User-Agent", UserAgentPool.getRandomUserAgent());
        if (headers != null) {
            for (Object o : headers.entrySet()) {
                Map.Entry entry = (Map.Entry) o;
                String key = entry.getKey().toString();
                conn.setRequestProperty(key, entry.getValue().toString());
            }
        }

//        conn.setRequestProperty("Host","www.amazon.cn");
////        conn.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
////        conn.setRequestProperty("Connection","keep-alive");
////        conn.setRequestProperty("Cache-Control", "no-cache");
////        conn.setRequestProperty("Content-Encoding", "gzip");
////        conn.setRequestProperty("Content-Language", "zh-CN");
////        conn.setRequestProperty("Content-Type", "text/html;charset=UTF-8");
////        conn.setRequestProperty("Expires", "-1");
////        conn.setRequestProperty("Date", DateUtil.getGMTString());
////        conn.setRequestProperty("Pragma", "no-cache");
////        conn.setRequestProperty("Server", "Server");
////        conn.setRequestProperty("Transfer-Encoding", "chunked");
////        conn.setRequestProperty("Vary", "Accept-Encoding,User-Agent,X-Amzn-CDN-Cache,X-Amzn-AX-Treatment");
////        conn.setRequestProperty("X-Content-Type-Options", "nosniff");
////        conn.setRequestProperty("X-Frame-Options", "SAMEORIGIN");
////        conn.setRequestProperty("X-XSS-Protection", "1;");


        conn.connect();

        int httpCode = conn.getResponseCode();
        if (!String.valueOf(httpCode).startsWith("2")) {
            System.out.println("response code is : " + httpCode);
            return null;
        }

//        InputStream in = conn.getInputStream();
//        byte [] buf = new byte[1024];
//
//        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
//
//        int len = 0;
//        while((len=in.read(buf))!=-1){
//            outStream.write(buf,0,len);
//        }
//
//        in.close();
//        outStream.close();
//
//        return new String(outStream.toByteArray());
        try (
                InputStream gzippedResponse = conn.getInputStream();
                InputStream ungzippedResponse = new GZIPInputStream(gzippedResponse);
                Reader reader = new InputStreamReader(ungzippedResponse, "UTF-8");
                Writer writer = new StringWriter();
        ) {
            char[] buffer = new char[10240];
            for (int length = 0; (length = reader.read(buffer)) > 0;) {
                writer.write(buffer, 0, length);
            }
            return writer.toString();
        }
    }


    public static void main(String[] args) throws IOException {
//        System.out.println(get("https://www.amazon.cn/s?k=%E5%86%B0%E7%AE%B1&__mk_zh_CN=%E4%BA%9A%E9%A9%AC%E9%80%8A%E7%BD%91%E7%AB%99&ref=nb_sb_noss", null));
        System.out.println(get("https://www.amazon.com.au/Hana-Essential-Therapeutic-Aromatherapy-Diffuser/dp/B07GLKB29Z/ref=sr_1_26?__mk_zh_CN=%C3%A4%C2%BA%C2%9A%C3%A9%C2%A9%C2%AC%C3%A9%C2%80%C2%8A%C3%A7%C2%BD%C2%91%C3%A7%C2%AB%C2%99&keywords=Essential+oil+aromatherapy&qid=1566397356&s=gateway&sr=8-26", null));
    }

}
