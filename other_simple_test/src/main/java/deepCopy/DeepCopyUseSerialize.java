package deepCopy;

import java.io.*;

public class DeepCopyUseSerialize {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Item item = new Item();
		AnotherItem anotherItem = new AnotherItem();
		item.setItem(anotherItem);
		item.setName("yogurt");

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
		objectOutputStream.writeObject(item);

		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
		ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
		Object copy = objectInputStream.readObject();
		objectOutputStream.close();
		objectInputStream.close();
	}
}
