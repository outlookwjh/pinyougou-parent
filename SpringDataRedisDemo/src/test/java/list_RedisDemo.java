import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-redis.xml")
public class list_RedisDemo {

    @Autowired
    private RedisTemplate template;

    /**
     * 右压栈：先进先出，后进后出
     */
    @Test
    public void RightpushValue(){

        template.boundListOps("rightListName").rightPush("王建宏");
        template.boundListOps("rightListName").rightPush("刘丽丽");
        template.boundListOps("rightListName").rightPush("王伯阳");

    }

    /**
     * 取值range 右压栈：先进先出，后进后出
     */
    @Test
    public void getValue()
    {
        System.out.println(template.boundListOps("rightListName").range(0,10));
    }

    @Test
    public void leftpushValue(){

        template.boundListOps("leftListName1").leftPush("王建宏");
        template.boundListOps("leftListName1").leftPush("刘丽丽");
        template.boundListOps("leftListName1").leftPush("王伯阳");

    }

    /**
     * 取值range 左压栈：先进后出，后进先出
     */
    @Test
    public void getleftValue()
    {
        System.out.println(template.boundListOps("leftListName1").range(0,3));
    }


    /**
     * 按照索引查询
     */
    @Test
    public void getByIndex()
    {
        System.out.println(template.boundListOps("rightListName").index(0));
        System.out.println(template.boundListOps("leftListName1").index(0));
    }

    /**
     * 1表示个数，删除一个值为王建宏的元素
     */
    @Test
    public void del()
    {
        template.boundListOps("rightListName").remove(1,"王建宏");
    }


}
