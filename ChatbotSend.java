package com;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.http.impl.client.HttpClients;

/**
 * 测试 给钉钉指定用户发送消息
 * @author Dell
 *
 */
public class ChatbotSend {
	
	// 测试成功   智能制造群
	public static String WEBHOOK_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=56d79cefea9c266ff129d4597e5f036e1c800e033ad425bd40cad5a69946c843";
	
	
    public static void main(String args[]) throws Exception{
    	/**
    	 * org.apache.http.client.HttpClient 是接口 
    	 * org.apache.http.impl.client.HttpClients  
    	 */
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(WEBHOOK_TOKEN);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
 
        String textMsg = "{ \"msgtype\": \"text\", \"text\": {\"content\": \"大家好，我是小小钉钉。\"}}";
        StringEntity se = new StringEntity(textMsg, "utf-8");
        /**
         * StringEntity 第一个参数String不能为null 第二个参数String指定编码格式 
         * public StringEntity(String string, String charset) throws UnsupportedCharsetException
         * Creates a StringEntity with the specified content and charset. The MIME type defaults to "text/plain".
         * 
         */
        httppost.setEntity(se);
        HttpResponse response = httpclient.execute(httppost); // 执发送消息 json格式的字符串
        
        // 验证发送是否成功  若成功则返回ok
        // public static final int SC_OK = 200; response状态码200表示成功
        if (response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
            String result= EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(result);
        }
    }
	
}
