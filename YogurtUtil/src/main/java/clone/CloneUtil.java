package clone;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import java.io.*;

public class CloneUtil {

	/**
	 * 利用序列化进行深拷贝
	 */
	public static<T extends Serializable> T deepCopy(T src) throws IOException, ClassNotFoundException {
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream objectOut = new ObjectOutputStream(byteOut);

		objectOut.writeObject(src);

		//将原对象输出为字节流
		byte[] bytes = byteOut.toByteArray();

		ByteArrayInputStream byteInput = new ByteArrayInputStream(bytes);
		ObjectInputStream objectInput = new ObjectInputStream(byteInput);
		T copy = (T )objectInput.readObject();

		return copy;
	}
}
