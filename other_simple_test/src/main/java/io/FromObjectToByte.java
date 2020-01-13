package io;

import java.io.*;

public class FromObjectToByte {
    static class Sample implements Serializable {
        private String a;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }
    }

    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        Sample sample = new Sample();
        sample.setA("xixi");
        objectOutputStream.writeObject(sample);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        String s = bytesToHex(bytes);
        System.out.println(s);
    }

    public static String bytesToHex(byte[] bs){
        StringBuilder sb = new StringBuilder();
        for (byte b : bs){
            String s = Integer.toHexString(b & 0xFF);
            if (s.length() < 2)
                sb.append(0);
            sb.append(s);
        }
        sb.append("\n");
        return sb.toString();
    }
}
