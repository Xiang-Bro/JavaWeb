package Zhibo;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArrayList;

@ServerEndpoint("/onlineServer")

/*
在浏览器开直播，用的WebRTC技术，然后通过websocket将你的视频信息传到java后台，后台将这个视频转发，
谁通过watch.html连接，就可向java后台请求直播的视频资源，就可在自己电脑上播放
*/
public class WebSocketService {

   private static int onlineCount=0;
   private static CopyOnWriteArrayList<WebSocketService> webSocketsSet = new CopyOnWriteArrayList<WebSocketService>();
   private Session session;
   @OnOpen
   public void onopen(Session session){
       this.session=session;
       webSocketsSet.add(this);
       System.out.println("建立连接");
       //addOnlineCount();
   }
   @OnClose
   public void onclose(){
       webSocketsSet.remove(this);
       System.out.println("关闭");
   }
   @OnMessage
   public void onmessage(String message, Session session){
       for(WebSocketService ws:webSocketsSet){
           try{
               ws.session.getBasicRemote().sendText(message);
           }catch (Exception e){
               System.out.println(e);
           }
       }
   }
}
