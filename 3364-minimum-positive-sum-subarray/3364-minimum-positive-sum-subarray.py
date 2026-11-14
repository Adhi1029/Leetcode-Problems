class Solution:
    def minimumSumSubarray(self, nums: List[int], l: int, r: int) -> int:
        n = len(nums)
        ans = float('inf')
        for start in range(n - l + 1):
            currSum = 0
            for size in range(1, min(r, n - start) + 1):
                currSum += nums[start + size - 1]
                if size >= l and currSum > 0:
                    ans = min(ans, currSum)
        return -1 if ans == float('inf') else ans