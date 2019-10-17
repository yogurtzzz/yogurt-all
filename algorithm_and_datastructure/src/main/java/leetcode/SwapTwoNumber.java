package leetcode;

import org.junit.Test;

public class SwapTwoNumber {
    public void swap(int a,int b){
        //异或，不同的位得到的结果为1，相同位得到0
        //所以a ^ b 得到的结果temp 保存了a和b不同的位的信息
        //temp中为1的位，表示a和b在这一位上是不同的
        //和1做异或，相当于取反 ， 0 ^ 1 = 1  1 ^ 1 = 0
        //和0做异或，保持不变， 0 ^ 0 = 0， 1 ^ 0 = 1
        //所以a ^ temp 将a中与b不同的位，取了反，得到的就是b
        //同理 b ^ temp，将得到a
        a = a ^ b; //运算完后 a = temp
        b = a ^ b;  //运算完后 b = 原来的a
        a = a ^ b;  //运算完后 a = temp ^ a = 原来的b
        //或者简写为如下形式
        a ^= b ^= a ^= b;
        //但是需要注意，由于java是按值传递，所以这个函数并不能达到交换a和b的效果
    }


    @Test
    public void test(){
        int a = 3,b = 9;
        swap(a,b);
        System.out.println("a = " + a +", b = " + b);
    }
}
