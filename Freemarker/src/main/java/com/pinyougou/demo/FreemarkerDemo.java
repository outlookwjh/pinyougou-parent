package com.pinyougou.demo;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

public class FreemarkerDemo {

    public static void main(String[] args) throws IOException, TemplateException {

        Configuration cfg = new Configuration(Configuration.getVersion());

        cfg.setDirectoryForTemplateLoading(new File("D:\\workspace\\pinyougou-parent\\Freemarker\\src\\main\\resources"));

        cfg.setDefaultEncoding("UTF-8");

        Template template = cfg.getTemplate("template.ftl");

        Map map = new HashMap();

        map.put("name", "张三 ");
        map.put("message", "欢迎来到神奇的品优购世界！");
        map.put("success", false);

        List list = new ArrayList();
        Map goods1 = new HashMap();
        goods1.put("name","苹果");
        goods1.put("price",23);
        Map goods2 = new HashMap();
        goods2.put("name","香蕉");
        goods2.put("price",21);
        list.add(goods1);
        list.add(goods2);

        map.put("goodsList",list);

        map.put("today",new Date());

        map.put("numbertostring",123456);

        Writer out = new FileWriter(new File("D:\\workspace\\pinyougou-parent\\Freemarker\\src\\main\\resources\\demo.html"));

        template.process(map,out);

        out.close();

    }

}
