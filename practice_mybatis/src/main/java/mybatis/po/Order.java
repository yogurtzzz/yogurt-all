package mybatis.po;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Order {
	private Integer id;
	private String product;
	private Integer price;
	private Integer buyerId;
	private Date dealTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}

	public Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

	@Override
	public String toString() {
		return "Order{" +
				"id=" + id +
				", product='" + product + '\'' +
				", price=" + price +
				", buyerId=" + buyerId +
				", dealTime=" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dealTime) +
				'}';
	}
}
