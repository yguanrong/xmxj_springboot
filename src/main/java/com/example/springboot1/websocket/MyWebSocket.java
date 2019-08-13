package com.example.springboot1.websocket;

import com.alibaba.fastjson.JSONObject;
import com.example.springboot1.websocket.model.WebSocketMsg;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;


@ServerEndpoint(value = "/websocket/{name}")
@Component
public class MyWebSocket {

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static ConcurrentHashMap<String,MyWebSocket> webSocketSet =
            new ConcurrentHashMap<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;


    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session,@PathParam(value = "name") String name) {
        this.session = session;
        webSocketSet.put(name,this);     //加入set中
        System.out.println("有新连接加入！当前在线人数为:" + webSocketSet.size());
        System.out.println("新用户的name为" + name);
//        try {
//            sendMessage("有新用户加入");
//        } catch (IOException e) {
//            System.out.println("IO异常");
//        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(@PathParam(value = "name") String name) {
        webSocketSet.remove(name);  //从set中删除
        System.out.println("有一连接关闭！当前在线人数为" + webSocketSet.size());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     * */
    @OnMessage
    public void onMessage(String message, Session session) {

        WebSocketMsg msg = JSONObject.parseObject(message,WebSocketMsg.class);
        System.out.println("来自客户端的消息:" + msg.getMsg());
        if (msg.getType() == 0){
            sendAll(msg.getMsg());
        }else {
            sendToOne(msg.getName(),msg.getMsg());
        }
    }

    /**
     * 发生错误时调用
     @OnError
     */
    @OnError
     public void onError(Session session, Throwable error) {
     System.out.println("发生错误,远程主机异常退出");
//     error.printStackTrace();
     }


    /**
     * 发送消息
     * @param message
     * @throws IOException
     */
     private void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
     }

     /**
      * 群发自定义消息
      * */
    public static void sendAll(String message){
        //群发消息

        for (String name : webSocketSet.keySet()) {
            try {

                webSocketSet.get(name).sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 指定发送
     * @param name
     * @param message
     */
    public void sendToOne(String name,String message){
        try {
            if(webSocketSet.get(name) == null){
                this.session.getBasicRemote().sendText("用户不在线上");
            }else{
                webSocketSet.get(name).session.getBasicRemote().sendText(message);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
