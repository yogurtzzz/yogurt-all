package concurrent.snowflake;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yogurtzzz
 * @date 2020/4/17 16:47
 **/
public class Validator {
    public static void main(String[] args) throws IOException {
        File file = new File("E:\\test.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> stringList = new ArrayList<>(200);
        String s = "";
        String patternStr = "Y-\\d+-(\\d{4})";
        Pattern pattern = Pattern.compile(patternStr);
        int p = 1;
        int max = 0;
        while ((s = bufferedReader.readLine()) != null){
            System.out.println("处理到第" + (p++) + "行...");
            Matcher matcher = pattern.matcher(s);
            if (matcher.find()){
                String group = matcher.group();
                int num = Integer.parseInt(matcher.group(1));
                if (num > max){
                    max = num;
                }
                if (stringList.contains(group)){
                    throw new RuntimeException("repeated value : " + group);
                }else {
                    stringList.add(group);
                }
            }
        }
        System.out.println("clear");
        System.out.println("max : " + max);
    }
}
