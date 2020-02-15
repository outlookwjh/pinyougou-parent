import com.pinyougou.pojo.TbItem;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-solr.xml")
public class SolrTest {

    @Autowired
    private SolrTemplate solrTemplate;

    /**
     * 增加或者修改，solr中有id主键时修改，没有时增加
     */
    @Test
    public void testAdd()
    {
        ArrayList<TbItem> tbItems = new ArrayList<>();


        for (int i = 0; i < 100; i++) {
            TbItem item=new TbItem();
            item.setId(1L+i);
            item.setBrand("华为");
            item.setCategory("手机");
            item.setGoodsId(1L+i);
            item.setSeller("华为"+i+"号专卖店");
            item.setTitle("华为Mate9");
            item.setPrice(new BigDecimal(2000));
            tbItems.add(item);
            /*solrTemplate.saveBean(item);
            solrTemplate.commit();*/
        }
        solrTemplate.saveBeans(tbItems);
        solrTemplate.commit();

    }

    /**
     * 按主键查询
     */
    @Test
    public void testQueryById()
    {
        System.out.println(solrTemplate.getById(102, TbItem.class).getBrand());
    }

    /**
     * 按主键删除
     */
    @Test
    public void testDelById()
    {
        solrTemplate.deleteById("101");
    }

    /**
     * 分页查询
     */
    @Test
    public void testQueryByPage()
    {
        Query query=new SimpleQuery("*:*");
        query.setOffset(10);
        query.setRows(20);
        ScoredPage<TbItem> tbItemScoredPage = solrTemplate.queryForPage(query, TbItem.class);

        List<TbItem> content = tbItemScoredPage.getContent();

        content.stream().forEach(e-> System.out.println(e.getTitle()+"----"+e.getBrand()));

        int totalPages = tbItemScoredPage.getTotalPages();
        System.out.println(totalPages);
    }

    /**
     * 条件查询
     */
    @Test
    public void testQueryByCondition()
    {
        Query query=new SimpleQuery("*:*");
        Criteria criteria = new Criteria("item_title").contains("2");
        criteria=criteria.and("item_title").contains("5");
        query.addCriteria(criteria);

        ScoredPage<TbItem> tbItemScoredPage = solrTemplate.queryForPage(query, TbItem.class);

        List<TbItem> content = tbItemScoredPage.getContent();

        content.stream().forEach(e-> System.out.println(e.getTitle()+"//"+e.getPrice()));
    }


    @Test
    public void delAll()
    {
        Query query= new SimpleQuery("*:*");


        solrTemplate.delete(query);
        solrTemplate.commit();
    }
}
