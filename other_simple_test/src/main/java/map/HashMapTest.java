package map;

import org.junit.Test;

import java.util.HashMap;

public class HashMapTest {

	@Test
	public void test(){
		HashMap<Person,Integer> strMap = new HashMap<>(4);
		strMap.put(new Person("Huangbingyao",4),1);
		strMap.put(new Person("Yogurt",8),1);
		strMap.put(new Person("Amber",4),1);
		strMap.put(new Person("Fucker",7),1);
		strMap.put(new Person("Sucker",7),1);
		strMap.put(new Person("Cripple",8),1);
		strMap.put(new Person("Yell",7),1);
		strMap.put(new Person("Loud",4),1);
		strMap.put(new Person("Phoenix",3),1);
		strMap.put(new Person("Dragon",3),1);

	}
}
