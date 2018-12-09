package hard.topic2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
// 使用动态规划解决
class Solution {
    public boolean isMatch(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        boolean[][] memory = new boolean[sLen+1][pLen+1];
        memory[0][0] = true;
        for(int i = 0; i <= sLen; i++) {
            for(int j = 1; j <= pLen; j++) {
                if(p.charAt(j-1) == '*') {
                    memory[i][j] = memory[i][j-2] || (i > 0 && (s.charAt(i-1) == p.charAt(j-2) ||
                            p.charAt(j-2) == '.') && memory[i-1][j]);
                }else {
                    memory[i][j] = i > 0 && (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.')
                            && memory[i-1][j-1];
                }
            }
        }
        return memory[sLen][pLen];
    }
}
*/
/*
// 在上面方法的基础上，由于每次都只使用了memory表中相邻的两行，因此可以进一步降低代码的空间复杂度如下
class Solution {
    public boolean isMatch(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        boolean[][] memory = new boolean[2][pLen+1];
        memory[0][0] = true;
        int cur = 0, pre = 0;
        for(int i = 0; i <= sLen; i++) {
            cur = i % 2;
            pre = (i + 1) % 2;
            if(i > 1) {
                // 当i>1时，每次迭代，都要重新复用当前这行，所以在迭代前将本行内的数据都重置为初始值，即false
                for(int j = 0; j <= pLen; j++) {
                    memory[cur][j] = false;
                }
            }
            for(int j = 1; j <= pLen; j++) {
                if(p.charAt(j-1) == '*') {

                    memory[cur][j] = memory[cur][j-2] || (i > 0 && (s.charAt(i-1) == p.charAt(j-2) ||
                            p.charAt(j-2) == '.') && memory[pre][j]);
                }else {
                    memory[cur][j] = i > 0 && (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.')
                            && memory[pre][j-1];
                }
            }
        }
        return memory[cur][pLen];
    }
}
*/

// 把模式P拆分成多个子模式,子模式分为完全匹配和动态匹配。然后用深度优先的方式进行搜索，只要找到一个符合的组合就返回。
class Solution {
    class PatternNode{
        String str;                                         // 匹配字符串
        int matchType;                                      // 匹配类型 0 全部匹配、 1 动态匹配,匹配零个或多个
        int startIndex;                                     // 匹配字符串的开始索引
        int endIndex;                                       // 匹配字符串的结束索引
        PatternNode pre;                                    // 前一个节点
        PatternNode next;                                   // 下一个节点

        public PatternNode(String str,int matchType){
            this.str = str;
            this.matchType = matchType;
            startIndex = 0;
            endIndex = 0;
            pre = null;
            next = null;
        }
    }

    public boolean matchPattern(PatternNode node,String s){
        // sp指示字符串s当前索引的位置
        int sp = 0;
        if(node.pre != null){
            sp = node.pre.endIndex + 1;
        }
        if(node.matchType == 0){
            // 全部匹配
            if(sp + node.str.length() - 1 < s.length()){
                boolean isMatch = true;
                int i = 0;
                for(;i<node.str.length();i++){
                    // 全部匹配，当s[sp + i]与node[i]不等且node[i]不等于'.'时，匹配失败
                    if((s.charAt(sp+i) != node.str.charAt(i) && node.str.charAt(i) != '.')){
                        isMatch = false;
                        break;
                    }
                }
                if(isMatch){
                    node.startIndex = 0;
                    node.endIndex = sp+i-1;
                    if(node.next!=null){
                        return matchPattern(node.next,s);
                    }else{
                        if(node.endIndex == s.length() - 1){
                            return true;
                        }else{
                            return false;
                        }
                    }
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }else if(node.matchType == 1){
            // 匹配零个或多个
            int t = sp;
            while (t < s.length()){
                if(s.charAt(t) == node.str.charAt(0) || node.str.charAt(0) == '.'){
                    node.startIndex = sp;
                    node.endIndex = t;
                    // t-sp表示匹配字符串的个数，最初t-sp = 0，表示匹配0个字符
                    // 枚举了所有匹配字符个数的可能，进行一个深度的搜索，当存在一个t-sp，时后续的s和node匹配，则直接返回true
                    if(node.next!=null){
                        if(matchPattern(node.next,s)){
                            return true;
                        }
                    }
                    t++;
                }else {
                    break;
                }
            }
            if(node.next!=null){
                node.startIndex = sp;
                node.endIndex = sp-1;
                return matchPattern(node.next,s);
            }else{
                node.startIndex = sp;
                node.endIndex = t-1;
                if(node.endIndex == s.length() -1){
                    return true;
                }else{
                    return false;
                }
            }
        }else{
            return false;
        }
    }

    public boolean isMatch(String s, String p){
        int pp = 0;// 字符模式指针
        PatternNode pnList = null;
        PatternNode ptr = null;
        // 过滤空字符串
        if(s.equals("")){
            if(p.equals("")){
                return true;
            }
        }
        if(p.equals("")){
            return false;
        }
        // 拆分子模式
        while (pp<p.length()){
            PatternNode node;
            if(pp+1<p.length()&&p.charAt(pp+1)=='*'){
                // 匹配零个或多个
                node = new PatternNode(p.substring(pp,pp+1),1);
                pp++;
            }else{
                // 全部匹配
                int start = pp;
                while (pp+1<p.length()){
                    pp++;
                    // 遇到后面为*的的字符退出
                    if(pp+1<p.length()&&p.charAt(pp+1)=='*'){
                        pp--;
                        break;
                    }
                }
                node = new PatternNode(p.substring(start,pp+1),0);
            }
            if(pnList == null){
                pnList = node;
            }else {
                ptr.next = node;
                node.pre = ptr;
            }
            ptr = node;
            pp++;
        }
        return matchPattern(pnList,s);
    }
}


/*
// 失败代码
class Solution {
    public boolean isMatch(String s, String p) {
        // p = ""时
        if (p.length() == 0) {
            if (s.length() == 0) {
                return true;
            }
            return false;
        }
        // p仅包含一个字符时，此时p只能是一个a-z的字符或者'.'
        if (p.length() == 1) {
            if (s.length() == 0) {
                return false;
            } else if (s.length() == 1) {
                if (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.') {
                    return true;
                }
                return false;
            }
            return false;
        }
        // 当s = '',p中，每一个有效的字符(a-z,或者'.')后面，都需要带一个*，才能符合条件
        if (s.length() == 0) {
            if (p.length() % 2 == 0) {
                // 符合条件的p的长度必须为偶数
                for (int i = 1; i < p.length(); i += 2) {
                    if (p.charAt(i) != '*') {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }

        int k = 0;
        int i = 0;
        while (k < s.length() && i < p.length() - 1) {
            int j = i + 1;
            if (p.charAt(j) != '*') {
               if (s.charAt(k) != p.charAt(i) && p.charAt(i) != '.') {
                   return false;
               }
               i++;
               k++;
            } else {
                // p[j] = '*'时
                if (s.charAt(i) != '.') {
                    int countInS = 0;
                    char value = s.charAt(k);
                    while(k < s.length() && s.charAt(k) == value) {
                        k++;
                        countInS++;
                    }
                    int countInP = 0;
                    while (i < p.length() - 1 && s.charAt(i) == value) {
                        j = i + 1;
                        if (p.charAt(j) != '*') {
                            i = j;
                            countInP++;
                        } else {
                            i += 2;
                        }
                    }
                    if (countInP > countInS) {
                        return false;
                    }

                } else {
                 // 遇到".*"时

                }
            }

        }
        if (k == s.length() && i == p.length()) {
            return true;
        }


    }
}
*/

public class MainClass {
    public static String stringToString(String input) {
        if (input == null) {
            return "null";
        }
//        return Json.value(input).toString();
        return input.trim();
    }

    public static String booleanToString(boolean input) {
        return input ? "True" : "False";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            String s = stringToString(line);
            line = in.readLine();
            String p = stringToString(line);

            boolean ret = new Solution().isMatch(s, p);

            String out = booleanToString(ret);

            System.out.print(out);
        }
    }
}