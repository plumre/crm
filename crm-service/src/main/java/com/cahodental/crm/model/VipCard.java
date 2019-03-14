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
public class VipCard implements Serializable{

    private Long id;//会员号(与客户的id一致)
    private BigDecimal balance;//余额
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

    public BigDecimal getBalance(){
        return balance;
    }
    public void setBalance(BigDecimal balance){
        this.balance = balance;
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
