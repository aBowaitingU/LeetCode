package easy.topic6;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Solution {
    public boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');
        List<Character> stock = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            if (map.keySet().contains(s.charAt(i))) {
                stock.add(s.charAt(i));
            } else if (map.values().contains(s.charAt(i))) {
                if (stock.size() > 0 && map.get(stock.get(stock.size() - 1)) == s.charAt(i)) {
                    stock.remove(stock.size() - 1);
                } else {
                    return false;
                }
            }
        }

        if (stock.size() > 0) {
            return false;
        }
        return true;
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

    public static String booleanToString(boolean input) {
        return input ? "True" : "False";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            String s = stringToString(line);

            boolean ret = new Solution().isValid(s);

            String out = booleanToString(ret);

            System.out.print(out);
        }
    }
}
