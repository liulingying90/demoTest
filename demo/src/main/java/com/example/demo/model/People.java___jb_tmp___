package com.example.demo.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 比较两个对象时，首先判断两个对象是否具有相同的地址，如果是同一个对象的引用，则直接放回true;
 * 如果地址不一样，则证明不是引用同一个对象，接下来就是挨个去比较两个字符串对象的内容是否一致，
 * 完全相等返回true,否则false;
 */
public class People {
    private String name;
    private String phoneNumber;

    public People(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public boolean equals(Object arg0){
        // TODO Auto-generated method stub
        People p=(People) arg0;
        return name.equals(p.name) && phoneNumber.equals(p.phoneNumber);
    }

    public int hashCode(){
        // TODO Auto-generated method stub
        String str=name+phoneNumber;
        return str.hashCode();
    }

    public static void main(String[] args){
        List<People> listPeople=new ArrayList<People>();
        listPeople.add(new People("张三","111111"));
        listPeople.add(new People("张三","222222"));
        listPeople.add(new People("李四","333333"));
        listPeople.add(new People("张三","222222"));
        Set<People> setData=new HashSet<People>();
        setData.addAll(listPeople);
        System.out.println("list: "+listPeople.toString());
        System.out.println("set: "+setData.toString());
    }
}
