package com.fundamentosplatzi.springboot.fundamentos.bean;

public class MyBeanWithPropertiesImplement implements MyBeanWithProperties{
String nombre;
String apellido;


    public MyBeanWithPropertiesImplement(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    @Override
    public String Function() {
        return nombre +"-"+apellido;
    }
}
