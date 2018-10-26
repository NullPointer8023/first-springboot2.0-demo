package com.ice.firstappdemo.config;

import com.ice.firstappdemo.domain.User;
import com.ice.firstappdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;

import java.util.Collection;

/**
 * @author allin
 * @date 2018/10/25.
 * 路由器函数 配置
 */
@Configuration
public class RouterFunctionConfiguration {

    /**
     * Web Flux
     * servlet
     *  请求接口：servletRequest 或者 HttpServletRequest
     *  响应接口：servletResponse 或者 HttpServletResponse
     * Spring 5.0 重新定义了服务请求和相应接口
     *  请求接口：serverRequest
     *  响应接口：serverResponse
     * 即可支持servlet规范，也可以支持自定义，比如Netty (Web Server)
     *
     * 例：定义GET请求，返回所有用户对象 URI：person/find/all
     * Autowired：通过方法参数
     * 注入：字段参数、property注入、方法注入、get set注入、构造器注入
     *
     * Flux: 0--N 个对象集合
     * Mono：0--1 个对象集合
     * Reactive 中的Flux 或者 Mono是异步处理（非阻塞）
     * 集合基本上是同步处理（阻塞）
     *
     * Flux 或者 Mono 都是一个 Publisher
     *
     */
    @Bean
    @Autowired
    public RouterFunction<ServerResponse> personFindAll(UserRepository userRepository){
        return RouterFunctions.route(RequestPredicates.GET("/person/find/all"),
                request -> {
                    Collection<User> users = userRepository.findAll();
                    Flux<User> userFlux = Flux.fromIterable(users);
                    return ServerResponse.ok().body(userFlux,User.class) ;
                });
    }

}
