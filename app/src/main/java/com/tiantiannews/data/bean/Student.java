package com.tiantiannews.data.bean;


public class Student {

    public int id;
    public String name; //姓名
    public String age; //年龄
    public int sex;  //性别
    public String address; //家庭地址
    public String homeNumber; //家庭电话
    public String fatherPhone; //父亲手机
    public String motherPhone; //母亲手机
    public String className; //班级
    public String gradeName; //年级
    public String letter; //拼音
    public String revisitDate; //回访时间

    public Student(int id, String name, String age, int sex, String address, String homeNumber, String fatherPhone, String motherPhone, String className, String gradeName, String letter, String revisitDate) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.address = address;
        this.homeNumber = homeNumber;
        this.fatherPhone = fatherPhone;
        this.motherPhone = motherPhone;
        this.className = className;
        this.gradeName = gradeName;
        this.letter = letter;
        this.revisitDate = revisitDate;
    }
}
