from typing import List

class Solution:
    def minSumSquareDiff(
        self,
        nums1: List[int],
        nums2: List[int],
        k1: int,
        k2: int
    ) -> int:
        k = k1 + k2
        freq = [0] * 100001
        diff_sum = 0
        max_diff = 0
        for n1, n2 in zip(nums1, nums2):
            diff = abs(n1 - n2)
            if diff > 0:
                freq[diff] += 1
                diff_sum += diff
                max_diff = max(max_diff, diff)
        if diff_sum <= k:
            return 0
        for d in range(max_diff, 0, -1):
            if k == 0:
                break
            take = min(freq[d], k)
            freq[d] -= take
            freq[d - 1] += take
            k -= take
        result = 0
        for d in range(1, max_diff + 1):
            result += freq[d] * d * d
        return result