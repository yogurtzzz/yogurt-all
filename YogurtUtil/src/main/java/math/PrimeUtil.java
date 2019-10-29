package math;

import java.util.Arrays;

public class PrimeUtil {
    //返回小于max的所有素数集合
    public static int[] getPrimes(int max){
        //先考虑边界条件
        // 提前返回
        if (max <= 2)
            return new int[]{};

        int[] primes = new int[max];
        int count = 0;
        for (int i = 2; i < max; i++){
            if (isPrime(i))
                primes[count++] = i;
        }
        primes = Arrays.copyOf(primes,count);
        return primes;
    }

    //数n是否是素数
    public static boolean isPrime(int n){
        if (n < 2)
            return false;
        if (n == 2)
            return true;
        // 优化：
        // 只需要判断前面一半
        // 超过2后，可以只判断奇因子
        for (int i = 2; i < n/2 + 1; ){
            if (n % i == 0)
                return false;
            if (i == 2)
                i++;
            else
                i += 2;
        }
        return true;
    }

    public static int getLargestPrimeLessThan(int max){
        if (max <= 2){
            throw new IllegalArgumentException("小于2的素数不存在");
        }
        for (int i = max - 1; i >= 2; i--){
            if (isPrime(i))
                return i;
        }
        //理论上不会走到这一步
        return -1;
    }
}
