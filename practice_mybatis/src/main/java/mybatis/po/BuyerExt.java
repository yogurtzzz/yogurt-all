package mybatis.po;

import java.util.List;

public class BuyerExt extends User {
	List<Order> orders;

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}
