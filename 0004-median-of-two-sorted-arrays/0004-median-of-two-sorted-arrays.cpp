class Solution {
public:
    double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {
        vector<int>res;
        for(int i=0;i<nums1.size();i++){
            res.push_back(nums1[i]);
        }
        for(int i=0;i<nums2.size();i++){
            res.push_back(nums2[i]);
        }
        sort(res.begin(),res.end());
        float med = res.size()/2 ;
        if(res.size() %2 != 0){
            return res[med];
        }else{
            med = (res[res.size()/2] +res[res.size()/2-1])/2.0;
        }
        return med;
    }
};