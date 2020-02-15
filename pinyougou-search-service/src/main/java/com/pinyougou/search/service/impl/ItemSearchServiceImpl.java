package com.pinyougou.search.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.pinyougou.pojo.TbItem;
import com.pinyougou.search.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.*;
import org.springframework.data.solr.core.query.result.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(timeout = 5000)
public class ItemSearchServiceImpl implements ItemSearchService {

    @Autowired
    private SolrTemplate solrTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Map<String, Object> search(Map search) {

        Map map = new HashMap();

        map.putAll(searchList(search));

        map.putAll(searchCatogryList(search));

        List<String> categoryList = (List) map.get("categoryList");
        if (categoryList.size()>0)
        {
            map.putAll(searchSpecList(categoryList.get(0)));
            map.putAll(searchBrandList(categoryList.get(0)));
        }



        return map;
    }

    /**
     * 查询规格
     * @return
     */
    private Map searchSpecList(String category)
    {
        Map map=new HashMap();

        Long id = (Long) redisTemplate.boundHashOps("itemCat").get(category);

        List brandList = (List) redisTemplate.boundHashOps("specList").get(id);

        map.put("specList", brandList);

        return map;
    }
    /**
     * 查询品牌
     * @return
     */
    private Map searchBrandList(String category)
    {
        Map map=new HashMap();

        Long id = (Long) redisTemplate.boundHashOps("itemCat").get(category);

        List brandList = (List) redisTemplate.boundHashOps("brandList").get(id);

        map.put("brandList", brandList);

        return map;
    }
    
    /**
     * 高亮查询列表信息
     * @param search
     * @return
     */
    private Map searchList(Map search)
    {
        Map<String,Object> map=new HashMap<>();

       /* Query query = new SimpleQuery("*:*");

        Criteria criteria = new Criteria("item_keywords").is(search.get("keywords"));

        query.addCriteria(criteria);

        ScoredPage<TbItem> tbItemScoredPage = solrTemplate.queryForPage(query, TbItem.class);

        List<TbItem> content = tbItemScoredPage.getContent();

        map.put("rows",content);*/

        //高亮
        HighlightQuery query=new SimpleHighlightQuery();

        Criteria criteria = new Criteria("item_keywords").is(search.get("keywords"));

        HighlightOptions highlightOptions = new HighlightOptions().addField("item_title");
        highlightOptions.setSimplePrefix("<em style='color:red'>");
        highlightOptions.setSimplePostfix("</em>");
        query.setHighlightOptions(highlightOptions);

        query.addCriteria(criteria);
        HighlightPage<TbItem> page = solrTemplate.queryForHighlightPage(query, TbItem.class);

        List<HighlightEntry<TbItem>> highlighted = page.getHighlighted();

        for (HighlightEntry<TbItem> highlightEntry : highlighted) {

            TbItem item = highlightEntry.getEntity();

            if(highlightEntry.getHighlights().size()>0 && highlightEntry.getHighlights().get(0).getSnipplets().size()>0){
                item.setTitle(highlightEntry.getHighlights().get(0).getSnipplets().get(0));//设置高亮的结果
            }
        }


        map.put("rows",page.getContent());

        return map;

    }

    //查询分组信息
    public Map searchCatogryList(Map searchMap)
    {
        Map map = new HashMap();

        Query query = new SimpleQuery();

        Criteria criterial= new Criteria("item_keywords").is(searchMap.get("keywords"));

        query.addCriteria(criterial);
        GroupOptions groupOptions=new GroupOptions();
        groupOptions.addGroupByField("item_category");

        query.setGroupOptions(groupOptions);

        GroupPage<TbItem> groupPage = solrTemplate.queryForGroupPage(query, TbItem.class);

        GroupResult<TbItem> item_category = groupPage.getGroupResult("item_category");

        Page<GroupEntry<TbItem>> groupEntries = item_category.getGroupEntries();

        List<GroupEntry<TbItem>> content = groupEntries.getContent();
        List<String> list=new ArrayList();

        content.stream().forEach(e->list.add(e.getGroupValue()));

        map.put("categoryList", list);
        return map;
    }

}
