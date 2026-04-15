class Solution {
    public int closestTarget(String[] words, String target, int startIndex) {
        int arrayLength = words.length;
        int minDistance = arrayLength;
        for (int currentIndex = 0; currentIndex < arrayLength; ++currentIndex) {
            String currentWord = words[currentIndex];
            if (currentWord.equals(target)) {
                int directDistance = Math.abs(currentIndex - startIndex);
                int wrapAroundDistance = arrayLength - directDistance;
                int shortestPath = Math.min(directDistance, wrapAroundDistance);
                minDistance = Math.min(minDistance, shortestPath);
            }
        }
        return minDistance == arrayLength ? -1 : minDistance;
    }
}