package medium.topic6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 暴力法，效率很低
class Solution {
    public int maxArea(int[] height) {
        int maxSize = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                maxSize = maxSize < (j - i) * Math.min(height[i], height[j]) ? (j - i) * Math.min(height[i], height[j]) : maxSize;
            }
        }
        return maxSize;
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

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] height = stringToIntegerArray(line);

            int ret = new Solution().maxArea(height);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}
