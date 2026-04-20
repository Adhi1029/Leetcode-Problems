class Solution {
    public int maxDistance(int[] colors) {
        int maxDist = 0;
        int arrayLength = colors.length;
        for (int leftIndex = 0; leftIndex < arrayLength; ++leftIndex) {
            for (int rightIndex = leftIndex + 1; rightIndex < arrayLength; ++rightIndex) {
                if (colors[leftIndex] != colors[rightIndex]) {
                    int currentDistance = Math.abs(leftIndex - rightIndex);
                    maxDist = Math.max(maxDist, currentDistance);
                }
            }
        }
        return maxDist;
    }
}