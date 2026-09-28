class Solution(object):
    def maxDepth(self, s):
        count = 0
        res = 0
        for ch in s:
            if ch == '(':
                count += 1
            if ch == ')':
                count -= 1
            res = max(res, count)
        return res