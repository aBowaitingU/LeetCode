package medium.topic11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return resultList;
        }
        // nums中的数值和数值个数的映射
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], 1);
            } else {
                int numSize = map.get(nums[i]);
                numSize++;
                map.put(nums[i], numSize);
            }
        }
        Integer[] array = new Integer[map.keySet().size()];
        map.keySet().toArray(array);
        Arrays.sort(array);

        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array.length; j++) {
                if (i == j && map.get(array[i]) < 2) {
                    continue;
                }
                int half = (target - array[i] - array[j]) / 2;
                int base = 0;
                if (half > array[array.length - 1]) {
                    // 如果half比array中的最大值还要大，那肯定不满足
                    continue;
                }
                while (array[base] < half) {
                    base++;
                }
                if (base < j) {
                    break;
                }
                for (int k = Math.max(base, j); k < array.length; k++) {
                    int numNeed = target - array[i] - array[j] - array[k];
                    if (map.containsKey(numNeed)) {
                        if (numNeed > array[j] && numNeed < array[k]) {
                            resultList.add(new ArrayList<>(Arrays.asList(array[i], array[j], array[k], numNeed)));
                            // 我们需要详细讨论array[i],array[j],array[k]和numNeed取相同值时的情况
                        } else if (numNeed == array[j] || numNeed ==array[k]) {
                            if (i == j) {
                                if (j == k) {
                                    // array[i] = array[j] = array[k] = numNeed
                                    if (map.get(numNeed) >= 4) {
                                        resultList.add(new ArrayList<>(Arrays.asList(array[i], array[j], array[k], numNeed)));
                                    }
                                } else {
                                    // array[j] != array[k]
                                    if (numNeed == array[j]) {
                                        // array[i] = array[j] = numNeed
                                        if (map.get(numNeed) >= 3) {
                                            resultList.add(new ArrayList<>(Arrays.asList(array[i], array[j], array[k], numNeed)));
                                        }
                                    }
                                    if (numNeed == array[k]) {
                                        // array[k] = numNeed
                                        if (map.get(numNeed) >= 2) {
                                            resultList.add(new ArrayList<>(Arrays.asList(array[i], array[j], array[k], numNeed)));
                                        }
                                    }
                                }
                            } else {
                                // array[i] != array[j]
                                if (j == k ) {
                                    // array[j] = array[k] = numNeed
                                    if (map.get(numNeed) >= 3) {
                                        resultList.add(new ArrayList<>(Arrays.asList(array[i], array[j], array[k], numNeed)));
                                    }
                                } else {
                                    // array[j] != array[k]
                                    // array[j] = numNeed || array[k] = numNeed
                                    if (map.get(numNeed) >= 2) {
                                        resultList.add(new ArrayList<>(Arrays.asList(array[i], array[j], array[k], numNeed)));
                                    }
                                }

                            }
                        } else if (numNeed < array[j]){
                            break;
                        }
                    }
                }
            }
        }

        return resultList;
    }
}

public class MainClass {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static String integerArrayListToString(List<Integer> nums, int length) {
        if (length == 0) {
            return "[]";
        }

        String result = "";
        for(int index = 0; index < length; index++) {
            Integer number = nums.get(index);
            result += Integer.toString(number) + ", ";
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static String integerArrayListToString(List<Integer> nums) {
        return integerArrayListToString(nums, nums.size());
    }

    public static String int2dListToString(List<List<Integer>> nums) {
        StringBuilder sb = new StringBuilder("[");
        for (List<Integer> list: nums) {
            sb.append(integerArrayListToString(list));
            sb.append(",");
        }

        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums = stringToIntegerArray(line);
            line = in.readLine();
            int target = Integer.parseInt(line);

            List<List<Integer>> ret = new Solution().fourSum(nums, target);

            String out = int2dListToString(ret);

            System.out.print(out);
        }
    }
}
