/**
 *Given a 32-bit signed integer, reverse digits of an integer.

    Example 1:
    Input: 123
    Output: 321

    Example 2:
    Input: -123
    Output: -321

    Example 3:
    Input: 120
    Output: 21

    Note:
    Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. 
    For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 *  
 **/

#include "iostream"
#include "cstdio"
using namespace std;

class Solution {
public:
    /**
     * -2^31    = -2147483648
     *  2^31 -1 =  2147483647
     * Note: cpp 负数模除时被除数与除数取绝对值运算后结果的符号与被除数一致.
     **/
    // Solution 1 
    int reverse(int x) {
        int res = 0;
        while(x != 0) {
            int tmp = x % 10;
            x /= 10;
            // why `tmp > 7`? 
            // when `res == INT_MAX/10` (i.e. 214748364)
            // `res * 10 + tmp` will overflow `if tmp > 7`.
            if ((res > INT_MAX/10) || (res == INT_MAX/10 && tmp > 7)) return 0;
            if ((res < INT_MIN/10) || (res == INT_MIN/10 && tmp < -8)) return 0;
            res = res * 10 + tmp;  // `res` maybe overflow here.
        }
        return res;
    }
    // Solution 2 
    int reverse2(int x) {
        int res = 0;
        long long check = 0;
        while(x != 0) {
            int tmp = x % 10;
            x /= 10;
            check = check * 10 + tmp;
            if (check > INT_MAX || check < INT_MIN) return 0;
            res = res * 10 + tmp;
        }
        return res;
    }
};

int main()
{
    Solution* s = new Solution();
    int x[5] = {-123, 100, -1230, 1230, -345};
    for (int i = 0; i < 5 ; ++i) {
        int a = s->reverse(x[i]);
        int b = s->reverse2(x[i]);
        printf("origin: %d\treverse(): %d\treverse2(): %d\n", x[i], a, b);
    }

    return 0;
}