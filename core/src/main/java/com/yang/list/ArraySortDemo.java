package com.yang.list;

/**
 * @Description: 数组排序
 * @ClassName: ArraySortDemo
 * @author tonasun
 * @date 2018年6月4日
 */
public class ArraySortDemo {
	public static void main(String[] args) {
		int[] arrays1 = new int[10000];
		int[] arrays2 = new int[10000];
		int[] arrays3 = new int[10000];
		for (int i = 0, j = 99999; i < 100; i++, j--) {
			arrays1[i] = j;
			arrays2[i] = j;
			arrays3[i] = j;
		}
		long nowTime = System.currentTimeMillis();
		bubbleSort(arrays1);
		System.out.println("bubbleSort---------" + (System.currentTimeMillis() - nowTime));
		long nowTime2 = System.currentTimeMillis();
		selectSort(arrays2);
		System.out.println("selectSort---------" + (System.currentTimeMillis() - nowTime2));
		long nowTime3 = System.currentTimeMillis();
		quickSort(arrays3, 0, 9999);
		System.out.println("insertSort---------" + (System.currentTimeMillis() - nowTime3));
	}

	/**
	 * 冒泡排序
	 * 
	 * @param arrays 需要排序的数组
	 */
	public static void bubbleSort(int[] arrays) {
		for (int i = 0, length = arrays.length; i < length; i++) {
			for (int j = 0; j < length - i - 1; j++) {
				if (arrays[j] > arrays[j + 1]) {
					swap(arrays, j, j + 1);
				}
			}
		}
	}

	/**
	 * 选择排序
	 * 
	 * @param arrays 需要排序的数组
	 */
	public static void selectSort(int[] arrays) {
		for (int i = 0, length = arrays.length; i < length; i++) {
			int min = i;
			for (int j = i + 1; j < length; j++) {
				if (arrays[min] > arrays[j]) {
					min = j;
				}
			}
			if (min != i) {
				swap(arrays, min, i);
			}
		}
	}

	/**
	 * @Title: insertSort @Description: 插入排序 @param arrays @return void @throws
	 */
	public static void insertSort(int[] arrays) {
		for (int i = 1, length = arrays.length; i < length; i++) {
			for (int j = i; j > 0 && arrays[j - 1] > arrays[j]; j--) {
				swap(arrays, j - 1, j);
			}
		}
	}

	/*
	 * 快速排序
	 * 
	 * @param array 待排序数组
	 * 
	 * @param startIndex 开始位置
	 * 
	 * @param endIndex 结束位置
	 */
	private static void quickSort(int[] array, int startIndex, int endIndex) {
		if (startIndex >= endIndex) {
			return;
		}
		int boundary = boundary(array, startIndex, endIndex);
		quickSort(array, startIndex, boundary - 1);
		quickSort(array, boundary + 1, endIndex);
	}

	/*
	 * 交换并返回分界点
	 * 
	 * @param array 待排序数组
	 * 
	 * @param startIndex 开始位置
	 * 
	 * @param endIndex 结束位置
	 * 
	 * @return 分界点
	 */
	private static int boundary(int[] array, int startIndex, int endIndex) {
		int standard = array[startIndex]; // 定义标准
		int leftIndex = startIndex; // 左指针
		int rightIndex = endIndex; // 右指针

		while (leftIndex < rightIndex) {
			while (leftIndex < rightIndex && array[rightIndex] >= standard) {
				rightIndex--;
			}
			array[leftIndex] = array[rightIndex];

			while (leftIndex < rightIndex && array[leftIndex] <= standard) {
				leftIndex++;
			}
			array[rightIndex] = array[leftIndex];
		}

		array[leftIndex] = standard;
		return leftIndex;
	}

	/**
	 * 将数组arrays中i下标和j下标的数据交换
	 * 
	 * @param arrays 操作的数组
	 * @param i      下标A
	 * @param j      下标B
	 */
	private static void swap(int[] arrays, int i, int j) {
		int temp = arrays[i];
		arrays[i] = arrays[j];
		arrays[j] = temp;
	}
}
