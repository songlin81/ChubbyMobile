package com.chubbymobile.wwh.chubbymobile.DAL;

/**
 * Created by wwh on 16/3/2.
 */
public class Person{
    public int _id;
    public String name;
    public int age;
    public String info;

    public Person(){}

    public Person(String name, int age, String info){
        this.name = name;
        this.age = age;
        this.info = info;
    }
}