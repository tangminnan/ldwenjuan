package com.liudiaowenjuan.common.utils;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *  微信扫一扫 获取数据库用户信息
 *  工具类1
 */
public class WeiScanUtils {
    /**
     * 微信公众号的id
     */
    public static final String APP_ID = "wxa6898d89801a6fa5";
    /**
     * 微信公众号好的Appsecret
     */
    public static final String APP_SECRET = "f3cf09199d3bc69122f1e3bb222964b4";
    /**
     *  请求的路径
     */
    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APP_ID+"&secret="+APP_SECRET;

    public static String accessToken = null;

    /**
     * 获取  签名
     * @param url
     * @return
     * @throws IOException
     */

    public static Map<String, Object> sign(String url) throws IOException {
        JSONObject accesTokenObject = doGetJson(ACCESS_TOKEN_URL);
        String accesToken = (String) accesTokenObject.get("access_token");
        System.out.println("微信返回accesTokenObject"+accesTokenObject);
        JSONObject jsapiTicketObject = doGetJson("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + accesToken + "&type=jsapi");
        String jsapiTicket = (String) jsapiTicketObject.get("ticket");
        System.out.println("微信返回jsapiTicketObject"+jsapiTicketObject);
        Map<String, Object> ret = new HashMap<String, Object>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String string1;
        String signature = "";

        //注意这里参数名必须全部小写，且必须有序
        string1 = "jsapi_ticket=" + jsapiTicket +
                "&noncestr=" + nonce_str +
                "&timestamp=" + timestamp +
                "&url=" + url;
        System.out.println("string1="+string1);

        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string1.getBytes("UTF-8"));
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

        ret.put("url", url);
        ret.put("jsapi_ticket", jsapiTicket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);
        ret.put("appId", APP_ID);

        System.out.println("1.ticket(原始)="+jsapiTicket);
        System.out.println("2.url="+ret.get("url"));
        System.out.println("3.jsapi_ticket="+ret.get("jsapi_ticket"));
        System.out.println("4.nonceStr="+ret.get("nonceStr"));
        System.out.println("5.signature="+ret.get("signature"));
        System.out.println("6.timestamp="+ret.get("timestamp"));

        return ret;
    }


    /**
     * 随机加密
     * @param hash
     * @return
     */
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

    /**
     * 产生随机串--由程序自己随机产生
     * @return
     */
    private static String create_nonce_str() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 16);//随机字符串
    }

    /**
     * 由程序自己获取当前时间
     * @return
     */
    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    /**
     * 发送 get请求
     */

    public static JSONObject doGetJson(String url) throws IOException {
        JSONObject jsonObject = null;
        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        if(entity != null){
            String result = EntityUtils.toString(entity,"utf-8");
            jsonObject = JSONObject.fromObject(result);
        }
        httpGet.releaseConnection();
        return jsonObject;
    }


}
