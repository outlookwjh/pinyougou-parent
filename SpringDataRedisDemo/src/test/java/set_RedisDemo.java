import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-redis.xml")
public class set_RedisDemo {

    @Autowired
    private RedisTemplate template;

    /**
     * 存如set
     */
    @Test
    public void setValue(){

        template.boundSetOps("setName").add("王建宏");
        template.boundSetOps("setName").add("刘丽丽");
        template.boundSetOps("setName").add("王伯阳");
    }

    /**
     * 取值 set(存取无序)
     */
    @Test
    public void getValue()
    {
        System.out.println(template.boundSetOps("setName").members());
    }

    @Test
    public void delSetValue()
    {
        template.boundSetOps("setName").remove("王建宏");
        System.out.println("删除后："+template.boundSetOps("setName").members());
    }

    @Test
    public void delSet()
    {
        template.delete("setName");
        System.out.println(template.boundSetOps("setName").members());
    }


}
