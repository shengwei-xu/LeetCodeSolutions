/**
 * 0242 - Valid Anagram
 * Easy 
 * TAG: sort | hash table
 * 
 * Given two strings s and t , write a function to determine if t is an anagram of s.

    Example 1:

    Input: s = "anagram", t = "nagaram"
    Output: true
    Example 2:

    Input: s = "rat", t = "car"
    Output: false
    Note:
    You may assume the string contains only lowercase alphabets.

    Follow up:
    What if the inputs contain unicode characters? How would you adapt your solution to such case?
 * 
 **/

#include <iostream>
#include <string>
#include <algorithm>
using namespace std;

class Solution {
    public:
        // O(nlogn)
        bool isAnagram(string s, string t) {
            if (s.length() != t.length()) return false;

            for (int i = 0; i < s.length(); ++i) {
                if (s[i] >= 'A' && s[i] <= 'Z')
                    s[i] += 'a' - 'A';
                if (t[i] >= 'A' && t[i] <= 'Z')
                    t[i] += 'a' - 'A';
            }

            sort(s.begin(), s.end());
            sort(t.begin(), t.end());
            
            for (int i = 0; i < s.length(); ++i) {
                if (s[i] != t[i])
                    return false;
            }
            return true;
        }
        // O(n)
        bool isAnagram_hashtable(string s, string t) {
            if (s.length() != t.length()) return false;

            for (int i = 0; i < s.length(); ++i) {
                if (s[i] >= 'A' && s[i] <= 'Z')
                    s[i] += 'a' - 'A';
                if (t[i] >= 'A' && t[i] <= 'Z')
                    t[i] += 'a' - 'A';
            }

            int counter[26] = {0};
            for (int i = 0; i < s.length(); ++i) {
                counter[s[i] - 'a'] ++;
                counter[t[i] - 'a'] --;
            }
            for (auto count : counter) {
                if (count != 0) return false;
            }
            return true;
        }

};

int main()
{
    Solution* a = new Solution();
    string s = "rat";
    string t = "car";
    cout << a->isAnagram(s, t) << endl;
    cout << a->isAnagram_hashtable(s, t) << endl;
    
    return 0;
}


