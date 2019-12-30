import mybatis_spring.dao.OrderDao;
import mybatis_spring.dao.SellerDao;
import mybatis_spring.dao.UserDao;
import mybatis_spring.po.Order;
import mybatis_spring.po.Seller;
import mybatis_spring.po.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:mybatis/spring-mybatis-config.xml")
public class Mybatis_SpringTest {

	@Autowired
	UserDao userDao;

	@Autowired
	OrderDao orderDao;

	@Autowired
	SellerDao sellerDao;

	//编程式事务
	@Test
	public void test(){
		User user = new User();
		user.setName("mybatis-spring");
		user.setAge(1);
		user.setGender(1);
		user.setAddress("spring.io");
		userDao.insertOne(user);
	}

	//声明式事务
	@Test(expected = RuntimeException.class)
	public void test2(){
		Order order = new Order();
		order.setPrice(2000);
		order.setBuyerId(2);
		order.setProduct("GTX3080Ti");
		order.setStatus(1);
		orderDao.addOne(order);
	}

	//注解式事务
	@Test
	public void test3(){
		ApplicationContext context = new ClassPathXmlApplicationContext("");
		Seller seller = new Seller();
		seller.setShop_name("大黄的小店");
		seller.setStar_level(3);
		seller.setAddress("广州");
		seller.setOpen_year(1);
		sellerDao.addOne(seller);
	}
}
