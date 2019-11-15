package deepCopy;

import java.io.Serializable;

public class Item implements Serializable {
	private String name;
	private AnotherItem item;

	public Item(String name, AnotherItem item) {
		this.name = name;
		this.item = item;
	}

	public Item() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AnotherItem getItem() {
		return item;
	}

	public void setItem(AnotherItem item) {
		this.item = item;
	}
}
