package leetcode;

public class T0287_Find_The_Duplicate_Number {

    /**
     * 287. 寻找重复数
     * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
     *
     * 示例 1:
     *
     * 输入: [1,3,4,2,2]
     * 输出: 2
     * 示例 2:
     *
     * 输入: [3,1,3,4,2]
     * 输出: 3
     * 说明：
     *
     * 不能更改原数组（假设数组是只读的）。
     * 只能使用额外的 O(1) 的空间。
     * 时间复杂度小于 O(n2) 。
     * 数组中只有一个重复的数字，但它可能不止重复出现一次。
     */


    /**
     * 快慢指针解法
     */
    public static int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        fast = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }

    /**
     * 数学解法 【不适用本题, 适合的问题的是：一个数字重复了两次，找到缺失的数字和重复的数字】
     * 设重复的数字为 x，缺少的数字为 y
     * 将数组所有数字相加、相乘分别得到 Sum_A, Prod_A
     * 将1～n所有数字相加、相乘分别得到 Sum_O, Prod_O
     * 根据题目意思，这个数组中出现两次 x，而原数组中每个数字只出现一次，可得 2 * x - y = Sum_A - Sum_O
     * 再将 Prod_A 和 Prod_O 相比，可得 Prod_A / Prod_O = (x * x) / (x * y) = x / y
     * 联列方程组, 可解出 x = Prod_A * (Sum_A - Sum_O) / (Mul_A - Mul_O)
     */
    public static int findDuplicate2(int[] nums) {
        int sumA = 0, sumO = 0;
        int prodA = 1, prodO = 1;

        for (int i = 0; i < nums.length; ++i) {
            sumA += nums[i];
            sumO += i + 1;
            prodA *= nums[i];
            prodO *= i + 1;
        }

        return prodA * (sumA - sumO) / (prodA - prodO);
    }


    /**
     * 交换位置
     */
    public static int findDuplicate3(int[] nums) {
        if (nums.length == 1) return nums[0];

        for (int i = 0; i < nums.length; ++i) {
            while (nums[i] != (i + 1)) {
                if (nums[i] == nums[nums[i] - 1]) return nums[i];
                swap(nums, nums[i] - 1, i);
            }
        }
        return -1;
    }

    private static void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] + nums[j] - (nums[j] = nums[i]);
    }

    public static void main(String[] args) {
        System.out.println(findDuplicate(new int[]{1, 3, 4, 2, 2}));
    }

}
