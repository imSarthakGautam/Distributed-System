// C++ program to illustrate the Lamport's
// Logical Clock
#include <iostream>
#include <vector>
#include <cstdlib>
using namespace std;

int max1(int a, int b) {
    return (a > b) ? a : b;
}

void display(int e1, int e2, vector<int>& p1, vector<int>& p2) {
    cout << "\nThe timestamps of events in P1:\n";
    for (int i = 0; i < e1; i++) {
        cout << p1[i] << " ";
    }
    cout << "\nThe timestamps of events in P2:\n";
    for (int i = 0; i < e2; i++) {
        cout << p2[i] << " ";
    }
    cout << endl;
}

void lamportLogicalClock(int e1, int e2, vector<vector<int>>& m) {
    vector<int> p1(e1), p2(e2);

    // Initializing logical clocks
    for (int i = 0; i < e1; i++) p1[i] = i + 1;
    for (int i = 0; i < e2; i++) p2[i] = i + 1;

    // Updating logical clocks based on message matrix
    for (int i = 0; i < e1; i++) {
        for (int j = 0; j < e2; j++) {
            if (m[i][j] == 1) {
                p2[j] = max1(p2[j], p1[i] + 1);
                for (int k = j + 1; k < e2; k++) p2[k] = p2[k - 1] + 1;
            }
            if (m[i][j] == -1) {
                p1[i] = max1(p1[i], p2[j] + 1);
                for (int k = i + 1; k < e1; k++) p1[k] = p1[k - 1] + 1;
            }
        }
    }

    display(e1, e2, p1, p2);
}

int main() {
    int e1, e2;
    cout << "Enter the number of events in P1: ";
    cin >> e1;
    cout << "Enter the number of events in P2: ";
    cin >> e2;

    vector<vector<int>> m(e1, vector<int>(e2));

    cout << "Enter the message matrix (1 for send, -1 for receive, 0 for no message):\n";
    for (int i = 0; i < e1; i++) {
        for (int j = 0; j < e2; j++) {
            cout << "m[" << i << "][" << j << "]: ";
            cin >> m[i][j];
        }
    }

    lamportLogicalClock(e1, e2, m);

    return 0;
}
