import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-redis.xml")
public class hash_RedisDemo {

    @Autowired
    private RedisTemplate template;

    /**
     * 存如k-v键值对
     */
    @Test
    public void setValue(){

        template.boundHashOps("bigKey").put("smallKey1","王建宏");
        template.boundHashOps("bigKey").put("smallKey2","刘丽丽");
        template.boundHashOps("bigKey").put("smallKey3","王伯阳");

    }

    /**
     * 取值 k-v
     */
    @Test
    public void getValue()
    {
        System.out.println(template.boundHashOps("bigKey").get("smallKey1"));

        System.out.println(template.boundHashOps("bigKey").keys());

        System.out.println(template.boundHashOps("bigKey").values());


    }

    /**
     * 移除小key
     */
    @Test
    public void delValue()
    {
        template.boundHashOps("bigKey").delete("smallkey1");
    }


}
