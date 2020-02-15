import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-redis.xml")
public class k_v_RedisDemo {

    @Autowired
    private RedisTemplate template;

    /**
     * 存如k-v键值对
     */
    @Test
    public void setValue(){

        template.boundValueOps("name").set("itcast");
    }

    /**
     * 取值 k-v
     */
    @Test
    public void getValue()
    {
        System.out.println(template.boundValueOps("name").get());
    }

    @Test
    public void delValue()
    {
        System.out.println("删除前："+template.boundValueOps("name").get());
        template.delete("name");
        System.out.println("删除后："+template.boundValueOps("name").get());
    }


}
