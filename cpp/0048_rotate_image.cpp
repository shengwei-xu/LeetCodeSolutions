/**
 * 48. Rotate Image
    Medium

    You are given an n x n 2D matrix representing an image.

    Rotate the image by 90 degrees (clockwise).

    Note:

    You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. 
    DO NOT allocate another 2D matrix and do the rotation.

    Example 1:

    Given input matrix = 
    [
    [1,2,3],
    [4,5,6],
    [7,8,9]
    ],

    rotate the input matrix in-place such that it becomes:
    [
    [7,4,1],
    [8,5,2],
    [9,6,3]
    ]
    Example 2:

    Given input matrix =
    [
    [ 5, 1, 9,11],
    [ 2, 4, 8,10],
    [13, 3, 6, 7],
    [15,14,12,16]
    ], 

    rotate the input matrix in-place such that it becomes:
    [
    [15,13, 2, 5],
    [14, 3, 4, 1],
    [12, 6, 8, 9],
    [16, 7,10,11]
    ]
 * */

#include <iostream>
#include <vector>

using namespace std;

class Solution {
public:
    void rotate(vector<vector<int>>& matrix) {
        // First, left-right rotation
        for (int i = 0; i < matrix.size(); ++i) {
            for (int j = 0; j < matrix.size() / 2; ++j) {
                swap(matrix[i][j], matrix[i][matrix.size() - j - 1]);
            }
        }
        // Second, auxiliary diagonal rotation
        for (int i = 0; i < matrix.size(); ++i) {
            for (int j = 0; i + j < matrix.size(); ++j) {
                swap(matrix[i][j], matrix[matrix.size() - j - 1][matrix.size() - i - 1]);
            }
        }
        show_matrix(matrix);
    }
    
    void rotate_2(vector<vector<int>>& matrix) {
        // Transpose the matrix initially, and then reverse rows of the matrix.
        for (int i = 0; i < matrix.size(); ++i) {
            for (int j = i; j < matrix.size(); ++j) {
                swap(matrix[i][j], matrix[j][i]);
            }
        }
        for (int i = 0; i < matrix.size(); ++i) {
            reverse(matrix[i].begin(), matrix[i].end());
        }
        show_matrix(matrix);
    }

    void rotate_3(vector<vector<int>>& matrix) {
        for(int i = 0; i < matrix.size(); i++) {
            for(int j = matrix.size()-1; j >= 0; j--) {
                matrix[i].push_back(matrix[j][i]);
            }
        }
        for(int i = 0; i < matrix.size(); i++) {
            matrix[i].erase(matrix[i].begin(),matrix[i].begin() + matrix.size());
        }
        show_matrix(matrix);
    }

    void show_matrix(vector<vector<int>>& matrix) {
        for (int i = 0; i < matrix.size(); ++i) {
            for (int j = 0; j < matrix.size(); ++j) {
                cout << matrix[i][j] << " ";
            }
            cout << endl;
        }
    }
};

int main() {
    Solution * s = new Solution();
    vector<vector<int>> data = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};

    // s->rotate(data);
    // s->rotate_2(data);
    s->rotate_3(data);
    return 0;
}

