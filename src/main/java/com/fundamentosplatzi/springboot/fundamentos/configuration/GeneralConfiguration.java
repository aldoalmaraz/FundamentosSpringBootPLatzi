package com.fundamentosplatzi.springboot.fundamentos.configuration;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithPropertiesImplement;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@EnableConfigurationProperties(UserPojo.class)
@Configuration
@PropertySource("classpath:conection.properties") // Aqui llamas a classpatch de la clase conection.properties de resources y con esto tenemos archivo propiedades nueva.

//con @value te traes la variable que creaste en la clase de configuracion application propierties
public class GeneralConfiguration {
@Value("${value.name}")
private String name;

@Value("${value.apellido}")
private String apellido;

@Value("${value.random}")
private String random;

@Value("${jdbc.url}")
private String jdbcUrl;   // con esto tenemos los valores del archivo de propiedades en resources (conection.propierties)

@Value("${driver}")
private String driver;

@Value("${username}")
private String username;

@Value("${password}")
private String password;


@Bean
    public MyBeanWithProperties function(){
    return new MyBeanWithPropertiesImplement(name,apellido);
}

//IMPLEMENTACION DE UNA NUEVA BASE DE DATOS.

    @Bean
    public DataSource datasource(){
        DataSourceBuilder dataSourceBuilder= DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driver);
        dataSourceBuilder.url(jdbcUrl);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);
        return dataSourceBuilder.build();
    }



}


