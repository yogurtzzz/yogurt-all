package utils;


public class ArrayUtil {

    static class IllegalOrderException extends RuntimeException{
        public IllegalOrderException(){
            super("数组不是有序");
        }
    }
    public static void printArray(int[] arr){
        for (int i : arr){
            System.out.print(i + " ");
        }
        System.out.println();
    }
    public static void printArray(Object[] arr){
        for (Object a : arr)
            System.out.println(a);
    }

    public static void checkIndex(int[] arr,int i){
        if (i < 0 || i >= arr.length)
            throw new ArrayIndexOutOfBoundsException("i = " + i + ",but length = " + arr.length);
    }

    public static int[] createRandomArray(int from, int to, int size){
        if (from >= to)
            throw new IllegalArgumentException("起始值必须小于结束值！");
        if (size < 0)
            throw new IllegalArgumentException("数组大小必须大于等于0");
        if (size == 0)
            return new int[]{};
        int gap = to - from;
        int[] arr = new int[size];
        for (int i = 0 ; i < size; i++){
            int item = (int)(Math.random() * gap) + from;
            arr[i] = item;
        }
        return arr;
    }

    public static void assertSorted(int[] arr){
        if (arr == null || arr.length == 1)
            return ;
        Boolean ascend = null;
        for (int i = 0 ; i < arr.length - 1 ; i++){
            if (ascend != null){
                if (ascend){
                    if (arr[i] > arr[i + 1])
                        throw new IllegalOrderException();
                }else{
                    if (arr[i] < arr[i + 1])
                        throw new IllegalOrderException();
                }
            }else{
                if (arr[i] < arr[i + 1])
                    ascend = true;
                else if (arr[i] > arr[i + 1])
                    ascend = false;
            }
        }
    }

}
