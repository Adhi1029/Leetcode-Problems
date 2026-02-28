class Solution {
    public int minNumberOfSemesters(int n, int[][] relations, int k) {
        int[] prerequisites = new int[n + 1];
        for (int[] relation : relations) {
            int prerequisite = relation[0];
            int course = relation[1];
            prerequisites[course] |= 1 << prerequisite;
        }
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {0, 0});
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        while (!queue.isEmpty()) {
            int[] current = queue.pollFirst();
            int coursesTaken = current[0];
            int semesters = current[1];
            if (coursesTaken == (1 << (n + 1)) - 2) {
                return semesters;
            }
            int availableCourses = 0;
            for (int course = 1; course <= n; ++course) {
                if ((coursesTaken & prerequisites[course]) == prerequisites[course]) {
                    availableCourses |= 1 << course;
                }
            }
            availableCourses ^= coursesTaken;
            if (Integer.bitCount(availableCourses) <= k) {
                int nextState = availableCourses | coursesTaken;
                if (visited.add(nextState)) {
                    queue.offer(new int[] {nextState, semesters + 1});
                }
            } else {
                int subset = availableCourses;
                while (subset > 0) {
                    if (Integer.bitCount(subset) == k) {
                        int nextState = subset | coursesTaken;
                        if (visited.add(nextState)) {
                            queue.offer(new int[] {nextState, semesters + 1});
                        }
                    }
                    subset = (subset - 1) & availableCourses;
                }
            }
        }
        return 0;
    }
}