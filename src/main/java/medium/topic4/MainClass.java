package medium.topic4;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
    public String convert(String s, int numRows) {
        StringBuilder stringBuilder = new StringBuilder();
        if (numRows == 1) {
            return s;
        }
        for (int i = 0; i < numRows; i++) {
            int index = 0;
            if (i == 0 || i == numRows - 1) {
                while (index + i < s.length()) {
                    stringBuilder.append(s.charAt(index + i));
                    index += 2 * (numRows - 1);
                }
            } else {
                while (index + i < s.length()) {
                    stringBuilder.append(s.charAt(index + i));
                    index += 2 * (numRows - 1);
                    if (index - i < s.length()) {
                        stringBuilder.append(s.charAt(index - i));
                    } else {
                        break;
                    }
                }
            }
        }
        return stringBuilder.toString();
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
            line = in.readLine();
            int numRows = Integer.parseInt(line);

            String ret = new Solution().convert(s, numRows);

            String out = (ret);

            System.out.print(out);
        }
    }
}
