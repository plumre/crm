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
public class OrderInfo implements Serializable{

    private Long id;//订单id
    private Long userId;//客户id
    private BigDecimal orderAmount;//订单金额
    private Boolean isPromotion;//是否参加活动
    private Byte discount;//折扣
    private Long referer;//推荐人id
    private Boolean isPay;//是否支付
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Timestamp payTime;//支付时间
    private Boolean haveGift;//是否有礼物
    private String giftDetail;//礼物详情
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

    public Long getUserId(){
        return userId;
    }
    public void setUserId(Long userId){
        this.userId = userId;
    }

    public BigDecimal getOrderAmount(){
        return orderAmount;
    }
    public void setOrderAmount(BigDecimal orderAmount){
        this.orderAmount = orderAmount;
    }

    public Boolean getIsPromotion(){
        return isPromotion;
    }
    public void setIsPromotion(Boolean isPromotion){
        this.isPromotion = isPromotion;
    }

    public Byte getDiscount(){
        return discount;
    }
    public void setDiscount(Byte discount){
        this.discount = discount;
    }

    public Long getReferer(){
        return referer;
    }
    public void setReferer(Long referer){
        this.referer = referer;
    }

    public Boolean getIsPay(){
        return isPay;
    }
    public void setIsPay(Boolean isPay){
        this.isPay = isPay;
    }

    public Timestamp getPayTime(){
        return payTime;
    }
    public void setPayTime(Timestamp payTime){
        this.payTime = payTime;
    }

    public Boolean getHaveGift(){
        return haveGift;
    }
    public void setHaveGift(Boolean haveGift){
        this.haveGift = haveGift;
    }

    public String getGiftDetail(){
        return giftDetail;
    }
    public void setGiftDetail(String giftDetail){
        this.giftDetail = giftDetail;
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
