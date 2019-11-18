package mybatis.po;


public class OrderExt extends Order {
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "OrderExt{" +
				"user=" + user + " " +
				super.toString() +
				'}';
	}
}
