package medium.topic8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3)
            return Collections.emptyList();
        List<List<Integer>> res = new ArrayList<>();
        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;
        int negSize = 0;
        int posSize = 0;
        int zeroSize = 0;
        // 记录数组中大于0，小于0以及等于0的元素的个数，以及数组的最大值和最小值
        for (int v : nums) {
            if (v < minValue)
                minValue = v;
            if (v > maxValue)
                maxValue = v;
            if (v > 0)
                posSize++;
            else if (v < 0)
                negSize++;
            else
                zeroSize++;
        }
        // 数组中存在至少3个0时，[0, 0, 0]为一个结果
        if (zeroSize >= 3)
            res.add(Arrays.asList(0, 0, 0));
        // 当数组中不存在正数或负数，则直接返回结果数组
        if (negSize == 0 || posSize == 0)
            return res;
        // 剔除数组中一些过大或过小的数，这些数不可能让条件成立，进一步减少迭代的次数
        if (minValue * 2 + maxValue > 0)
            maxValue = -minValue * 2;
        else if (maxValue * 2 + minValue < 0)
            minValue = -maxValue * 2;

        int[] map = new int[maxValue - minValue + 1];
        int[] negs = new int[negSize];
        int[] poses = new int[posSize];
        negSize = 0;
        posSize = 0;
        // map[value]表示值value + minValue在输入数组的个数
        // poses数组和negs数组存储了输入数组中每一个不同的正数和负数值（没有重复），最后将poses和negs排序
        for (int v : nums) {
            if (v >= minValue && v <= maxValue) {
                if (map[v - minValue]++ == 0) {
                    if (v > 0)
                        poses[posSize++] = v;
                    else if (v < 0)
                        negs[negSize++] = v;
                }
            }
        }
        Arrays.sort(poses, 0, posSize);
        Arrays.sort(negs, 0, negSize);

        // 随着nv的减小，每次basej都会>=上一次迭代的basej，basej设置在外部可以有效减少basej++的次数
        int basej = 0;
        for (int i = negSize - 1; i >= 0; i--) {
            int nv = negs[i];
            int minp = (-nv) >>> 1;
            while (basej < posSize && poses[basej] < minp)
                basej++;
            // pv >= (int)(-nv / 2)
            // 随着pv的增大，cv不断减小，cv可能小到变成负数，但是cv不能比nv还要小
            for (int j = basej; j < posSize; j++) {
                int pv = poses[j];
                int cv = 0 - nv - pv;
                // 我们只考虑[nv,pv]间的cv值，超过这个范围的值，避免后续迭代得出重复结果
                // 且如果cv==nv或pv，需要查看cv值在输入数组的中是否有重复出现（通过map实现）
                if (cv >= nv && cv <= pv) {
                    if (cv == nv) {
                        if (map[nv - minValue] > 1)
                            res.add(Arrays.asList(nv, nv, pv));
                    } else if (cv == pv) {
                        if (map[pv - minValue] > 1)
                            res.add(Arrays.asList(nv, pv, pv));
                    } else {
                        if (map[cv - minValue] > 0)
                            res.add(Arrays.asList(nv, cv, pv));
                    }
                } else if (cv < nv)
                    break;
            }
        }
        return res;
    }
}

/*
// 时间复杂度太高，效率有点低
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        // 建立nums[i]值与位置数组的映射
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.get(nums[i]).add(i);
            } else {
                List<Integer> indexList = new ArrayList<>();
                indexList.add(i);
                map.put(nums[i], indexList);
            }
        }
        //如果使用set来判定num[i], num[j]是否重复出现，则容易造成很大的延迟
//        Set<Set<Integer>> set = new HashSet<>();
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
//                Set<Integer> set2Num = new HashSet<>();
//                set2Num.add(nums[i]);
//                set2Num.add(nums[j]);
//                if (set.contains(set2Num)) {
//                    continue;
//                }
//                set.add(set2Num);
                if (map.containsKey(0 - nums[i] - nums[j])) {
                    List<Integer> indexList = map.get(0 - nums[i] - nums[j]);
                    for(int index : indexList) {
                        if (index < j && index != i) {
                            break;
                        }
                        if (index > j) {
                            // 判定num[i], num[j]的组合是否曾经出现过
                                if (map.get(nums[i]).get(0) < i) {
                                break;
                            }
                            if (nums[i] == nums[j]) {
                                if (map.get(nums[j]).get(1) < j) {
                                    break;
                                }
                            } else if (map.get(nums[j]).get(0) < j) {
                                break;
                            }

                            List<Integer> array = new ArrayList<>();
                            array.add(nums[i]);
                            array.add(nums[j]);
                            array.add(nums[index]);
                            resultList.add(array);
                            break;
                        }
                    }
                }
            }

        }

        return resultList;
    }
}
*/

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

            List<List<Integer>> ret = new Solution().threeSum(nums);

            String out = int2dListToString(ret);

            System.out.print(out);
        }
    }
}