package utils;

import java.util.Arrays;

public class YogurtArrays {
    public static void showArrays(int[] arr){
        for (int i : arr){
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void arrayCopy(int[] src,int srcPos,int[] dest,int destPos,int length){
        checkIndex(src,srcPos);
        checkIndex(dest,destPos);
        int[] srcCopy = Arrays.copyOf(src,src.length);
        if (length > src.length - srcPos || length > dest.length - destPos)
            throw new IllegalArgumentException("指定的长度超出数组最大下标");
        for (int i = 0; i < length; i++){
            dest[destPos++] =srcCopy[srcPos++];
        }

    }

    public static void checkIndex(int[] arr,int i){
        if (i < 0 || i >= arr.length)
            throw new ArrayIndexOutOfBoundsException("i = " + i + ",but length = " + arr.length);
    }
}
