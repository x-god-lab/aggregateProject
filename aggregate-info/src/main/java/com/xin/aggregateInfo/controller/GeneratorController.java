package com.xin.aggregateInfo.controller;

import cn.hutool.core.date.DatePattern;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.xin.aggregateInfo.pojo.dto.GeneratorTableDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Types;
import java.util.Collections;

/**
 * @description
 * @author:xin
 * @create:2023-09-23 15:42
 **/
@RestController
@RequestMapping("generator")
public class GeneratorController {

    @Value("${spring:datasource:dynamic:datasource:master:url}")
    private String url;

    @Value("${spring:datasource:dynamic:datasource:master:username}")
    private String username;

    @Value("${spring:datasource:dynamic:datasource:master:password}")
    private String password;

    @PostMapping("generatorCode")
    public void generatorCode(@RequestBody GeneratorTableDTO params){
        FastAutoGenerator.create(url, username, password)
                // 设置作者
                .globalConfig(builder -> builder.author("xin")
                        .enableSwagger() // 开启 swagger 模式
                        .dateType(DateType.TIME_PACK)
                        .commentDate(DatePattern.NORM_DATETIME_PATTERN)
                        .disableOpenDir()
                        // 覆盖已生成文件
                        .outputDir("E:\\code\\Java\\aggregateProject\\aggregate-info\\src\\main\\resources\\generator\\code"))
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);

                }))
                .packageConfig(builder -> {
                    builder.parent("com.xin.aggregateInfo") // 设置父包名
                            .entity("pojo.entity")
                            .controller("controller")
                            .service("service")
                            .serviceImpl("service.impl")
                            .mapper("mappers.master")
                            .xml("mappers.master")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "E:\\code\\Java\\aggregateProject\\aggregate-info\\src\\main\\resources\\generator\\code\\xml")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(params.getTableName().split(",")); // 设置需要生成的表名
                    builder.entityBuilder().disableSerialVersionUID().enableLombok().enableTableFieldAnnotation().enableFileOverride();
                    builder.serviceBuilder().formatServiceFileName("%sService").enableFileOverride();
                    builder.mapperBuilder().enableFileOverride().enableBaseColumnList().enableBaseResultMap();
                    builder.controllerBuilder().enableFileOverride().enableRestStyle();
                })
//                .templateConfig(new Consumer<TemplateConfig.Builder>() {
//                    @Override
//                    public void accept(TemplateConfig.Builder builder) {
//                        builder.entity("templates/entity.java");
//                    }
//                })
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
