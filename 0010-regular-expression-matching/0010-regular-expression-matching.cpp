class Solution {
public:
    bool isMatch(string s, string p) {
        int m=s.size(),n=p.size();
        int f[25][25];
        memset(f,0,sizeof f);
        function<bool(int,int)>dfs=[&](int i ,int j)->bool{
            if(j>=n){
                return i==m;
            }
            if(f[i][j]){
                return f[i][j]==1;
            }
            bool first_match = (i < m && (p[j] == s[i] || p[j] == '.'));
            bool res;
            if (j + 1 < n && p[j + 1] == '*') {
                res = dfs(i, j + 2) || (first_match && dfs(i + 1, j));
            }else {
                res = first_match && dfs(i + 1, j + 1);
            }
            return f[i][j] = res;
        };
        return dfs(0, 0);
    }
};