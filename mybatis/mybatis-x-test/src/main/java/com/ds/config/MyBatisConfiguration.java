package com.ds.config;

import com.ds.mybatisx.session.SqlSessionFactory;
import com.ds.mybatisx.spring.annotion.MapperxScan;
import com.ds.mybatisx.starter.MyBatsixStarter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author ds
 * @date 2023/11/21
 * @description
 */
@Configuration
@MapperxScan(basePackages = "com.ds.mapper")
@ComponentScan("com.ds")
public class MyBatisConfiguration {

    @Bean
    public SqlSessionFactory sqlSessionFactory() {
        return MyBatsixStarter.getMyBatsixStarter().getSqlSessionFactory("mybatisx.xml");
    }

}
