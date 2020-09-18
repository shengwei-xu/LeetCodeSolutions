package leetcode;

public class T0137_Single_Number_II {

    /**
     * 137. 只出现一次的数字 II
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
     *
     * 说明：
     * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     *
     * 示例 1:
     * 输入: [2,2,3,2]
     * 输出: 3
     *
     * 示例 2:
     * 输入: [0,1,0,1,0,1,99]
     * 输出: 99
     */

    public static int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < 32; ++i) {
            int cnt = 0;
            for (int n : nums) {
                if ((n & (1 << i)) != 0) ++cnt;
            }
            if (cnt % 3 != 0) {
                res |= (1 << i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(singleNumber(new int[] {0,1,0,1,0,1,99}));
    }

}
