package jedis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisTest {

	private JedisPool jedisPool;

	private static final int maxTotal = 100;
	private static final int maxIdle = 50;
	private static final int minIdle = 10;

	@Before
	public void init(){
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(maxTotal);
		config.setMaxIdle(maxIdle);
		config.setMinIdle(minIdle);

		jedisPool = new JedisPool(config,"192.168.183.128",6379,120);
	}

	@Test
	public void test1() throws InterruptedException {
		Jedis jedis = jedisPool.getResource();
		jedis.setnx("user:1","yogurt");
		jedis.setnx("user:2","amber");
		Thread.sleep(100);

		System.out.println(jedis.get("user:1"));
		System.out.println(jedis.get("user:2"));
	}
}
