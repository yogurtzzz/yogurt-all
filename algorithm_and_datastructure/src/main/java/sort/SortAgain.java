//package sort;
//
//public class SortAgain {
//
//	public static void bubbleSort(int[] arr){
//		for (int i = arr.length - 1; i > 0; i--){
//			for (int j = 0; j < i; j++){
//				if (arr[j] > arr[j + 1]){
//					int temp = arr[j];
//					arr[j] = arr[j + 1];
//					arr[j + 1] = temp;
//				}
//			}
//		}
//	}
//
//	public static void insertSort(int[] arr){
//		if (arr.length <= 1)
//			return;
//		int sortedSize = 1;
//		int pos = 1;
//		while (sortedSize < arr.length){
//			int currentPos = pos;
//			for (int i = sortedSize - 1;i >= 0; i--){
//				if (arr[i] > arr[currentPos]){
//					int temp = arr[i];
//					arr[i] = arr[currentPos];
//					arr[currentPos] = temp;
//					currentPos = i;
//				}else{
//					break;
//				}
//			}
//			sortedSize++;
//			pos++;
//		}
//	}
//
//	public static void shellSort(int[] arr){
//		for (int gap = arr.length / 2; gap >= 1; gap /= 2){
//			for (int i = 0; i < gap; i++){
//				int sortedSize = 1;
//				int pos = i + gap;
//				while (pos < arr.length){
//					int currentPos = pos;
//					for (int j = (sortedSize - 1) * gap + i; j >= 0; j -= gap){
//						if (arr[j] > arr[currentPos]){
//							int temp = arr[j];
//							arr[j] = arr[currentPos];
//							arr[currentPos] = temp;
//							currentPos = j;
//						}else {
//							break;
//						}
//					}
//					sortedSize++;
//					pos += gap;
//				}
//			}
//		}
//	}
//
//	public static void quickSort(int[] arr){
//		quickSort(arr,0,arr.length - 1);
//	}
//	private static void quickSort(int[] arr,int left,int right){
//		if (left >= right)
//			return;
//		int pivot = arr[left];
//		int pivotPos = left;
//		int posL = left + 1;
//		int posR = right;
//		boolean moveRight = true;
//		while (posL <= posR){
//			if (moveRight){
//				if (arr[posR] < pivot){
//					arr[pivotPos] = arr[posR];
//					arr[posR] = pivot;
//					pivotPos = posR;
//					moveRight = false;
//				}
//				posR--;
//			}else{
//				if (arr[posL] > pivot){
//					arr[pivotPos] = arr[posL];
//					arr[posL] = pivot;
//					pivotPos = posL;
//					moveRight = true;
//				}
//				posL++;
//			}
//		}
//		quickSort(arr,left,pivotPos - 1);
//		quickSort(arr,pivotPos + 1,right);
//	}
//
//
//	public static void mergeSort(int[] arr){
//		int[] newArr = new int[arr.length];
//		mergeSort(arr,0,arr.length - 1,newArr);
//	}
//
//	public static void mergeSort(int[] arr,int left,int right,int[] newArr){
//		if (left >= right)
//			return ;
//		int mid = (left + right) / 2;
//		mergeSort(arr,left,mid,newArr);
//		mergeSort(arr,mid + 1,right,newArr);
//		//merge之前进行数组的拷贝
//		System.arraycopy(arr,left,newArr,left,right - left + 1);
//		//下面开始merge
//		int posL = left,posR = mid + 1;
//		int posNew = left;
//		while (posL <= mid && posR <= right){
//			if (newArr[posL] <= newArr[posR]){
//				arr[posNew++] = newArr[posL++];
//			}else{
//				arr[posNew++] = newArr[posR++];
//			}
//		}
//
//		while (posL <= mid){
//			arr[posNew++] = newArr[posL++];
//		}
//		while (posR <= right){
//			arr[posNew++] = newArr[posR++];
//		}
//	}
//	public static void main(String[] args) {
//		int[] arr = {-2,3,5,25,1,23,-17,9,15,12};
//		mergeSort(arr);
//		show(arr);
//	}
//
//	public static void show(int[] arr){
//		for (int i : arr){
//			System.out.print(i+" ");
//		}
//		System.out.println();
//	}
//}
