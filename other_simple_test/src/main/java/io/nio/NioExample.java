package io.nio;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

public class NioExample {

    @Test
    public void copyFile() throws IOException {
        FileInputStream fin = new FileInputStream("C:\\Users\\iGola\\Desktop\\reader.txt");
        FileChannel inChannel = fin.getChannel();
        FileOutputStream fout = new FileOutputStream("C:\\Users\\iGola\\Desktop\\copy.txt");
        FileChannel outChannel = fout.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (true){
            byteBuffer.clear();
            int r = inChannel.read(byteBuffer);
            if (r == -1)
                break;
            byteBuffer.flip();

            //clear() 方法重设缓冲区，使它可以接受读入的数据。 flip() 方法让缓冲区可以将新读入的数据写入另一个通道。
            outChannel.write(byteBuffer);
        }
    }

    @Test
    public void test1(){
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("Amber","Li");
        map.put("Bob","Wu");
        map.put("Christina","Zhao");
        map.put("David","Huang");
        for (Map.Entry<String,String> entry : map.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    @Test
    public void test2(){
        FormatStyle style = FormatStyle.LONG;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd E");
        System.out.println(formatter.format(LocalDate.now()));
    }

    @Test
    public void test3(){
        List<String> arr = new ArrayList<>(Arrays.asList("Segment","Date","Airline"));
        List<String> suppliers = Arrays.asList("myholidays,kiwi,snail".split(","));
        arr.addAll(suppliers);
        arr.forEach(v-> System.out.println(v));
    }

    @Test
    public void test4(){
        String excelFileName = "excel\\"
                + new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date())
                + ".xlsx";
        File excelFile = new File(excelFileName);
        if (!excelFile.getParentFile().exists()){
            excelFile.getParentFile().mkdirs();
        }
        try {
            excelFile.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void test5() throws IOException {
        String s = "{\n" +
                "  \"resultCode\" : 200,\n" +
                "  \"details\" : [ ]\n" +
                "}";
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(s);
        JsonNode jsonNode = node.get("details");
        JsonNode item1 = ((ArrayNode) jsonNode).get(0);
        System.out.println(item1);
    }
}
