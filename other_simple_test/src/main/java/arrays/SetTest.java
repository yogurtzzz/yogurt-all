package arrays;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class SetTest {
    public enum COLOR{
        BLUE("蓝色"),RED("红色"),YELLOW("黄色");
        private String desc;
        COLOR(String d){
            desc = d;
        }

        @Override
        public String toString() {
            return this.desc;
        }
    }
    @Test
    public void test(){
        EnumSet<COLOR> enumSet = EnumSet.noneOf(COLOR.class);
        COLOR blue = COLOR.BLUE;
        enumSet.add(blue);
        for (COLOR color : enumSet){
            System.out.println(color);
        }
    }

    private static int x = 100;
    public static void main(String[] args) {
        String a = "duowan";
        System.out.println(compositionCount(a));
    }


    public static boolean isAndEqualExist(int[] a){
        //一个数组，判断其是否满足如下条件
            // 数组的某个元素，等于其余所有元素做与操作的结果
        //因为与操作的结果肯定是比任意的操作数小的，那么如果存在，剩下的那个数一定是最小的数
        if (a == null || a.length <= 1)
            return false;
        //兼容长度为2的数组
        if (a.length == 2)
            return a[0] == a[1];
        //找出最小值
        int min = a[0];
        int minPos = 0;
        for (int i = 1; i < a.length; i++){
            if (a[i] < min){
                min = a[i];
                minPos = i;
            }
        }
        int result = 0;
        boolean first = true;
        for (int i = 0; i < a.length;i++){
            if (i == minPos)
                continue;
            if (first){
                result = a[i];
                first = false;
            }
            else
                result = result & a[i];
        }
        if (result == min)
            return true;
        else
            return false;
    }
    public static int compositionCount(String str){
        if (str == null || str.length() < 5)
            return 0;
        Map<Character,List<Integer>> charAndPositions = new HashMap<>();
        Character[] characters = str.chars().mapToObj(c->(char)c).toArray(Character[]::new);
        for (int i = 0; i < characters.length; i++){
            if (i == 0 || i == characters.length - 1){
                //首位字符不计算在内
                continue;
            }
            List<Integer> positions = charAndPositions.get(characters[i]);
            if (positions == null){
                positions = new ArrayList<>();
                positions.add(i);
                charAndPositions.put(characters[i],positions);
            }else{
                positions.add(i);
            }
        }

        int sum = 0;

        for (Map.Entry<Character,List<Integer>> entry : charAndPositions.entrySet()){
            List<Integer> positions = entry.getValue();
            if (positions.size() <= 1){
                continue;
            }
            for (int i = 0;i < positions.size(); i++){
                if (i + 1 < positions.size()){
                    if (positions.get(i + 1) > positions.get(i) + 1){
                        sum += positions.size() - i - 1;
                    }else {
                        sum += positions.size() - i - 2;
                    }
                }
            }
        }
        return sum;
    }
}
