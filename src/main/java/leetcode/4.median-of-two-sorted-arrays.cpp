class Solution{
public:
  double findMedianSortedArrays(vector<int> &nums1, vector<int> &nums2)
  {
    int len = nums1.size() + nums2.size();
    if (len % 2)
    {
      return solve(nums1, nums2, len / 2 + 1);
    }
    return solve(nums1, nums2, len / 2, len / 2 + 1);
  }

  double solve(vector<int> &nums1, vector<int> &nums2, int pos) {
    int val = 0;
    int le = 0, ri = 0, cnt = 0;
    while (cnt != pos) {
      int p = getNext(nums1, nums2, le, ri);
      cnt++;
      val = p == 0 ? nums1[le++] : nums2[ri++];
    }
    return val;
  }

  double solve(vector<int> &nums1, vector<int> &nums2, int pos1, int pos2) {
    double sum = 0;
    int le = 0, ri = 0, cnt = 0;
    int n = 0;
    while (n != 2) {
      int p = getNext(nums1, nums2, le, ri);
      cnt++;
      int val = p == 0 ? nums1[le++] : nums2[ri++];
      if (cnt == pos1){ sum += val; n++;}
      if (cnt == pos2) { sum += val; n++;}
    }
    return sum / 2;
  }

private:
  int getNext(vector<int> &nums1, vector<int> &nums2, int le, int ri) {
    if (le == nums1.size()) {
      return 1;
    }
    if (ri == nums2.size()) {
      return 0;
    }
    if (nums1[le] <= nums2[ri]) {
      return 0;
    }
    return 1;
  }
};
