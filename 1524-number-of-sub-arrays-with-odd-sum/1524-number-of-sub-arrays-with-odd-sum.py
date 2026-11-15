class Solution:
    def numOfSubarrays(self, arr: list[int]) -> int:
        p = int(bytearray(arr).translate(b'01' * 128), 2)
        s = 1
        while s < len(arr):
            p ^= p >> s
            s <<= 1
        odd = p.bit_count()
        return odd * (len(arr) + 1 - odd) % (10**9 + 7)