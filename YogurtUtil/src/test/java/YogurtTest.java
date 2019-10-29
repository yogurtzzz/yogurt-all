import math.PrimeUtil;
import org.junit.Assert;
import org.junit.Test;

public class YogurtTest {

    @Test
    public void primeUtilTest(){
        int[] empty = {};
        Assert.assertArrayEquals(empty, PrimeUtil.getPrimes(-1));
        Assert.assertArrayEquals(empty, PrimeUtil.getPrimes(0));
        Assert.assertArrayEquals(empty, PrimeUtil.getPrimes(1));
        Assert.assertArrayEquals(empty, PrimeUtil.getPrimes(2));
    }

    @Test
    public void test2(){
        Assert.assertArrayEquals(new int[]{2,3,5},PrimeUtil.getPrimes(6));
        Assert.assertArrayEquals(new int[]{2,3,5,7},PrimeUtil.getPrimes(8));
        Assert.assertArrayEquals(new int[]{2},PrimeUtil.getPrimes(3));
        Assert.assertArrayEquals(new int[]{2,3,5,7,11,13},PrimeUtil.getPrimes(14));
        Assert.assertArrayEquals(new int[]{2,3,5,7,11,13,17,19,23},PrimeUtil.getPrimes(25));
        Assert.assertArrayEquals(new int[]{2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,
                67,71,73,79,83,89,97,101,103,107,109,113,127,131,137,139,149,151,157,163,167,
                173,179,181,191,193,197,199,211, 223,227,229,233,239,241,251,257,263,269,271,
                277,281,283,293},PrimeUtil.getPrimes(300));
        for (int i : PrimeUtil.getPrimes(500)){
            System.out.print(i + " ");
        }
    }
}
