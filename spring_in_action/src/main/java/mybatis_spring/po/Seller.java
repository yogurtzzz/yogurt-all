package mybatis_spring.po;

public class Seller {
	private Integer id;
	private Integer star_level;
	private String address;
	private String shop_name;
	private Integer open_year;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStar_level() {
		return star_level;
	}

	public void setStar_level(Integer star_level) {
		this.star_level = star_level;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getShop_name() {
		return shop_name;
	}

	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}

	public Integer getOpen_year() {
		return open_year;
	}

	public void setOpen_year(Integer open_year) {
		this.open_year = open_year;
	}
}
