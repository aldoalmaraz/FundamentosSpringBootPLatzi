package com.fundamentosplatzi.springboot.fundamentos.configuration;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithPropertiesImplement;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@EnableConfigurationProperties(UserPojo.class)
@Configuration

//con @value te traes la variable que creaste en la clase de configuracion application propierties
public class GeneralConfiguration {
@Value("${value.name}")
private String name;

@Value("${value.apellido}")
private String apellido;

@Value("${value.random}")
private String random;

@Bean
    public MyBeanWithProperties function(){
    return new MyBeanWithPropertiesImplement(name,apellido);
}

//IMPLEMENTACION DE UNA NUEVA BASE DE DATOS.

    @Bean
    public DataSource datasource(){
        DataSourceBuilder dataSourceBuilder= DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.h2.Driver");
        dataSourceBuilder.url("jdbc:h2:mem:testdb");
        dataSourceBuilder.username("SA");
        dataSourceBuilder.password("");
        return dataSourceBuilder.build();
    }



}


