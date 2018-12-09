package medium.topic7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
    public String intToRoman(int num) {
        int[] numArray = new int[4];
        for (int i = 0; i < numArray.length; i++) {
            numArray[i] = num % 10;
            num /= 10;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = numArray.length - 1; i >= 0; i--) {
            switch (i) {
                case 3:
                    formatString(stringBuilder, numArray[i], "M", "", "");
                    break;
                case 2:
                    formatString(stringBuilder, numArray[i], "C", "D", "M");
                    break;
                case 1:
                    formatString(stringBuilder, numArray[i], "X", "L", "C");
                    break;
                case 0:
                    formatString(stringBuilder, numArray[i], "I", "V", "X");
                    break;
            }
        }
        return stringBuilder.toString();
    }

    private void formatString(StringBuilder str, int index, String low, String medium, String high) {
        if (index < 4) {
            for (int i = 0; i < index; i++) {
                str.append(low);
            }
        } else if (index == 4) {
            str.append(low + medium);
        } else if (index < 9) {
            str.append(medium);
            for (int i = 0; i < index - 5; i++) {
                str.append(low);
            }
        } else {
            str.append(low + high);
        }
    }
}

public class MainClass {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int num = Integer.parseInt(line);

            String ret = new Solution().intToRoman(num);

            String out = (ret);

            System.out.print(out);
        }
    }
}
