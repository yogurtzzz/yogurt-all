package leetcode;

import java.math.BigInteger;

public class HowManyZero {
    public static void main(String[] args) {
        BigInteger multi = new BigInteger("1",10);
        BigInteger item;
        for (int i = 1; i < 101; i++){
            item = new BigInteger(""+i);
            multi = multi.multiply(item);
        }
        String res = multi.toString();
        int numOfZero = 0;
        System.out.println(res.length());
        for (int i = res.length() - 1; i > 0 ;i--){
            if (res.charAt(i) == '0'){
                numOfZero++;
            }else
                continue;
        }
        System.out.println(multi);
        System.out.println(numOfZero);
    }
}
