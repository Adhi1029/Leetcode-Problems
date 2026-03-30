class Solution {
    public int sumOfGoodNumbers(int[] nums, int k) {
        int sum = 0;
        int arrayLength = nums.length;
        for (int i = 0; i < arrayLength; i++) {
            if (i >= k && nums[i] <= nums[i - k] ) continue;
            if (i + k < arrayLength && nums[i] <= nums[i + k]) continue;
            sum += nums[i];
        }
        return sum;
    }
}