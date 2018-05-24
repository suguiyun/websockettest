package com.mycompany.myapp.config;

import com.mycompany.myapp.security.AuthoritiesConstants;
import io.github.jhipster.config.JHipsterProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfiguration extends AbstractWebSocketMessageBrokerConfigurer {

    private final Logger log = LoggerFactory.getLogger(WebsocketConfiguration.class);

    public static final String IP_ADDRESS = "IP_ADDRESS";

    private final JHipsterProperties jHipsterProperties;

    public static Map<String ,Object> map = new HashMap<>();

    public WebsocketConfiguration(JHipsterProperties jHipsterProperties) {
        this.jHipsterProperties = jHipsterProperties;
    }

    /**
     * configureMessageBroker:我的理解是消息的传递方式是一对多的topic方式
     * @param config
     */

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic","/user");
        config.setUserDestinationPrefix("/user/");
        config.setApplicationDestinationPrefixes("/app");//走@messageMapping
    }

    /**
     * registry.addEndpoint("/websocket/tracker")：   客户端与服务器端建立连接   连接地址是：/websocket/tracker
     * setHandshakeHandler(defaultHandshakeHandler())：客户端与服务器端建立连接后默认执行的方法
     * setAllowedOrigins(allowedOrigins)：允许的request Origin
     * withSockJS()？有待讨论？？？？
     * setInterceptors(httpSessionHandshakeInterceptor())：建立握手前后对请求进行拦截
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        String[] allowedOrigins = Optional.ofNullable(jHipsterProperties.getCors().getAllowedOrigins()).map(origins -> origins.toArray(new String[0])).orElse(new String[0]);
        registry.addEndpoint("/websocket/tracker1")    ///websocket/tracker就是websocket的端点，客户端需要注册这个端点进行链接
            .setHandshakeHandler(defaultHandshakeHandler())
            .setAllowedOrigins(allowedOrigins)
            .withSockJS()
            .setInterceptors(httpSessionHandshakeInterceptor());
    }

    /**
     * 方法翻译后得意思：握手拦截
     * 我觉得应该是：客户端和服务器端建立连接的时候屏蔽一些无效的拦截，比如我们的应用场景可能是需要用户必须登入，所以可以通过判断将这些拦截通通过滤掉
     * 方法：分为建立握手前拦截：我的理解可能是过滤掉服务器中一些无效连接   建立握手后的拦截：带讨论？？？？？？
     * @return
     */
    @Bean
    public HandshakeInterceptor httpSessionHandshakeInterceptor() {
        return new HandshakeInterceptor() {

            @Override
            public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
                if (request instanceof ServletServerHttpRequest) {
                    ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
                    attributes.put(IP_ADDRESS, servletRequest.getRemoteAddress());
                }
                return true;
            }

            @Override
            public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
                Principal principal = request.getPrincipal();
                map.put(principal.getName(),principal);
            }
        };
    }


    /**
     * 方法翻译后得意思：默认握手处理程序
     * 我觉得应该是：客户端和服务器端建立连接后默认执行的方法
     * 方法：获取当前连接的用户身份，如果没有身份new一个匿名的身份
     * @return
     */
    private DefaultHandshakeHandler defaultHandshakeHandler() {
        return new DefaultHandshakeHandler() {
            @Override
            protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
                Principal principal = request.getPrincipal();
                if (principal == null) {
                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    authorities.add(new SimpleGrantedAuthority(AuthoritiesConstants.ANONYMOUS));
                    principal = new AnonymousAuthenticationToken("WebsocketConfiguration", "anonymous", authorities);
                }
                return principal;
            }
        };
    }
}
