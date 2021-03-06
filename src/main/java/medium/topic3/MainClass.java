package medium.topic3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
             return "";
        }
        int maxLen = 0;
        int strStart = 0;
        int strEnd = 0;
        for (int k = 2; k < 2 * s.length() - 1; k++) {
            int i, j;
            if (k % 2 == 0) {
                i = k / 2 - 1;
                j = k / 2;
            } else {
                i = k / 2 - 1;
                j = k / 2 + 1;
            }

            while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
                i--;
                j++;
            }
            // 将判定放在循环后，避免每次循环都判定一次，增加开销
            // 此时，真正的回文字符串的位置应该是i + 1和j - 1
            if (maxLen < j - i - 1) {
                maxLen = j - i - 1;
                strStart = ++i;
                strEnd = --j;
            }
        }
        return s.substring(strStart, strEnd + 1);
    }

}

public class MainClass {
    public static String stringToString(String input) {
        if (input == null) {
            return "null";
        }
//        return Json.value(input).toString();
        return input.trim();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            String s = stringToString(line);

            String ret = new Solution().longestPalindrome(s);

            String out = (ret);

            System.out.print(out);
        }
    }
}