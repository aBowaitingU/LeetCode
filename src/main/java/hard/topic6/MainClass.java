package hard.topic6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }

        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            int isValid = 0;
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(j) == '(') {
                    isValid++;
                } else if (s.charAt(j) == ')') {
                    isValid--;
                }
                if (isValid == 0 && result < j - i) {
                    result = j - i + 1;
                }
                if (isValid < 0) {
                    break;
                }
            }
        }

        return result;
    }
}

public class MainClass {


    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            String s = line;

            int ret = new Solution().longestValidParentheses(s);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}