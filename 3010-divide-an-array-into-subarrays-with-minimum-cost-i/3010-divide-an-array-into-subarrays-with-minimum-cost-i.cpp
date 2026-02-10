class Solution {
public:
    int minimumCost(vector<int>& nums) {
        nth_element(begin(nums) + 1, (begin(nums) + 1) + (2 - 1), end(nums));
        return nums[0]+nums[1]+nums[2];
    }
};
class Solution2{
public:
    int minimumCost(vector<int>&nums) {
        vector<int>tep2(2,numeric_limits<int>::max());
        for(int i=1;i<size(nums);i++){
            for(auto&x:tep2){
                if(nums[i]<x){
                    swap(x,nums[i]);
                }
            }
        }
        return nums[0] + tep2[0] + tep2[1];
    }   
};