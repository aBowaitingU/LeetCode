package medium.topic2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
// 最初的想法实现的代码
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }

        int maxLen = 1;
        for (int i = 0; i < s.length(); i++) {
            Set<Character> uniqueSet = new HashSet<>();
            int j = i + maxLen;
            if (j >= s.length()) {
                break;
            }
            for (int k =i; k < j; k++) {
                uniqueSet.add(s.charAt(k));
            }
            if (uniqueSet.size() < maxLen) {
                continue;
            }
            for ( ; j < s.length(); j++) {
                if (uniqueSet.contains(s.charAt(j))) {
                    break;
                }
                uniqueSet.add(s.charAt(j));
                maxLen++;
            }
        }
        return maxLen;
    }
}
*/

/*
// 方法二 滑动窗口的实现
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }
}
*/

// 方法三 优化的滑动窗口
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
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

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            String s = stringToString(line);

            int ret = new Solution().lengthOfLongestSubstring(s);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}
