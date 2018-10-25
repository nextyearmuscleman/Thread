/**
 * Author:  jixuelei
 */
package Sort;

import java.util.Arrays;

/**
 * @author jixuelei
 */
public class Study_QuickSort {

    public static void  quick_sort(int[] arr,int low,int high){
        /**
         * 下面的递归调用的时候。会出现基准数左边|右边只有一个数字的情况；此时就应该结束递归
         */
        if (low >= high){
            return ;
        }
        int i = low;
        int j = high;
        int index = arr[i];//index是一个基准数

        //从右边开始与基准数比较，如果比基准数大就j--;直到比基准数小
       while(i <j){
           /**
            * 当{3，4，5，6}的时候，index=3；a[i]=3,a[j]=6;arr[j] >= index这个条件一直是满足的。
            * 那么j--就会一直执行到j==i；直到j<0;因此添加边界条件是i<j
            */
           while(i < j && arr[j] >= index) j--;
           if (i<j){
               int tmp = arr[j];
               arr[j] = arr[i];
               arr[i] = tmp;
           }
           /**
            * 当{1，2，3，4}；index等与4；a[i]一直比index小，i一直++；直到i出了边界
            */
           while(i < j && arr[i] <= index) i++;
            if(i<j){
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }

       }
        quick_sort(arr,low,i-1);
       quick_sort(arr,i+1,high);

    }

    public static void main(String[] args) {
        int[] a={1,4,8,-4,-12,10};
        quick_sort(a,0,a.length-1);
        System.out.println(Arrays.toString(a));

    }
}
