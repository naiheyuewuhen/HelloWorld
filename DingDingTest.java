package com;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.dingtalk.chatbot.DingtalkChatbotClient;
import com.dingtalk.chatbot.SendResult;
import com.dingtalk.chatbot.message.ActionButtonStyle;
import com.dingtalk.chatbot.message.ActionCardAction;
import com.dingtalk.chatbot.message.ActionCardMessage;
import com.dingtalk.chatbot.message.FeedCardMessage;
import com.dingtalk.chatbot.message.FeedCardMessageItem;
import com.dingtalk.chatbot.message.LinkMessage;
import com.dingtalk.chatbot.message.SingleTargetActionCardMessage;
import com.dingtalk.chatbot.message.TextMessage;

public class DingDingTest {
	private DingtalkChatbotClient client = new DingtalkChatbotClient();
	public static final String CHATBOT_WEBHOOK = "https://oapi.dingtalk.com/robot/send?access_token=56d79cefea9c266ff129d4597e5f036e1c800e033ad425bd40cad5a69946c843";
	
	
	/* --------------------------ActionCardMessage---------------------------------------  */
    @Test
    public void testSendActionCardMessage() throws Exception {
        ActionCardMessage message = new ActionCardMessage();
        message.setBriefText("亲，小秘没有看懂你的问题哦，换个说法问问小秘看~你也可以试试以下问题");
        ActionCardAction action1 = new ActionCardAction("考勤打卡", "http://www.dingtalk.com");
        message.addAction(action1);
        ActionCardAction action2 = new ActionCardAction("办公电话", "http://www.dingtalk.com");
        message.addAction(action2);
        ActionCardAction action3 = new ActionCardAction("智能客服", "http://www.dingtalk.com");
        message.addAction(action3);
        ActionCardAction action4 = new ActionCardAction("更多问题", "http://www.dingtalk.com");
        message.addAction(action4);

//        SendResult result = client.send(TestConfig.CHATBOT_WEBHOOK, message);
        SendResult result = client.send(CHATBOT_WEBHOOK, message);
        System.out.println(result);
    }
    
    @Test
    public void testSendActionCardMessageWithTitle() throws Exception {
        ActionCardMessage message = new ActionCardMessage();
        message.setTitle("钉钉功能推荐");
        message.setBriefTitle("创建团队，让工作沟通更加安全高效");
        message.setBriefText("国内广受认可的企业级应用市场入驻标准高、产品质量高，入驻即代表得到市场认可，实力获得钉钉背书");
        ActionCardAction action1 = new ActionCardAction("邀请群成员创建团队", "http://www.dingtalk.com");
        message.addAction(action1);

        SendResult result = client.send(CHATBOT_WEBHOOK, message);
        System.out.println(result);
    }
    
    @Test
    public void testSendActionCardMessageWithBanner() throws Exception {
        ActionCardMessage message = new ActionCardMessage();
        message.setTitle("钉钉功能推荐");
        message.setBannerURL("http://img01.taobaocdn.com/top/i1/LB1GCdYQXXXXXXtaFXXXXXXXXXX");
        message.setBriefTitle("创建团队，让工作沟通更加安全高效");
        message.setBriefText("国内广受认可的企业级应用市场入驻标准高、产品质量高，入驻即代表得到市场认可，实力获得钉钉背书");
        ActionCardAction action1 = new ActionCardAction("查看详情", "http://www.dingtalk.com");
        message.addAction(action1);

        ActionCardAction action2 = new ActionCardAction("不感兴趣", "http://www.dingtalk.com");
        message.addAction(action2);

        SendResult result = client.send(CHATBOT_WEBHOOK, message);
        System.out.println(result);
    }
    
    @Test
    public void testSendActionCardMessageWithHorizontalButton() throws Exception {
        ActionCardMessage message = new ActionCardMessage();
        message.setBannerURL("http://img01.taobaocdn.com/top/i1/LB1GCdYQXXXXXXtaFXXXXXXXXXX");
        message.setTitle("创建团队，让工作沟通更加安全高效");
        message.setBriefText("国内广受认可的企业级应用市场入驻标准高、产品质量高，入驻即代表得到市场认可，实力获得钉钉背书");
        ActionCardAction action1 = new ActionCardAction("查看详情", "http://www.dingtalk.com");
        message.addAction(action1);

        ActionCardAction action2 = new ActionCardAction("不感兴趣", "http://www.dingtalk.com");
        message.addAction(action2);
        message.setActionButtonStyle(ActionButtonStyle.HORIZONTAL);

        SendResult result = client.send(CHATBOT_WEBHOOK, message);
        System.out.println(result);
    }
    
    /**
     * ActionCardMessage
     * 发送图片 加 两个水平按钮  并影藏发送人头像名字
     * @throws Exception
     */
    @Test
    public void testSendActionCardMessageWithHorizontalButtonWithoutAvatar() throws Exception {
        ActionCardMessage message = new ActionCardMessage();
        message.setTitle("创建团队，让工作沟通更加安全高效");
        message.setBannerURL("http://img01.taobaocdn.com/top/i1/LB1GCdYQXXXXXXtaFXXXXXXXXXX");// 加图片
        message.setBriefText("国内广受认可的企业级应用市场入驻标准高、产品质量高，入驻即代表得到市场认可，实力获得钉钉背书");
        ActionCardAction action1 = new ActionCardAction("查看详情", "dtmd://dingtalkclient/sendMessage?content=world");
        message.addAction(action1);

        ActionCardAction action2 = new ActionCardAction("不感兴趣", URLEncoder.encode("dtmd://dingtalkclient/sendMessage?content=不感兴趣", "UTF-8"));
        message.addAction(action2);
        message.setActionButtonStyle(ActionButtonStyle.HORIZONTAL); // 两个按钮水平排列
        message.setHideAvatar(true); //影藏会话人头像及名字 

        SendResult result = client.send(CHATBOT_WEBHOOK, message);
        System.out.println(result);
    }
    
    /* --------------------------FeedCardMessage---------------------------------------  */
    /**
     * FeedCardMessage 
     * @throws Exception
     */
    @Test
    public void testSendMultipleFeedCardMessage() throws Exception {
        FeedCardMessage message = new FeedCardMessage();

        List<FeedCardMessageItem> items = new ArrayList<FeedCardMessageItem>();
        FeedCardMessageItem item1 = new FeedCardMessageItem();
        item1.setTitle("心灵鸡汤1");
        item1.setPicURL("https://img.alicdn.com/tps/TB1XLjqNVXXXXc4XVXXXXXXXXXX-170-64.png");
        item1.setMessageURL("http://www.dingtalk.com");
        items.add(item1);

        FeedCardMessageItem item2 = new FeedCardMessageItem();
        item2.setTitle("心灵鸡汤2");
        item2.setPicURL("https://img.alicdn.com/tps/TB1XLjqNVXXXXc4XVXXXXXXXXXX-170-64.png");
        item2.setMessageURL("http://www.dingtalk.com");
        items.add(item2);

        FeedCardMessageItem item3 = new FeedCardMessageItem();
        item3.setTitle("心灵鸡汤3");
        item3.setPicURL("https://img.alicdn.com/tps/TB1XLjqNVXXXXc4XVXXXXXXXXXX-170-64.png");
        item3.setMessageURL("http://www.dingtalk.com");
        items.add(item3);

        message.setFeedItems(items);

        System.out.println(message.toJsonString());

        SendResult result = client.send(CHATBOT_WEBHOOK, message);
        System.out.println(result);
    }
    
    /* ---------------------LinkMessage--------------------------------------------  */
    @Test
    public void testSendLinkMessage() throws Exception {
        LinkMessage message = new LinkMessage();
        message.setTitle("时代的火车向前开");
        message.setText("这个即将发布的新版本，创始人陈航（花名“无招”）称它为“红树林”。\n" +
                "而在此之前，每当面临重大升级，产品经理们都会取一个应景的代号，这一次，为什么是“红树林”？\"");
        message.setMessageUrl("https://mp.weixin.qq.com/s?spm=a219a.7629140.0.0.EUDyWG&__biz=MzA4NjMwMTA2Ng==&mid=2650316842&idx=1&sn=60da3ea2b29f1dcc43a7c8e4a7c97a16&scene=2&srcid=09189AnRJEdIiWVaKltFzNTw&from=timeline&isappinstalled=0&key=&ascene=2&uin=&devicetype=android-23&version=26031933&nettype=WIFI");
        message.setPicUrl("https://img.alicdn.com/tps/TB1XLjqNVXXXXc4XVXXXXXXXXXX-170-64.png");

        SendResult result = client.send(CHATBOT_WEBHOOK, message);
        System.out.println(result);
    }

    @Test
    public void testSendLinkMessageWithoutIcon() throws Exception {
        LinkMessage message = new LinkMessage();
        message.setTitle("时代的火车向前开");
        message.setText("这个即将发布的新版本，创始人陈航（花名“无招”）称它为“红树林”。\n" +
        		"而在此之前，每当面临重大升级，产品经理们都会取一个应景的代号，这一次，为什么是“红树林”？\"");
        message.setMessageUrl("https://mp.weixin.qq.com/s?spm=a219a.7629140.0.0.EUDyWG&__biz=MzA4NjMwMTA2Ng==&mid=2650316842&idx=1&sn=60da3ea2b29f1dcc43a7c8e4a7c97a16&scene=2&srcid=09189AnRJEdIiWVaKltFzNTw&from=timeline&isappinstalled=0&key=&ascene=2&uin=&devicetype=android-23&version=26031933&nettype=WIFI");
        SendResult result = client.send(CHATBOT_WEBHOOK, message);
        System.out.println(result);
    }
    
    
    /* --------------------------TextMessage---------------------------------------  */
    
    /**
     * 只发送文本消息
     * @throws Exception
     */
    @Test
    public void testSendTextMessage() throws Exception {
        TextMessage message = new TextMessage("我就是我, 是不一样的烟火");
        SendResult result = client.send(CHATBOT_WEBHOOK, message);
        System.out.println(result);
    }

    /**
     * 发送文本消息并@ 指定人
     * 测试 ok 
     * @throws Exception
     */
    @Test
    public void testSendTextMessageWithAt() throws Exception {
        TextMessage message = new TextMessage("书籍是人类进步的阶梯.生活里没有书籍,就好像大地没有阳光;" +
        		"智慧里没有书籍,就好像鸟儿没有翅膀.一本好书就是一位好老师,它可以塑造一个完美的灵魂");
        ArrayList<String> atMobiles = new ArrayList<String>();
        //atMobiles.add("17600279965");
        atMobiles.add("18611075593");
        message.setAtMobiles(atMobiles);
        SendResult result = client.send(CHATBOT_WEBHOOK, message);
        System.out.println(result);
    }

    /**
     * 发送文字消息 并 @ 群里所有人
     * @throws Exception
     */
    @Test
    public void testSendTextMessageWithAtAll() throws Exception {
        TextMessage message = new TextMessage("书籍是人类进步的阶梯.生活里没有书籍,就好像大地没有阳光;" +
        		"智慧里没有书籍,就好像鸟儿没有翅膀.一本好书就是一位好老师,它可以塑造一个完美的灵魂");
        message.setIsAtAll(true);
        SendResult result = client.send(CHATBOT_WEBHOOK, message);
        System.out.println(result);
    }

    /**
     * 发送文字消息 并 @ 群里指定人（个人、所有）
     * @throws Exception
     */
    @Test
    public void testSendTextMessageWithAtAndAtAll() throws Exception {
        TextMessage message = new TextMessage("我就是我, 是不一样的烟火");
        ArrayList<String> atMobiles = new ArrayList<String>();
        atMobiles.add("186xxxx0822");
        message.setAtMobiles(atMobiles);
        message.setIsAtAll(true);
        SendResult result = client.send(CHATBOT_WEBHOOK, message);
        System.out.println(result);
    }
    
    /* ----------------SingleTargetActionCardMessage-----------------------  */
    @Test
    public void testSingleTargetActionCardMessage() throws Exception {
        SingleTargetActionCardMessage message = new SingleTargetActionCardMessage();
        message.setBannerUrl("http://img01.taobaocdn.com/top/i1/LB1GCdYQXXXXXXtaFXXXXXXXXXX");
        message.setTitle("2017年你要读的十本书");
        message.setBriefTitle("2017年你要读的十本书");
        message.setBriefText("书籍是人类进步的阶梯.生活里没有书籍,就好像大地没有阳光;智慧里没有书籍,就好像鸟儿没有翅膀.一本好书就是一位好老师,它可以塑造一个完美的灵魂");
        message.setSingleTitle("查看更多");
        message.setSingleURL("http://www.dingtalk.com");

        SendResult result = client.send(CHATBOT_WEBHOOK, message);
        System.out.println(result);

    }

    @Test
    public void testSendSingleTargetActionCardMessageWithoutBanner() throws Exception {
        SingleTargetActionCardMessage message = new SingleTargetActionCardMessage();
        message.setTitle("2017年你要读的十本书");
        message.setBriefTitle("2017年你要读的十本书");
        message.setBriefText("书籍是人类进步的阶梯”.生活里没有书籍,就好像大地没有阳光;智慧里没有书籍,就好像鸟儿没有翅膀.一本好书就是一位好老师,它可以塑造一个完美的灵魂");
        message.setSingleTitle("查看更多");
        message.setSingleURL("http://www.dingtalk.com");

        SendResult result = client.send(CHATBOT_WEBHOOK, message);
        System.out.println(result);
    }

    @Test
    public void testSendSingleTargetActionCardMessageHideAvatar() throws Exception {
        SingleTargetActionCardMessage message = new SingleTargetActionCardMessage();
        message.setTitle("2017年你要读的十本书");
        message.setBriefTitle("2017年你要读的十本书");
        message.setHideAvatar(true); // 影藏发送人（机器人小小钉钉）头像姓名
        message.setBriefText("书籍是人类进步的阶梯”.生活里没有书籍,就好像大地没有阳光;智慧里没有书籍,就好像鸟儿没有翅膀.一本好书就是一位好老师,它可以塑造一个完美的灵魂");
        message.setSingleTitle("查看更多");
        message.setSingleURL("http://www.dingtalk.com");

        SendResult result = client.send(CHATBOT_WEBHOOK, message);
        System.out.println(result);
    }
    
    /* ---------------- MarkdownMessage 省略   -----------------------  */
    
//   核心方法 
/*    HttpClient httpclient = HttpClients.createDefault();
    public SendResult send(String webhook, Message message) throws IOException{

        HttpPost httppost = new HttpPost(webhook);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        StringEntity se = new StringEntity(message.toJsonString(), "utf-8");
        httppost.setEntity(se);

        SendResult sendResult = new SendResult();
        HttpResponse response = httpclient.execute(httppost);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String result = EntityUtils.toString(response.getEntity());
            JSONObject obj = JSONObject.parseObject(result);

            Integer errcode = obj.getInteger("errcode");
            sendResult.setErrorCode(errcode);
            sendResult.setErrorMsg(obj.getString("errmsg"));
            sendResult.setIsSuccess(errcode.equals(0));
        }
        return sendResult;
    }*/
    
}
