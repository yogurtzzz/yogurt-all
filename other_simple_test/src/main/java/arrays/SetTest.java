package arrays;

import org.junit.Test;

import java.util.EnumSet;

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
}
