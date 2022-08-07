package com.xin.gateway.config;

import cn.dev33.satoken.reactor.context.SaReactorSyncHolder;
import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author xin
 */
@Configuration
public class SaTokenConfig {

    @Bean
    public SaReactorFilter getSaReactorFilter(){
        return new SaReactorFilter()
                // 拦截地址
                .addInclude("/**")
                // 开放地址
                .addExclude("/favicon.ico")
                // 鉴权方法，每次访问进入
                .setAuth(r->{
                    // 登录认证：除登录接口都需要认证
                    SaRouter.match(Collections.singletonList("/**"),
                            this.getNotMatchList(),
                            StpUtil::checkLogin);
                    // 权限认证：不同接口访问权限不同
                    SaRouter.match("/api/aggregateInfo/jokeCollection/**", () -> StpUtil.checkPermission("api:aggregateInfo:jokeCollection"));
                    SaRouter.match("/api/aggregateInfo/musicInformation/**", () -> StpUtil.checkPermission("api:aggregateInfo:musicInformation"));
                })// setAuth方法异常处理
                .setError(e -> {
                    //设置错误返回格式为JSON
                    ServerWebExchange exchange = SaReactorSyncHolder.getContent();
                    exchange.getResponse().getHeaders().set("Content-Type", "application/json; charset=utf-8");
                    return SaResult.error(e.getMessage());
                });
    }

    private List<String> getNotMatchList(){
        List<String> notMatchList = new ArrayList<>();
        notMatchList.add("/api/aggregateInfo/admin/login");
        notMatchList.add("/api/aggregateInfo/admin/register");
        notMatchList.add("/api/aggregateInfo/admin/getShearCaptcha");
        return notMatchList;
    }
}
