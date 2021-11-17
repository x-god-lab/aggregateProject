package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author xin
 * @create 2021/11/17 1:53
 * @description
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi(){
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("聚合项目")
                .description("自己写起来玩的项目，所有可以集成的东西都慢慢集成")
                .version("1.0")
                .contact(new Contact("xin","https://www.javafuture.cn/","xw6464918@gmail.com"))
                .license("Spring")
                .licenseUrl("https://www.baidu.com/")
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                //扫描那些controller
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.info.aggregateinfo.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo);
    }
}
