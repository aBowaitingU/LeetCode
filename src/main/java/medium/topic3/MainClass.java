package medium.topic3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
    public String longestPalindrome(String s) {
        for (int k = 2; k < 2 * s.length() - 2; k++) {
            if (k % 2 == 0) {

            } else {

            }
        }
    }

    private boolean isPalindromic (String palindromicStr) {

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