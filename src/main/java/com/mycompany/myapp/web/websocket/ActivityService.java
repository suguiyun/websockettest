package com.mycompany.myapp.web.websocket;

import com.mycompany.myapp.Constants;
import com.mycompany.myapp.config.WebsocketConfiguration;
import com.mycompany.myapp.web.websocket.dto.ActivityDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

import static com.mycompany.myapp.config.WebsocketConfiguration.IP_ADDRESS;

@Controller
public class ActivityService implements ApplicationListener<SessionDisconnectEvent> {
//public class ActivityService {

    private static final Logger log = LoggerFactory.getLogger(ActivityService.class);

    private final SimpMessageSendingOperations messagingTemplate;

    public ActivityService(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

//    @SubscribeMapping("/topic/activity")
    @SendTo("/topic/tracker")
    public ActivityDTO sendActivity(@Payload ActivityDTO activityDTO, StompHeaderAccessor stompHeaderAccessor, Principal principal) {
        activityDTO.setUserLogin(principal.getName());
        activityDTO.setSessionId(stompHeaderAccessor.getSessionId());
        activityDTO.setIpAddress(stompHeaderAccessor.getSessionAttributes().get(IP_ADDRESS).toString());
        activityDTO.setTime(Instant.now());
        log.debug("Sending user tracking data {}", activityDTO);
        return activityDTO;
    }

    /**
     * ApplicationListener<SessionDisconnectEvent> SessionDisconnectEvent:是session中断事件就会触发onApplicationEvent
     * @param event
     */
    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {

//        ActivityDTO activityDTO = new ActivityDTO();
//        activityDTO.setSessionId(event.getSessionId());
//        activityDTO.setPage("logout");
        if( event.getUser() != null){
            WebsocketConfiguration.map.remove(event.getUser().getName());
        }
//        messagingTemplate.convertAndSend("/topic/tracker", activityDTO);
    }

    @RequestMapping("/websocket/sendMsg")
    @ResponseBody
    public void sendMsg(HttpSession session){
        System.out.println("测试topic发送消息：随机消息" +session.getId());
        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setPage("topic");
        this.messagingTemplate.convertAndSend("/topic/tracker", activityDTO);
    }

    @RequestMapping("/websocket/user/{id}")
    @ResponseBody
    public void sendMsg(HttpSession session, @PathVariable String id){
        System.out.println("测试quere发送消息：随机消息" +session.getId());
        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setPage("user");
        activityDTO.setSessionId(id);
        this.messagingTemplate.convertAndSendToUser(id, "/message", "发给用户"+id);
    }

    /**
     * 获取所有连接的用过户信息
     */
    @RequestMapping("/websocket/userList")
    @ResponseBody
    public Map<String,Object> userList(){
        return WebsocketConfiguration.map;
    }

    @MessageMapping("/message")
//  @SendToUser("/message")//把返回值发到指定队列（“队列”实际不是队列，而是跟上面“主题”类似的东西，只是spring在SendTo的基础上加了用户的内容而已）
    public void testOneToOne(String id ) {
        this.messagingTemplate.convertAndSendToUser(id,"/message","test-------------------"+id);
    }

    public static void main(String[] args) throws Exception{
        Date createdAt =new Date();
        if(createdAt!=null){
            System.out.println(Constants.ymdhms_format.parse(Constants.ymd_format.format(createdAt) + Constants.TIME_START_STR));
            System.out.println(Constants.ymdhms_format.parse(Constants.ymd_format.format(createdAt) + Constants.TIME_END_STR));
        }
    }
}
