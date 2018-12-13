package medium.topic13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
// 错的
class Solution {
    public List<String> generateParenthesis(int n) {
        List<List<String>> lists = new ArrayList<>(2);
        List<String> startList = new ArrayList<>(Arrays.asList("()"));
        if (n == 1) {
            return startList;
        }
        lists.add(startList);
        for (int i = 1; i < n; i++) {
            List<String> preList = lists.get((i - 1) % 2);
            List<String> list = new ArrayList<>(preList.size() * 3 - 1);
            for (int j = 0; j < preList.size(); j++) {
                if (j == 0) {
                    list.add(preList.get(j) + "()");
                    list.add("(" + preList.get(j) + ")");
                } else {
                    list.add("()" + preList.get(j));
                    list.add(preList.get(j) + "()");
                    list.add("(" + preList.get(j) + ")");
                }
            }
            if (i == 1) {
                lists.add(list);
            } else {
                lists.set(i % 2, list);
            }
        }
        List<String> result = lists.get((n - 1) % 2);
        Collections.sort(result);
        return result;
    }
}

public class MainClass {
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
            int n = Integer.parseInt(line);

            List<String> ret = new Solution().generateParenthesis(n);

            String out = stringListToString(ret);

            System.out.print(out);
        }
    }
}