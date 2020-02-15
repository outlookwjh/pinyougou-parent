package com.pinyougou.search.controller;

import com.pinyougou.search.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/itemsearch")
public class ItemSearchController {

    @Autowired
    private ItemSearchService searchService;

    @RequestMapping("/search")
    public Map<String,Object> itemSearch(@RequestBody Map searchMap)
    {
        Map<String, Object> search = searchService.search(searchMap);
        return search;
    }

}
