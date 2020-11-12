package com.liudiaowenjuan.common.utils;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
/**
 *  微信扫一扫 获取数据库用户信息
 *  工具类2
 */
public class WeiScanUtils2 {

    /**
     * 微信公众号的id
     */
    public static final String APP_ID = "wxa6898d89801a6fa5";
    /**
     * 微信公众号好的Appsecret
     */
    public static final String APP_SECRET = "f3cf09199d3bc69122f1e3bb222964b4";

    public static Map<String,Object>  sign(String url){
        String accessToken = null; //"16_LN17KXa9jdOU5Eg4qjupBupH_XxdEbIHLxCzZ944nAoIC9wHmL1fKhx82duWdX7ad_0B8W-Sip5jGnM1DAckBzAgD61a4_kbt8P7tsfm8dDrnlyGKtrPVsv-EuMFKSaAJAUZH";
        try {
            accessToken = getAccessToken();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("accessToken========="+accessToken);
        //2、获取Ticket
        String jsapi_ticket = null; //"HoagFKDcsGMVCIY2vOjf9l_xORO2cDz_p4R2nJVbiRXnqO7ayO0GJ4WCpE41Cflk8G_3UiDVU0JYhod8X7vu6Q";
        try {
            jsapi_ticket = getTicket(accessToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("jsapi_ticket========="+jsapi_ticket);
        //3、时间戳和随机字符串
        String noncestr = UUID.randomUUID().toString().replace("-", "").substring(0, 16);//随机字符串
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);//时间戳
        //System.out.println("accessToken:"+accessToken+"\njsapi_ticket:"+jsapi_ticket+"\n时间戳："+timestamp+"\n随机字符串："+noncestr);
        //5、将参数排序并拼接字符串
        String str = "jsapi_ticket="+jsapi_ticket+"&noncestr="+noncestr+"&timestamp="+timestamp+"&url="+url;
        String signature =null;
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(str.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        //System.out.println("参数："+str+"\n签名："+signature);
        //Long userId = oauth2SecuritySubject.getCurrentUser();
        Map<String,Object> ok = new HashMap<String,Object>();
        ok.put("timestam",timestamp);
        ok.put("nonceSt",noncestr);
        ok.put("signatur",signature);
        System.out.println("timestam  "+timestamp);
        System.out.println("nonceSt  "+noncestr);
        System.out.println("signatur "+signature);
        return ok;
    }


    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
    private static String getAccessToken() throws Exception{
        String Url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APP_ID+"&secret="+APP_SECRET;
        URL url = new URL(Url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.connect();

        //获取返回的字符
        InputStream inputStream = connection.getInputStream();
        int size =inputStream.available();
        byte[] bs =new byte[size];
        inputStream.read(bs);
        String message=new String(bs,"UTF-8");
        System.out.println("=========================");
        System.out.println(message);
        System.out.println(message);
        System.out.println(message);
        System.out.println(message);
        System.out.println("=========================");
        JSONObject json = JSON.parseObject(message);
        //获取access_token
        String token = json.getString("access_token");
        return token;
    }
    private static String getTicket(String token) throws Exception{
        String Url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+token+"&type=jsapi";
        URL url = new URL(Url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.connect();

        //获取返回的字符
        InputStream inputStream = connection.getInputStream();
        int size =inputStream.available();
        byte[] bs =new byte[size];
        inputStream.read(bs);
        String message=new String(bs,"UTF-8");
        JSONObject json = JSON.parseObject(message);
        //获取access_token
        String ticket = json.getString("ticket");
        return ticket;
    }

}
