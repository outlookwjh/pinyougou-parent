package com.pinyougou.manager.controller;

import java.io.IOException;
import java.util.*;

import com.pinyougou.entity.PageResult;
import com.pinyougou.entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Reference
    private BrandService brandService;

    @RequestMapping("/findAll")
    public List<TbBrand> findAll() {
        List<TbBrand> all = brandService.findAll();
        return all;
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody TbBrand brand,@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        return brandService.findPage(brand,pageNum, pageSize);
    }

    @RequestMapping("/insertBrand")
    public Result insertBrand(@RequestBody TbBrand brand)
    {
        try {

            brandService.addBrand(brand);
            return new Result(true,"add brand success");

        }catch (Exception e)
        {
            return new Result(false,"add brand failure");
        }
    }

    @RequestMapping("/modifyBrand")
    public Result modifyBrand(@RequestBody TbBrand brand)
    {
        try {
            brandService.modifyBrand(brand);
            return new Result(true,"success");
        }catch (Exception e){
            return new Result(false,"modify brand failure");
        }
    }

    @RequestMapping("/findBrandById")
    public Result findOne(String id)
    {
        try {

            TbBrand brandById = brandService.findBrandById(id);
            return new Result(true,"success", brandById);
        }catch (Exception e){
            return new Result(false,"failure",null);
        }

    }

    @RequestMapping("/batchDel")
    public Result batchDel(String[] ids){
        try {
            brandService.batchDel(ids);
            return new Result(true,"success",null);
        }catch (Exception e){
            return new Result(false,"failure",null);
        }
    }

    @RequestMapping("/getBrandOptions")
    public List<Map<String,String>> getOptions(){

        List<Map<String, String>> brandOptions = brandService.getBrandOptions();

        return brandOptions;

    }
}
