package hard.topic5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> resultList = new ArrayList<>();
        if (words == null || words.length == 0 || words[0].length() == 0) {
            return resultList;
        }
        int wordsNum = words.length;
        int wordsLength = words[0].length();
        if (s.length() < wordsNum * wordsLength) {
            return resultList;
        }
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.merge(word, 1, (oldValue, value) -> oldValue + value);
        }

        int index = 0;
        for (; index <= s.length() - wordsNum * wordsLength; index++) {
            if (map.get(s.substring(index, index + wordsLength)) != null) {
                Map<String, Integer> stringMap = new HashMap<>();
                for (int i = 0; i < wordsNum; i++) {
                    stringMap.merge(s.substring(index + i * wordsLength, index + (i + 1) * wordsLength),
                            1, (oldValue, value) -> oldValue + value);
                }
                if (map.equals(stringMap)) {
                    resultList.add(index);
                }
            }
        }

        return resultList;
    }
}

public class MainClass {

    public static void main(String[] args) throws IOException {
            String s = "wordgoodgoodgoodbestword";
            String[] words = {"word","good","best","good"};

            List<Integer> ret = new Solution().findSubstring(s, words);

            System.out.print(ret);

    }
}