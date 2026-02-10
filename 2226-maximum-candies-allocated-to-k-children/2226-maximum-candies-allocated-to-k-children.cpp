class Solution {
public:
    int maximumCandies(vector<int>& candies, long long k) {
        long long totalCandies = 0;
        for (int candy : candies) {
            totalCandies += candy;
        }
        if (totalCandies < k) return 0;
        long long left = 1;
        long long right = totalCandies / k;
        int ans = 0;
        while (left <= right) {
            long long mid = left + (right - left) / 2;
            if (mid == 0) {
                left = 1;
                continue;
            }
            if (canDistribute(candies, k, mid)) {
                ans = mid;
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return ans;
    }
private:
    bool canDistribute(const vector<int>& candies, long long k, long long pilesSize) {
        long long childrenFed = 0;
        for (int candyPile : candies) {
            childrenFed += candyPile / pilesSize;
            if (childrenFed >= k) return true;
        }
        return childrenFed >= k;
    }
};