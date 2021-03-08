package com.example.helloworld.date;

public class WenDate {
    private String dateandtime;
    private String address;
    private Float wendu;
    private String special;
    private String stuid;
    private String name;
    private String stuclass;
    private Long msec;

    public Long getMsec() {
        return msec;
    }

    public void setMsec(Long msec) {
        this.msec = msec;
    }

    public String getStuclass() {
        return stuclass;
    }

    public void setStuclass(String stuclass) {
        this.stuclass = stuclass;
    }

    public Float getWendu() {
        return wendu;
    }
    public void setWendu(Float wendu) {
        this.wendu = wendu;
    }
    public String getDateandtime() {
        return dateandtime;
    }

    public void setDateandtime(String dateandtime) {
        this.dateandtime = dateandtime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WenDate() {
    }
    public WenDate(String dateandtime,String address,Float wendu,String special,String stuid,String name,String stuclass,Long msec)
    {
        this.dateandtime=dateandtime;
        this.address=address;
        this.wendu=wendu;
        this.special=special;
        this.stuid=stuid;
        this.name=name;
        this.msec=msec;
        this.stuclass=stuclass;
    }
    public WenDate(String dateandtime,String address,Float wendu,String special,String stuid,String name,String stuclass)
    {
        this.dateandtime=dateandtime;
        this.address=address;
        this.wendu=wendu;
        this.special=special;
        this.stuid=stuid;
        this.name=name;
        this.stuclass=stuclass;
    }
}
