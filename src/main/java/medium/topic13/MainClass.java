package medium.topic13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


class Solution {
    class Parenthesis {
        String temp;
        int left;
        int right;

        public Parenthesis(String temp, int left, int right) {
            this.temp = temp;
            this.left = left;
            this.right = right;
        }
    }
    public List<String> generateParenthesis(int n) {
        List<Parenthesis> list1 = new ArrayList<>();
        List<Parenthesis> list2 = new ArrayList<>();
        list1.add(new Parenthesis("", 0, 0));

        for (int i = 0; i < 2 * n; i++) {
            //
            List<Parenthesis> listFrom, listTo;
            if (i % 2 == 0) {
                listFrom = list1;
                listTo = list2;
            } else {
                listFrom = list2;
                listTo = list1;
            }

            for (Parenthesis parenthesis : listFrom) {
                if (parenthesis.left == parenthesis.right) {
                    listTo.add(new Parenthesis(parenthesis.temp + "(", parenthesis.left + 1, parenthesis.right));
                } else if (parenthesis.left > parenthesis.right) {
                    listTo.add(new Parenthesis(parenthesis.temp + ")", parenthesis.left, parenthesis.right + 1));
                    if (parenthesis.left < n) {
                        listTo.add(new Parenthesis(parenthesis.temp + "(", parenthesis.left + 1, parenthesis.right));
                    }
                }
            }
            listFrom.clear();
        }
        List<String> result = list1.stream().map(p -> p.temp).collect(Collectors.toList());
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