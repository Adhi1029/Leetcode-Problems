class Solution {
    public int minSizeSubarray(int[] nums, int target) {
        long total = Arrays.stream(nums).sum(), sum = 0;
        int n = nums.length, partial = n, complete = n * (int)(target / total), start = 0;
        target %= total;
        for (int end = 0; end < 2 * n; end++) {
            sum += nums[end % n];
            while (sum > target) {
                sum -= nums[start++ % n];
            }
            if (sum == target) {
                partial = Math.min(partial, end - start + 1);
            }
        }
        return partial != n ? partial + complete : -1;
    }
}