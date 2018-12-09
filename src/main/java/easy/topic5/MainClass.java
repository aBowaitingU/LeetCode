package easy.topic5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        } else if (strs.length == 1) {
            return strs[0];
        }

        int length = 0;
        boolean isSame = true;
        while (isSame) {
            if (strs[0].length() <= length) {
                return strs[0];
            }
            for (int i = 1; i < strs.length; i++) {
                if (strs[i].length() <= length) {
                    return strs[i];
                }
                if (strs[i].charAt(length) != strs[i - 1].charAt(length)) {
                    isSame = false;
                    break;
                }
            }
            if (isSame) {
                length++;
            }
        }

        return strs[0].substring(0, length);
    }
}

public class MainClass {
//    public static String[] stringToStringArray(String input) {
//        JsonArray jsonArray = JsonArray.readFrom(line);
//        String[] arr = new String[jsonArray.size()];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = jsonArray.get(i).asString();
//        }
//        return arr;
//    }
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        String line;
//        while ((line = in.readLine()) != null) {
//            String[] strs = stringToStringArray(line);
//
//            String ret = new Solution().longestCommonPrefix(strs);
//
//            String out = (ret);
//
//            System.out.print(out);
//        }
//    }
}
