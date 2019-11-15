package v1;

import mybatis.dao.UserMapper;
import mybatis.po.BuyerExt;
import mybatis.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleTest {

    private SqlSessionFactory factory;

    @Before
    public void init() throws IOException {
        String resource = "v1/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        factory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testFindAll(){
        SqlSession sqlSession = factory.openSession();
        //入参 findAll是 mapper文件中的 SQL标签id ，当存在同名id时，在前面加上namespace 以作区分
        //如 test.findAll
        List<User> list = sqlSession.selectList("findAll");
        for (User user : list){
            System.out.println(user);
        }
    }
    @Test
    public void testFindByName(){
        SqlSession sqlSession = factory.openSession();
        User user = sqlSession.selectOne("findByName", "yogurt");
        System.out.println(user);
    }

    @Test
    public void testFuzzySearch(){
        SqlSession sqlSession = factory.openSession();
        List<User> users = sqlSession.selectList("fuzzyFind", "am");
        for (User user : users){
            System.out.println(user);
        }
    }

    @Test
    public void testAddUser(){
        SqlSession sqlSession = factory.openSession();
        User newUser = new User();
        newUser.setName("Unicorn");
        newUser.setAge(27);
        newUser.setGender(1);
        newUser.setAddress("Guangzhou");
        sqlSession.insert("addUser",newUser);
        sqlSession.commit();
        System.out.println(newUser.getId());
    }

    @Test
    public void testDelete(){
        SqlSession sqlSession = factory.openSession();
        sqlSession.delete("deleteById",8);
        sqlSession.commit();
    }

    @Test
    public void testUpdate(){
        SqlSession sqlSession = factory.openSession();
        User user = new User();
        user.setId(7);
        user.setName("Gryffindor");
        user.setAge(23);
        user.setGender(0);
        user.setAddress("Hogwarts");
        sqlSession.update("updateById",user);
        sqlSession.commit();
    }

    @Test
    public void testFindById(){
        SqlSession sqlSession = factory.openSession();
        User u1 = sqlSession.selectOne("findUserById", 1);
        System.out.println(u1);

        User u2 = sqlSession.selectOne("findUserById", 1);
        System.out.println(u2);
    }

    @Test
    public void testDynamicAndSStatic(){
        SqlSession sqlSession = factory.openSession();
        Map<String,Object> map = new HashMap<>();
        map.put("name","am");
        map.put("id",3);
        User u1 = sqlSession.selectOne("testDynamicAndStatic", map);
        System.out.println(u1);
    }

    @Test
    public void testLazyLoad(){
        SqlSession sqlSession = factory.openSession();

        BuyerExt userExt = sqlSession.selectOne("findUserAndOrdersLazy",1);
        System.out.println("AggressiveLazyLoad BEGIN");
        //侵入式延迟加载将在下一行访问主信息时，执行子查询
        System.out.println(userExt.getName() + " " + userExt.getAddress());

        System.out.println("AggressiveLazyLoad END");
        //深度延迟加载将在下一行访问从信息时，执行子查询

        System.out.println("DeepLazyLoad BEGIN");
        System.out.println(userExt.getOrders().size());
        System.out.println("end");
    }

}
