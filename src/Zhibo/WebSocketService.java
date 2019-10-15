package Zhibo;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArrayList;

@ServerEndpoint("/onlineServer")

/*
���������ֱ�����õ�WebRTC������Ȼ��ͨ��websocket�������Ƶ��Ϣ����java��̨����̨�������Ƶת����
˭ͨ��watch.html���ӣ��Ϳ���java��̨����ֱ������Ƶ��Դ���Ϳ����Լ������ϲ���
*/
public class WebSocketService {

   private static int onlineCount=0;
   private static CopyOnWriteArrayList<WebSocketService> webSocketsSet = new CopyOnWriteArrayList<WebSocketService>();
   private Session session;
   @OnOpen
   public void onopen(Session session){
       this.session=session;
       webSocketsSet.add(this);
       System.out.println("��������");
       //addOnlineCount();
   }
   @OnClose
   public void onclose(){
       webSocketsSet.remove(this);
       System.out.println("�ر�");
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
