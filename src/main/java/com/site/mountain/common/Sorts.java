package com.site.mountain.common;

import java.util.Arrays;

public class Sorts {

    public static void main(String[] args) {
        int[] numbers = {10, 20, 15, 0, 6, 7, 2, 1, -5, 55};
        try {
            System.out.println(Arrays.toString(Sorts.bubbleSort(numbers)));
            System.out.println(Arrays.toString(Sorts.selectionSort(numbers)));
            System.out.println(Arrays.toString(Sorts.insertSort(numbers)));
            System.out.println(Arrays.toString(Sorts.shellSort(numbers)));
            System.out.println(Arrays.toString(Sorts.mergeSort(numbers)));

            Sorts.quickSort(numbers, 0, numbers.length - 1);
            System.out.println(Arrays.toString(numbers));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 冒泡排序
     * 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
     * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。
     * 针对所有的元素重复以上的步骤，除了最后一个。
     * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
     */
    public static int[] bubbleSort(int[] sourceArray) throws Exception {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        int size = arr.length;
        int temp = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }


    /**
     * 选择排序
     * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置
     * 再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
     * 重复第二步，直到所有元素均排序完毕。
     *
     * @param sourceArray
     * @return
     * @throws Exception
     */
    public static int[] selectionSort(int[] sourceArray) throws Exception {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        int size = sourceArray.length;
        int temp = 0;
        int min = 0;
        for (int i = 0; i < size; i++) {
            min = i;
            for (int j = i; j < size; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
        return arr;
    }

    /**
     * 插入排序
     * <p>
     * 将第一待排序序列第一个元素看做一个有序序列，把第二个元素到最后一个元素当成是未排序序列。
     * <p>
     * 从头到尾依次扫描未排序序列，将扫描到的每个元素插入有序序列的适当位置
     * （如果待插入的元素与有序序列中的某个元素相等，则将待插入元素插入到相等元素的后面。）
     */
    public static int[] insertSort(int[] sourceArray) throws Exception {
        int size = sourceArray.length;
        int[] arr = Arrays.copyOf(sourceArray, size);
        int temp = 0;
        int j = 0;
        for (int i = 1; i < size; i++) {
            temp = arr[i];
            for (j = i; j > 0 && temp < arr[j - 1]; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = temp;
        }
        return arr;
    }


    /**
     * 希尔排序
     * <p>
     * 选择一个增量序列 t1，t2，……，tk，其中 ti > tj, tk = 1；
     * <p>
     * 按增量序列个数 k，对序列进行 k 趟排序；
     * <p>
     * 每趟排序，根据对应的增量 ti，将待排序列分割成若干长度为 m 的子序列，
     * 分别对各子表进行直接插入排序。仅增量因子为 1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
     *
     * @param sourceArray
     * @return
     * @throws Exception
     */
    public static int[] shellSort(int[] sourceArray) throws Exception {
        int size = sourceArray.length;
        int[] arr = Arrays.copyOf(sourceArray, size);
        int gap = 1;
        int temp, j;
        for (gap = size / 2; gap >= 1; gap = gap / 2) {
            for (int i = gap; i < size; i++) {
                temp = arr[i];
                j = i - gap;
                while (j >= 0 && temp < arr[j]) {
                    arr[j + gap] = arr[j];
                    j = j - gap;
                }
                arr[j + gap] = temp;
            }
        }
        return arr;
    }


    /**
     * 归并排序
     * <p>
     * 5.1 算法步骤
     * 申请空间，使其大小为两个已经排序序列之和，该空间用来存放合并后的序列；
     * 设定两个指针，最初位置分别为两个已经排序序列的起始位置；
     * 比较两个指针所指向的元素，选择相对小的元素放入到合并空间，并移动指针到下一位置；
     * 重复步骤 3 直到某一指针达到序列尾；
     * 将另一序列剩下的所有元素直接复制到合并序列尾。
     *
     * @param sourceArray
     * @return
     * @throws Exception
     */
    public static int[] mergeSort(int[] sourceArray) throws Exception {
        int size = sourceArray.length;
        int[] arr = Arrays.copyOf(sourceArray, size);
        if (arr.length < 2) {
            return arr;
        }
        int middle = (int) Math.floor(arr.length / 2);
        int[] left = Arrays.copyOfRange(arr, 0, middle);
        int[] right = Arrays.copyOfRange(arr, middle, arr.length);
        return merge(mergeSort(left), mergeSort(right));
    }

    protected static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0;
        while (left.length > 0 && right.length > 0) {
            if (left[0] <= right[0]) {
                result[i++] = left[0];
                left = Arrays.copyOfRange(left, 1, left.length);
            } else {
                result[i++] = right[0];
                right = Arrays.copyOfRange(right, 1, right.length);
            }
        }
        while (left.length > 0) {
            result[i++] = left[0];
            left = Arrays.copyOfRange(left, 1, left.length);
        }

        while (right.length > 0) {
            result[i++] = right[0];
            right = Arrays.copyOfRange(right, 1, right.length);
        }
        return result;
    }

    /**
     * 快速排序
     * <p>
     * 从数列中挑出一个元素，称为 “基准”（pivot）;
     * <p>
     * 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
     * <p>
     * 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序；
     *
     * @param low
     * @param high
     * @return
     * @throws Exception
     */
    public static int[] quickSort(int[] arr, int low, int high) throws Exception {
//        int size = sourceArray.length;
//        int[] arr = Arrays.copyOf(sourceArray, size);
        int i, j, temp, t;
        if (low > high) {
            return arr;
        }
        i = low;
        j = high;
        //temp就是基准位
        temp = arr[low];
        while (i < j) {
            //先看后边，依次向左递减
            while (temp <= arr[j] && i < j) {
                j--;
            }
            while (temp >= arr[i] && i < j) {
                i++;
            }
            //如果满足条件则交换
            if (i < j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }

        }
        //最后将基准为与i和j相等位置的数字交换
        arr[low] = arr[i];
        arr[i] = temp;
        //递归调用左半数组

        quickSort(arr, low, j - 1);
        quickSort(arr, j + 1, high);

        return arr;
    }

}
