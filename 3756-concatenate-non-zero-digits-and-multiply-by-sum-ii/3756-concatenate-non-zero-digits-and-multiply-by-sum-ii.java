class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {
        final int MOD = 1_000_000_007;
        int n = s.length();

        int[] idx = new int[n + 1];
        long[] val = new long[n + 1];
        long[] total = new long[n + 1];
        long[] pow10 = new long[n + 1];

        pow10[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow10[i] = pow10[i - 1] * 10 % MOD;
        }

        int count = 0;

        for (int i = 0; i < n; i++) {
            int digit = s.charAt(i) - '0';

            if (digit != 0) {
                count++;
                val[count] = (val[count - 1] * 10 + digit) % MOD;
                total[count] = total[count - 1] + digit;
            }

            idx[i + 1] = count;
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int left = queries[i][0];
            int right = queries[i][1];

            int a = idx[left];
            int b = idx[right + 1];

            if (a == b) {
                ans[i] = 0;
                continue;
            }

            int length = b - a;

            long num = (val[b] - val[a] * pow10[length]) % MOD;
            if (num < 0) num += MOD;

            long digitSum = total[b] - total[a];

            ans[i] = (int)(num * digitSum % MOD);
        }

        return ans;
    }
}