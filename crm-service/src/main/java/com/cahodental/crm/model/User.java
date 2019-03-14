package com...model;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.math.*;
import java.io.Serializable;
import lombok.Data;
/**
* 代码为自动生成 Created by www.magicalcoder.com
* 如果你改变了此类 read 请将此行删除
* 欢迎加入官方QQ群:323237052
*/
@Data
public class User implements Serializable{

    private Long id;//用户id
    private String name;//姓名
    private Byte age;//年龄
    private Boolean gender;//性别
    private String phone;//电话
    private String email;//邮箱
    private String address;//地址
    private String type;//用户类型：0客户1医生2护士3老板
    private Long shopId;//店铺id
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Timestamp createTime;//创建时间
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Timestamp updateTime;//修改时间

    
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public Byte getAge(){
        return age;
    }
    public void setAge(Byte age){
        this.age = age;
    }

    public Boolean getGender(){
        return gender;
    }
    public void setGender(Boolean gender){
        this.gender = gender;
    }

    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }

    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type = type;
    }

    public Long getShopId(){
        return shopId;
    }
    public void setShopId(Long shopId){
        this.shopId = shopId;
    }

    public Timestamp getCreateTime(){
        return createTime;
    }
    public void setCreateTime(Timestamp createTime){
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime(){
        return updateTime;
    }
    public void setUpdateTime(Timestamp updateTime){
        this.updateTime = updateTime;
    }
}
