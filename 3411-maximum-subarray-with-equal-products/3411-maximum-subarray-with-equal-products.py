class Solution:
    def maxLength(self, nums: List[int]) -> int:
        n, ans = len(nums), 0
        for l in range(n):
            g = lc = nums[l]
            inv = 1.0 / nums[l]
            for r in range(l, n):
                if r > l:
                    g = gcd(g, nums[r])
                    lc = lcm(lc, nums[r])
                    inv /= nums[r]
                if g * lc * inv > 0.99999999:
                    ans = max(ans, r - l + 1)
        return ans