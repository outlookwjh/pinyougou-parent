package com.pinyougou.entity;

import com.pinyougou.pojo.TbBrand;

import java.io.Serializable;
import java.util.List;

public class Result implements Serializable{

    private boolean success;

    private String message;

    private TbBrand brand;


    public Result(boolean success, String message, TbBrand brand) {
        this.success = success;
        this.message = message;
        this.brand = brand;
    }

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public TbBrand getBrand() {
        return brand;
    }

    public void setBrand(TbBrand brand) {
        this.brand = brand;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
