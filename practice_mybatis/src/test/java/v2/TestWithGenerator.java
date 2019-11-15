package v2;

import mybatis.generator.dao.ProductOrderMapper;
import mybatis.generator.model.ProductOrder;
import mybatis.generator.model.ProductOrderExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestWithGenerator {
	private SqlSessionFactory factory;

	@Before
	public void init() throws IOException {
		String resource = "v2/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		factory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	@Test
	public void testCriteria(){
		SqlSession sqlSession = factory.openSession();
		ProductOrderMapper orderMapper = sqlSession.getMapper(ProductOrderMapper.class);
		ProductOrderExample example = new ProductOrderExample();
		ProductOrderExample.Criteria criteria = example.createCriteria();
		criteria.andBuyeridEqualTo(1);
		List<ProductOrder> orders = orderMapper.selectByExample(example);
		for (mybatis.generator.model.ProductOrder item : orders){
			System.out.println(item);
		}
	}
}
