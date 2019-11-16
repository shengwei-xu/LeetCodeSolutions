#include <iostream>
#include <vector>
#include <cmath>
using namespace std;


const int N = 8; // number of queens
int table[N][N] = {0}; // chess table
int queen[N]; // queens' row position
int solutions = 0; // number of solutions

void init() {
    for (int i = 0; i < N; ++i) {
        queen[i] = -1;
    }
}

int check(int x, int y) { // check whether to put a queen in (x, y)
    int judge = 1;
    for (int i = 0; i < N; ++i) {
        if (queen[i] != -1) {
            if (x == i || y == queen[i] || 
                (x - i) == (y - queen[i]) || 
                (x - i) == -(y - queen[i])) {
                judge = 0;
                break;
            }
        }
    }
    return judge;
}

void dfs(int x) { // x means the queen's row number 
    if (x > N - 1) { // when x greater than N, it will be a solution here
        solutions ++;
        return;
    }
    for (int i = 0; i < N; ++i) {
        if (check(x, i)) {
            queen[x] = i; // mark queen position
            dfs(x + 1); // go deeper
            queen[x] = -1; // unmark queen position when back to this layer
        }
    }
}

int main() {

    init();
    dfs(0); // start with #0 row
    cout << solutions << endl;

    return 0;
}
