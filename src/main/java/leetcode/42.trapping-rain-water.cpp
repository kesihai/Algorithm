/*
 * @lc app=leetcode id=42 lang=cpp
 *
 * [42] Trapping Rain Water
 */

// @lc code=start

// class Solution {
// public:
//     int trap(vector<int>& height) {
//       if (height.size() == 0) return 0;
//        vector<int> le;
//        le.push_back(height[0]);
//        int n = height.size();
//        for (int i = 1; i < n; i++) {
//          le.push_back(max(le[i - 1], height[i]));
//        }
//        vector<int> ri;
//        ri.push_back(height[n - 1]);
//        for (int i = 1; i < n; i++) {
//          ri.push_back(max(ri[i - 1], height[n - 1 - i]));
//        }
//       reverse(ri.begin(), ri.end());
//       int ans = 0;
//       for (int i = 1; i < n - 1; i++) {
//         ans += max(0, min(le[i - 1], ri[i + 1]) - height[i]);
//       }
//       return ans;
//     }
// };

struct Node {
    int le, ri;
    int ma;
}f[3 * 10000 * 4];

class Solution {
public:
    vector<int> height;
    int trap(vector<int>& height) {
        if (height.size() == 0) return 0;
       this -> height = height; 
       build(1, 0, height.size() - 1);
       int ans = 0;
       for (int i = 1; i < height.size() - 1; i++) {
           int le = query(1, 0, i - 1);
           int ri = query(1, i + 1, height.size() - 1);
           if (le <= height[i] || ri <= height[i]) continue;
           ans += min(le - height[i], ri - height[i]);
       }
       return ans;
    }

    void pushUp(int pos) {
        f[pos].ma = max(f[pos * 2].ma, f[pos * 2 + 1].ma);
    }

    void build (int pos, int le, int ri) {
        f[pos].le = le;
        f[pos].ri = ri;
        if (le == ri) {
            f[pos].ma = height[le];
            return;
        }
        int mid  = (le + ri) >> 1;
        build(pos * 2, le, mid);
        build(pos * 2 + 1, mid + 1, ri);
        pushUp(pos);
    }

    int query(int pos, int le, int ri) {
        if (le <= f[pos].le && ri >= f[pos].ri) {
            return f[pos].ma;
        }
        int mid = (f[pos].le + f[pos].ri) >> 1;
        if (mid >= ri) {
            return query(pos * 2, le, ri);
        } else if (mid < le) {
            return query(pos * 2 + 1, le, ri);
        } else {
            return max(query(pos, le, mid), query(pos, mid + 1, ri));
        }
    }
};
// @lc code=end

