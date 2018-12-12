package medium.topic10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Solution {
    private static Map<Integer, List<String>> numMap = new HashMap<>(8);
    static {
        numMap.put(2, new ArrayList<>(Arrays.asList("a", "b", "c")));
        numMap.put(3, new ArrayList<>(Arrays.asList("d", "e", "f")));
        numMap.put(4, new ArrayList<>(Arrays.asList("g", "h", "i")));
        numMap.put(5, new ArrayList<>(Arrays.asList("j", "k", "l")));
        numMap.put(6, new ArrayList<>(Arrays.asList("m", "n", "o")));
        numMap.put(7, new ArrayList<>(Arrays.asList("p", "q", "r", "s")));
        numMap.put(8, new ArrayList<>(Arrays.asList("t", "u", "v")));
        numMap.put(9, new ArrayList<>(Arrays.asList("w", "x", "y", "z")));
    }
    public List<String> letterCombinations(String digits) {
        StringBuilder resultStr = new StringBuilder();
        List<String> resultList = new ArrayList<>();

        if (digits == null || digits.length() == 0) {
            return resultList;
        }

        getResultStr(digits, 0, resultStr, resultList);
        return resultList;
    }

    private void getResultStr(String digits, int index, StringBuilder resultStr, List<String> resultList) {
        List<String> charList = numMap.get(Integer.parseInt("" + digits.charAt(index)));
        for (String singleChar : charList) {
            resultStr.delete(index, resultStr.length()).append(singleChar);
            if (index == digits.length() - 1) {
                resultList.add(resultStr.toString());
            } else {
                getResultStr(digits, (index + 1), resultStr, resultList);
            }
        }

    }
}

public class MainClass {
    public static String stringToString(String input) {
        if (input == null) {
            return "null";
        }
        return input.trim();
//        return Json.value(input).toString();
    }

    public static String stringListToString(List<String> stringList) {
        StringBuilder sb = new StringBuilder("[");
        for (String item : stringList) {
            sb.append(item);
            sb.append(",");
        }

        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            String digits = stringToString(line);

            List<String> ret = new Solution().letterCombinations(digits);

            String out = stringListToString(ret);

            System.out.print(out);
        }
    }
}
