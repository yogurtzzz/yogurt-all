package utils;

public class MyPair<V1,V2> {
	private V1 left;
	private V2 right;

	public MyPair(){}
	public MyPair(V1 left, V2 right) {
		this.left = left;
		this.right = right;
	}

	public V1 getLeft() {
		return left;
	}

	public void setLeft(V1 left) {
		this.left = left;
	}

	public V2 getRight() {
		return right;
	}

	public void setRight(V2 right) {
		this.right = right;
	}
}
