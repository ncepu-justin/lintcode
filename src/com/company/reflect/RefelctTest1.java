package com.company.reflect;

import java.lang.reflect.Constructor;

/**
 *
 * Created by Justin
 * 2018/8/11  10:11
 * 1.获取构造方法：
 1).批量的方法：
 public Constructor[] getConstructors()：所有"公有的"构造方法
 public Constructor[] getDeclaredConstructors()：获取所有的构造方法(包括私有、受保护、默认、公有)

 2).获取单个的方法，并调用：
 public Constructor getConstructor(Class... parameterTypes):获取单个的"公有的"构造方法：
 public Constructor getDeclaredConstructor(Class... parameterTypes):获取"某个构造方法"可以是私有的，或受保护、默认、公有；

 调用构造方法：
 Constructor-->newInstance(Object... initargs)

 2、newInstance是 Constructor类的方法（管理构造函数的类）
 api的解释为：
 newInstance(Object... initargs)
 使用此 Constructor 对象表示的构造方法来创建该构造方法的声明类的新实例，并用指定的初始化参数初始化该实例。
 它的返回值是T类型，所以newInstance是创建了一个构造方法的声明类的新实例对象。并为之调用
 */
public class RefelctTest1 {

    public static void main(String[] args) throws Exception {
        //1.加载Class对象
        Class clazz = Class.forName("com.company.reflect.Student");


        //2.获取所有公有构造方法
        System.out.println("**********************所有公有构造方法*********************************");
        Constructor[] conArray = clazz.getConstructors();
        for(Constructor c : conArray){
            System.out.println(c);
        }


        System.out.println("************所有的构造方法(包括：私有、受保护、默认、公有)***************");
        conArray = clazz.getDeclaredConstructors();
        for(Constructor c : conArray){
            System.out.println(c);
        }

        System.out.println("*****************获取公有、无参的构造方法*******************************");
        Constructor con = clazz.getConstructor(null);
        //1>、因为是无参的构造方法所以类型是一个null,不写也可以：这里需要的是一个参数的类型，切记是类型
        //2>、返回的是描述这个无参构造函数的类对象。

        System.out.println("con = " + con);
        //调用构造方法
       /* Object obj = con.newInstance();*/
        	/*System.out.println("obj = " + obj);
        	Student stu = (Student)obj;*/

        System.out.println("******************获取私有构造方法，并调用*******************************");
        con = clazz.getDeclaredConstructor(boolean.class);
        System.out.println(con);
        //调用构造方法
        con.setAccessible(true);//暴力访问(忽略掉访问修饰符)
        Object obj = con.newInstance(true);
    }

}
