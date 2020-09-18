package leetcode;

import java.util.*;

public class T0049_Group_Anagrams {

    /**
     * 49. 字母异位词分组
     * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
     *
     * 示例:
     * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
     * 输出:
     * [
     *   ["ate","eat","tea"],
     *   ["nat","tan"],
     *   ["bat"]
     * ]
     *
     * 说明：
     * 所有输入均为小写字母。
     * 不考虑答案输出的顺序。
     */

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        int idx = 0;
        for (String str : strs) {
            char[] chs = str.toCharArray();
            Arrays.sort(chs);
            String sortedStr = new String(chs);
            if (map.containsKey(sortedStr)) {
                map.get(sortedStr).add(str);
            } else {
                map.put(sortedStr, new ArrayList<>());
                map.get(sortedStr).add(str);
            }
        }

        for (List<String> list : map.values()) {
            ans.add(list);
        }
        return ans;
    }

}
