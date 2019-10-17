package leetcode;

import java.util.HashMap;
import java.util.Map;

public class LongestPalindrome {
    public String longestPalindromeStr(String s){
        if (s.length() <= 1)
            return s;
        Map<Integer,String> map = new HashMap<>();
        int maxLen = 0;
        char[] chars = s.toCharArray();
        int i = 0;
        int j = chars.length - 1;
        boolean moveI = false;
        while(true){
            if (j == chars.length - 1 && j - i + 1 <= maxLen){
                break;
            }
            if (j - i + 1 <= maxLen){
                moveI = true;
            }
            if (i == j){
                moveI = true;
            }
            if (i != j && chars[i] == chars[j]){
                int left = i,right = j;
                while (true){
                    if (left == right + 1 || left == right - 2){
                        if (j - i + 1 > maxLen ){
                            moveI = true;
                            maxLen = j - i + 1;
                            map.put(maxLen,s.substring(i,j+1));
                        }
                        break;
                    }
                    left++;
                    right--;
                    if (chars[left] == chars[right]){
                        //继续判断相等
                        continue;
                    }else{
                        //有不相等的，直接break
                        break;
                    }
                }
                if (maxLen == s.length())
                    break;
            }
            if (moveI){
                i++;
                j = s.length() - 1;
                moveI = false;
            }else{
                j--;
            }
        }
        if (map.size() == 0)
            return s.substring(0,1);
        else
            return map.get(maxLen);
    }

    public static void main(String[] args) {
        LongestPalindrome longestPalindrome = new LongestPalindrome();
        String str = longestPalindrome.longestPalindromeStr("cbbd");
        System.out.println(str);
    }
}
