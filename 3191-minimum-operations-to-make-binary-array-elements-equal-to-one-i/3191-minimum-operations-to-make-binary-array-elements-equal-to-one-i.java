class Solution {
    public int minOperations(int[] nums) {
        int operationCount = 0;
        int arrayLength = nums.length;
        for (int i = 0; i < arrayLength; i++) {
            if (nums[i] == 0) {
                if (i + 2 >= arrayLength) return-1;
                nums[i + 1] ^= 1;
                nums[i + 2] ^= 1;
                operationCount++;
            }
        }
        return operationCount;
    }
}