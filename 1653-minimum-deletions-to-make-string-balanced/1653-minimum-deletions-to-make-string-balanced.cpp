class Solution {
public:
    int minimumDeletions(string s) {
        int stringLength = s.size();
        vector<int> dp(stringLength + 1, 0);
        int bCount = 0;
        for (int i = 1; i <= stringLength; ++i) {
            if (s[i - 1] == 'b') {
                dp[i] = dp[i - 1];
                bCount++;
            }else {
                dp[i] = min(dp[i - 1] + 1, bCount);
            }
        }
        return dp[stringLength];
    }
};