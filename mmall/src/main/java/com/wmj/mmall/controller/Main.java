package com.wmj.mmall.controller;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        AutoGenerator autoGenerator = new AutoGenerator();
        DataSourceConfig dataSourceConfig = new DataSourceConfig();

        //配置数据库
        dataSourceConfig.setDbType(DbType.MYSQL);  //设置数据库是啥
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");  //设置数据库驱动
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("");
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/mmall?useUnicode=true&characterEncoding=UTF-8");
        autoGenerator.setDataSource(dataSourceConfig);

        //全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOpen(false);   //设为不打开
        globalConfig.setOutputDir(System.getProperty("user.dir")+"/src/main/java");   //设置代码生成的位置
        globalConfig.setAuthor("菜啊");  //随便设的作者名
        globalConfig.setServiceName("%sService");   //设置Service层的名字  %s 可以把默认Service前的I去掉
        autoGenerator.setGlobalConfig(globalConfig);

        //配置包信息
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.wmj.mmall");  //在那个目录下生成
        packageConfig.setEntity("entity");
        packageConfig.setMapper("mapper");
        packageConfig.setController("controller");
        packageConfig.setService("service");
        packageConfig.setServiceImpl("service.impl");
        autoGenerator.setPackageInfo(packageConfig);

        //配置生成策略
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setEntityLombokModel(true);   //生成lombok
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);  //数据库里的下划线命名转为驼峰命名 针对表名
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);  //针对字段名
        //自动装配 时间更新等 的 字段
        List<TableFill> list = new ArrayList<>();
        TableFill tableFill = new TableFill("create_time",FieldFill.INSERT);
        TableFill tableFill1 = new TableFill("update_time",FieldFill.INSERT_UPDATE);
        list.add(tableFill);
        list.add(tableFill1);
        strategyConfig.setTableFillList(list);
        autoGenerator.setStrategy(strategyConfig);

        autoGenerator.execute();
    }
}
