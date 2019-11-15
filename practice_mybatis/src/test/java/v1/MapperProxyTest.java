package v1;

import com.github.pagehelper.PageHelper;
import mybatis.dao.UserMapper;
import mybatis.dto.QueryVo;
import mybatis.generator.dao.ProductOrderMapper;
import mybatis.generator.model.ProductOrderExample;
import mybatis.po.BuyerExt;
import mybatis.po.Order;
import mybatis.po.OrderExt;
import mybatis.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MapperProxyTest {
	private SqlSessionFactory factory;

	@Before
	public void init() throws IOException {
		String resource = "v1/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		factory = new SqlSessionFactoryBuilder().build(inputStream);
	}

	@Test
	public void testMapper(){
		SqlSession sqlSession = factory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		System.out.println(userMapper.findUserById(1));
	}

	/**
	 * 多个查询条件
	 * 将查询条件封装成vo
	 */
	@Test
	public void testMultiCondition(){
		SqlSession sqlSession = factory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		QueryVo queryVo = new QueryVo();
		queryVo.setName("am");
		queryVo.setGender(1);
		List<User> list = userMapper.findByMultiCondition(queryVo);
		for (User item : list){
			System.out.println(item);
		}
	}

	/**
	 * 使用mybatis注解
	 * 将查询条件封装成map
	 */
	@Test
	public void testMultiCondition2(){
		SqlSession sqlSession = factory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		List<User> list = userMapper.findByMap("am",1);
		for (User item : list){
			System.out.println(item);
		}
	}

	/**
	 * 一对一关联映射
	 *
	 */
	@Test
	public void testOne2One(){
		SqlSession sqlSession = factory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		OrderExt orderExt = userMapper.findOrderAndBuyer(1);
		System.out.println(orderExt);
	}

	/**
	 * 一对多关联映射
	 */
	@Test
	public void testOne2Many(){
		SqlSession sqlSession = factory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		BuyerExt buyerExt = userMapper.findBuyerAndOrder(1);
		System.out.println(buyerExt.toString());
		for (Order item : buyerExt.getOrders()){
			//这里查询出来的  订单创建时间，和MySQL中存储的不一致
			//MySQL存储的是 15:31:57
			//这里查询出来的是 23:31:57
			//相差了8小时
			//这是因为Mybatis 连接 MySQL时指定的时区
			//与MySQL使用的时区不一致导致的
			//将Mybatis连接数据库的url中的时区设置为Asia/Shanghai 即可
			System.out.println(item);
		}
	}

	/**
	 * 延迟加载   or  嵌套查询
	 * 只有在使用association 和 collection ，并且设置了子查询语句时，才能生效
	 * 为什么要使用延迟加载？
	 *  为了提高数据库的访问效率
	 *  在涉及到多表关联查询时，会非常影响查询效率，所以引入延迟加载，按需要将信息进行加载，从而优化了性能，提高了效率
	 *
	 *  然而使用 深度延迟加载可以提高性能，但是若延迟加载的主信息太多，则会产生 1+ N 问题，会降低性能
	 *  例子：
	 *      给定查询条件，查询user ， 以及与该user 关联的多个 订单信息
	 *      根据查询条件，执行一次对主信息的查询，查询到 N 个 user
	 *      延迟加载时，对每个user ，都要做一次查询，则一共会执行 N 次查询
	 *      查询主信息时，执行了1次查询，获得N条记录
	 *      查询从信息时，对N条主信息，做关联查询，则会执行N 次查询
	 *
	 */
	@Test
	public void testLazyLoad(){
		//每次通过改变配置
		//来改变延迟加载策略
		//注意查询的结果会被保存到一级缓存（默认开启一级缓存）
		//保存到一级缓存时，可能需要调用对象的equals或hashCode来判断对象是否存在，此时会触发延迟加载
		//测试时可以在配置文件里配置  <setting name="lazyLoadTriggerMethods" value=""/>  来将触发延迟加载的方法设为空
		//mybatis延迟加载默认的触发方法有  equals hashCode clone toString
		SqlSession sqlSession = factory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		BuyerExt userExt = userMapper.findUserAndOrdersLazy(1);
		System.out.println("AggressiveLazyLoad BEGIN");
		//侵入式延迟加载将在下一行访问主信息时，执行子查询
		System.out.println(userExt.getName() + " " + userExt.getAddress());

		System.out.println("AggressiveLazyLoad END");
		//深度延迟加载将在下一行访问从信息时，执行子查询

		System.out.println("DeepLazyLoad BEGIN");
		System.out.println(userExt.getOrders().size());
		System.out.println("end");
	}

	/**
	 * 动态SQL
	 */
	/**
	 * 分页插件
	 */
	@Test
	public void testDynamicSql(){
		SqlSession sqlSession = factory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		QueryVo queryVo = new QueryVo();
		queryVo.setName("am");
		queryVo.setAddress("");
		//PageHelper.startPage(2,2);
		//是通过mybatis的plugin接口
		//加入一个拦截器
		//会查询SELECT COUNT
		//会加入 LIMIT ? , ?
		List<User> selective = userMapper.findSelective(queryVo);
		for (User item : selective){
			System.out.println(item);
		}
	}


	@Test
	public void test4Each(){
		SqlSession sqlSession = factory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		QueryVo queryVo = new QueryVo();
		List<Integer> ids = Arrays.stream(new int[]{1,2,4,6}).boxed().collect(Collectors.toList());
		queryVo.setIds(ids);
		List<User> lists = userMapper.find4Each(queryVo);
		for (User item : lists){
			System.out.println(item);
		}
	}

	@Test
	public void testLevel1Cache(){
		SqlSession sqlSession = factory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User userById = userMapper.findUserById(1);
		//提交事务会刷新一级缓存
		//sqlSession.commit();
		System.out.println(userById);
		User user2 = userMapper.findUserById(1);
		System.out.println(user2);
	}

	@Test
	public void testLevel2Cache(){
		SqlSession sqlSession = factory.openSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		User userById = userMapper.findUserById(1);
		System.out.println(userById);
		//注意需要提交后才会将结果保存到二级缓存
		//若不提交，则还是会查询2次数据库
		sqlSession.close();

		SqlSession sqlSession2 = factory.openSession();
		userMapper = sqlSession2.getMapper(UserMapper.class);
		User user2 = userMapper.findUserById(1);
		System.out.println(user2);
	}
}
