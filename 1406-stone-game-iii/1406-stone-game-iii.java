class Solution {
    Integer[] dp;
    public String stoneGameIII(int[] s) {
        int n = s.length;
        int[] dp = new int[n+3];
        for(int i=n-1; i>=0; i--) {
            int diff = s[i] - dp[i+1];
            if(i+1<n) diff = Math.max(diff, s[i]+s[i+1]-dp[i+2]);
            if(i+2<n) diff = Math.max(diff, s[i]+s[i+1]+s[i+2]-dp[i+3]);
            dp[i] = diff;
        }
        
        if(dp[0]<0) return "Bob";
        else if(dp[0]>0) return "Alice";
        else return "Tie";
    }
    private int solve(int[] s, int i) {
        int n = s.length;
        if(i>=n) return 0;
        if(dp[i]!=null) return dp[i];
        int res = s[i] - solve(s, i+1);
        if(i+1<n) res = Math.max(res, s[i]+s[i+1]-solve(s, i+2));
        if(i+2<n) res = Math.max(res, s[i]+s[i+1]+s[i+2]-solve(s, i+3));
        return dp[i] = res;
    }
}