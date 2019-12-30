package io;

import java.io.File;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class TraverseFileFolder {
	//最后用了deque来解决路径的显示问题
	private static Deque<String> deque = new LinkedList<>();

	/**
	 *
	 * @param path
	 * @param level 递归深度
	 * @param ext
	 */
	public static void recursiveTranverse(String path,int level,String ext){
		File dir = new File(path);
		if (!dir.isDirectory()){
			System.out.println("当前路径不是目录");
			return;
		}
		//File为文件时，打印时的前缀（含缩进）
		String premark = level == 0 ? "" : String.format("%"+(level + 3)+"s","|- ");
		//File为目录时，用于缩进的空格
		String blank = level == 0 ? "" : String.format("%"+ level +"s","");
		boolean flag = false;
		File[] fs = dir.listFiles();

		for (File f : fs){
			if (f.isDirectory()){
				deque.addFirst(blank + "/"+f.getName() +"\n");
				recursiveTranverse(f.getAbsolutePath(),level + 1,ext);
			}else if (f.isFile()){
				String fileName = f.getName();
				if (ext == null || fileName.endsWith(ext)){
					deque.addFirst(premark + f.getName() + "\n");
					flag = true;
				}
			}
		}
		if (!flag)
			deque.removeFirst();
	}

	public static void recursiveTranverse(String path){
		recursiveTranverse(path,0,null);
		print();
	}
	public static void recursiveTranverse(String path,String ext){
		recursiveTranverse(path,0,ext);
		print();
	}
	public static void print(){
		String s;
		while ((s = deque.pollLast()) != null){
			System.out.print(s);
		}
	}

	public static void main(String[] args) {
//		String path = "C:\\WINDOWS";
//		String path2 = "G:\\我的文件";
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("输入要搜索的文件类型 : ");
//		String ext = scanner.nextLine();
//		recursiveTranverse(path2,ext);
		//findLargestAndSmallest(path);

		int sum = 0;
		int count = 1;
		int ave = 0;
		for (int i = 0; i < 100; i++){
			int item = (int)(Math.random()*100);
			sum += item;
			if (ave == 0){
				ave = item;
			}else {
				ave = (item + ave) / 2;
			}
			int totalAve = sum / count;
			System.out.println("第"+count+"次   " + ave + " : " + totalAve);
			count++;
		}
	}

}
