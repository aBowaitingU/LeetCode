package medium.topic5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;

class Solution {
    public int myAtoi(String str) {
        if (str == null) {
            return 0;
        }
        str = str.trim();
        if (str.length() == 0) {
            return 0;
        }
        int i = 0;
        boolean isNegative = false;
        if (str.charAt(0) == '-') {
            i = 1;
            isNegative = true;
        } else if (str.charAt(0) == '+') {
            i = 1;
        }

        int result = 0;
        for (; i < str.length();i++) {
            if (str.charAt(i)<= '9' && str.charAt(i) >= '0') {
                int singleNum = str.charAt(i) - '0';
                if (!isNegative && result > (Integer.MAX_VALUE - singleNum) / 10){
                    return Integer.MAX_VALUE;
                } else if (isNegative && -result < (Integer.MIN_VALUE + singleNum) / 10) {
                    return Integer.MIN_VALUE;
                }
                result = result * 10 + singleNum;
            } else {
                break;
            }
        }
        result = isNegative ? -result : result;
        return result;

    }
}

public class MainClass {
    public static String stringToString(String input) {
        if (input == null) {
            return "null";
        }
        return input;
//        return Json.value(input).toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            String str = stringToString(line);

            int ret = new Solution().myAtoi(str);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}
