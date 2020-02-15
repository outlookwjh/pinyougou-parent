package com.pinyougou.page.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pinyougou.mapper.TbGoodsDescMapper;
import com.pinyougou.mapper.TbGoodsMapper;
import com.pinyougou.mapper.TbItemCatMapper;
import com.pinyougou.mapper.TbItemMapper;
import com.pinyougou.page.service.ItemPageService;
import com.pinyougou.pojo.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemPageServiceImpl implements ItemPageService {

    @Value("${path}")
    private String path;

    @Autowired
    private FreeMarkerConfig cfg;

    @Autowired
    private TbGoodsMapper goodsMapper;

    @Autowired
    private TbGoodsDescMapper descMapper;

    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Autowired
    private TbItemMapper itemMapper;

    @Override
    public boolean genItemHtml(Long goodsId) {

        Configuration configuration = cfg.getConfiguration();

        Writer out=null;

        Map map = new HashMap();

        try {
            Template template = configuration.getTemplate("item.ftl");

            //读取spu信息
            TbGoods goods = goodsMapper.selectByPrimaryKey(goodsId);
            //读取spu 详细信息
            TbGoodsDesc goodsDesc = descMapper.selectByPrimaryKey(goodsId);
            //读取商品分类信息
            String tbItemCat1 = itemCatMapper.selectByPrimaryKey(goods.getCategory1Id()).getName();
            String tbItemCat2 = itemCatMapper.selectByPrimaryKey(goods.getCategory2Id()).getName();
            String tbItemCat3 = itemCatMapper.selectByPrimaryKey(goods.getCategory3Id()).getName();
            //读取sku信息，根据spu获取sku信息，讲信息再页面静态化，避免与数据库交互
            TbItemExample example = new TbItemExample();
            example.setOrderByClause("is_default");//排序
            TbItemExample.Criteria criteria = example.createCriteria();
            criteria.andGoodsIdEqualTo(goodsId);
            criteria.andStatusEqualTo("1");
            List<TbItem> itemList = itemMapper.selectByExample(example);


            map.put("cat1",tbItemCat1);

            map.put("cat2",tbItemCat2);

            map.put("cat3",tbItemCat3);

            map.put("goods",goods);

            map.put("goodsDesc",goodsDesc);

            map.put("itemList",itemList);

            //String path = ItemPageServiceImpl.class.getClassLoader().getResource("").getPath();
            out = new FileWriter(path+goodsId+".html");

            template.process(map,out);

            out.close();

            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }catch (TemplateException e) {
            e.printStackTrace();
        }

        return false;
    }
}
