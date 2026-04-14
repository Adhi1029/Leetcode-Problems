class Solution {
public:
    int minimumDifference(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        int minDiff = 100000;
        for (int i = 0; i <= nums.size() - k; ++i) {
            int currentDiff = nums[i + k - 1] - nums[i];
            minDiff = min(minDiff, currentDiff);
        }
        return minDiff;
    }
};