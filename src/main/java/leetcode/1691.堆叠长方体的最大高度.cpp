#include<bits/stdc++.h>
using namespace std;
/*
 * @lc app=leetcode.cn id=1691 lang=cpp
 *
 * [1691] 堆叠长方体的最大高度
 */

// @lc code=start

struct Node {
  Node(int x, int y, int z, int index) {
    this -> x = x;
    this -> y = y;
    this -> z = z;
    this -> index = index;
  }

  int x, y, z;
  int index;
  
  bool operator < (const Node& b) const {
    if (x != b.x) {
        return x < b.x;
    }
    if (y != b.y) {
        return y < b.y;
    }
    return z < b.z;
  }

  bool isOk(const Node& b) const {
    return x >= b.x && y >= b.y && z >= b.z && index != b.index;
  }
};

class Solution {
public:
    int maxHeight(vector<vector<int>>& cuboids) {
      vector<Node> v;
      for (int i = 0; i < cuboids.size(); i++) {
        v.push_back(Node(cuboids[i][0], cuboids[i][1], cuboids[i][2], i));
        v.push_back(Node(cuboids[i][0], cuboids[i][2], cuboids[i][1], i));
        v.push_back(Node(cuboids[i][1], cuboids[i][0], cuboids[i][2], i));
        v.push_back(Node(cuboids[i][1], cuboids[i][2], cuboids[i][0], i));
        v.push_back(Node(cuboids[i][2], cuboids[i][0], cuboids[i][1], i));
        v.push_back(Node(cuboids[i][2], cuboids[i][1], cuboids[i][0], i));
      }
      sort(v.begin(), v.end());
      vector<int> dp(v.size(), 0);
      int len = v.size();
      for (int i = 0; i < len; i++) {
        dp[i] = v[i].z;
        for (int j = 0; j < i; j++) {
          if (v[i].isOk(v[j])) {
            dp[i] = max(dp[i], dp[j] + v[i].z);
          }
        }
      }
      return *max_element(dp.begin(), dp.end());
    }
};


// @lc code=end

