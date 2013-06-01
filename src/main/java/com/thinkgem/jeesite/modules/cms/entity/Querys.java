/*
 * Copyright (c) 2013.
 * whatlookingfor@gmail.com
 */

package com.thinkgem.jeesite.modules.cms.entity;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: lookingfor
 * Date: 13-5-31
 * Time: 下午11:28
 * To change this template use File | Settings | File Templates.
 */
public class Querys implements Serializable{
    private String goods_code;
    private String goods_name;
    private String consumer_code;
    private String consumer_name;
    private Long consumer_id=1L;
    private Long user_id;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getGoods_code() {
        return goods_code;
    }

    public void setGoods_code(String goods_code) {
        this.goods_code = goods_code;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getConsumer_name() {
        return consumer_name;
    }

    public void setConsumer_name(String consumer_name) {
        this.consumer_name = consumer_name;
    }

    public String getConsumer_code() {
        return consumer_code;
    }

    public void setConsumer_code(String consumer_code) {
        this.consumer_code = consumer_code;
    }

    public Long getConsumer_id() {
        return consumer_id;
    }

    public void setConsumer_id(Long consumer_id) {
        this.consumer_id = consumer_id;
    }
}
