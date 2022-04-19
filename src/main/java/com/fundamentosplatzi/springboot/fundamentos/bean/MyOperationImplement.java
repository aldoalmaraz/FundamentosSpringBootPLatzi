package com.fundamentosplatzi.springboot.fundamentos.bean;
// Este bin sumara 1
public class MyOperationImplement implements MyOperation{
    @Override
    public int suma(int number) {
        return number+1;
    }
}
