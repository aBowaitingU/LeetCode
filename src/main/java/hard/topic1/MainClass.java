package main.java.hard.topic1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;


class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m < n) {
            return findMedian(nums1,nums2);
        } else {
            return findMedian(nums2, nums1);
        }
    }

    private double findMedian(int[] array1, int[] array2) {
        //array1.length < array2.length
        int minM = 0,maxM = array1.length;
        int halfLen = (array1.length + array2.length + 1) / 2;
        while (minM <= maxM) {
            int i = (minM + maxM) / 2;
            int j = halfLen - i;
            if (i < maxM && array2[j - 1] > array1[i]) {
                minM = i + 1;
            } else if (i > minM && array1[i - 1] > array2[j]) {
                maxM = i - 1;
            } else {
                int leftMax = 0;
                if (i == 0) {
                    leftMax = array2[j - 1];
                } else if (j == 0) {
                    leftMax = array1[i - 1];
                } else {
                    leftMax = Math.max(array1[i -1], array2[j - 1]);
                }
                if ((array1.length + array2.length) % 2 == 1) {
                    return leftMax;
                }

                int rightMin = 0;
                if ( i == array1.length) {
                    rightMin = array2[j];
                } else if (j == array2.length) {
                    rightMin = array1[i];
                } else {
                    rightMin = Math.min(array1[i], array2[j]);
                }

                return (leftMax + rightMin) / 2.0;
            }
        }
        return 0.0;
    }

}

public class MainClass {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static String doubleToString(String input) {
        return new DecimalFormat("0.00000").format(input);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums1 = stringToIntegerArray(line);
            line = in.readLine();
            int[] nums2 = stringToIntegerArray(line);

            double ret = new Solution().findMedianSortedArrays(nums1, nums2);

            String out = doubleToString(ret + "");

            System.out.print(out);
        }
    }
}
