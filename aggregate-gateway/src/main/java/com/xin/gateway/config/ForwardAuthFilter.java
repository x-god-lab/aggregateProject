package com.xin.gateway.config;

import cn.dev33.satoken.id.SaIdUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author xin
 * @create 2022/01/24 1:14
 * @description 全局过滤器，为请求添加 Id-Token
 **/
@Component
public class ForwardAuthFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest newRequest = exchange
                .getRequest()
                .mutate()
                .header(SaIdUtil.ID_TOKEN, SaIdUtil.getToken())
                //.header(StpUtil.getTokenName(), StpUtil.getTokenValue())
                .build();
        ServerWebExchange newExchange = exchange.mutate().request(newRequest).build();
        return chain.filter(newExchange);
    }
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // 注册注解拦截器，并排除不需要注解鉴权的接口地址 (与登录拦截器无关)
//      //  log.info("sa-token拦截器");
//        registry.addInterceptor(new SaAnnotationInterceptor()).addPathPatterns("/**");
//    }
}
