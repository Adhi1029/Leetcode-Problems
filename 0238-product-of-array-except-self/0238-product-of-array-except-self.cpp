class Solution {
public:
    vector<int> productExceptSelf(vector<int>& nums) {
        vector<int>res;
        int total = 1;
        int cnt =0;
        for(int i=0;i<nums.size();i++){ 
            if(nums[i]==0){ 
                cnt++;
            }
            if(nums[i]!=0){
                total *=nums[i];
            }
        }
        for(int i =0;i<nums.size();i++){
            if(cnt ==0){
                int curr =total/nums[i];
                res.push_back(curr);
            }else if(cnt ==1 ){
                if(nums[i] ==0){
                    res.push_back(total);
                }else res.push_back(0);
            }else res.push_back(0);
        }
        return res;
    }
};