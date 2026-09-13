class Solution:
    def largestOverlap(self, A: List[List[int]], B: List[List[int]]) -> int:
        A_points, B_points, d = [], [], collections.defaultdict(int)
        for r in range(len(A)):
            for c in range(len(A[0])):
                if A[r][c]:
                    A_points.append((r, c))

                if B[r][c]:
                    B_points.append((r, c))
        for r_a, c_a in A_points:
            for r_b, c_b in B_points:
                d[(r_b - r_a, c_b - c_a)] += 1
        return max(d.values() or [0])