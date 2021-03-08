package com.example.helloworld.date;

public class StudentDate {
    private String stuID;
    private String stuName;
    private String stuPhone;
    private String stuClass;

    public String getStuID() {
        return stuID;
    }

    public String getStuName() {
        return stuName;
    }

    public String getStuPhone() {
        return stuPhone;
    }

    public String getStuClass() {
        return stuClass;
    }

    public void setStuID(String stuID) {
        this.stuID = stuID;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public void setStuPhone(String stuPhone) {
        this.stuPhone = stuPhone;
    }

    public void setStuClass(String stuClass) {
        this.stuClass = stuClass;
    }
    public StudentDate(){}

    public StudentDate(String stuClass,String stuID,String stuName,String stuPhone) {
        this.stuClass = stuClass;
        this.stuID=stuID;
        this.stuName=stuName;
        this.stuPhone=stuPhone;
    }
}
