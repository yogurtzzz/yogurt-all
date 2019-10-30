package base;

/**
 * 继承该类，以获取 swap 操作
 */
public class SwapCapable {
    /**
     * 最有效率的交换方法，无需额外空间
     * 需要注意当i = j时，做异或操作会使得数字变为0，要作为特殊情况单独考虑
     */
    protected void swap(int[] arr,int i ,int j){
        if (i == j)
            return;
        //该方法有个前提
        //i和j不能相等，否则将出错
        int size = arr.length;
        if (i >= size || j >= size || i < 0 || j < 0)
            throw new IndexOutOfBoundsException("i = " + i + ",j = " + j + ",but size = " +size);
        //most efficient method for swap two numbers
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
