class Solution {
    public long countSubarrays(int[] nums, int k) {
        long totalCount = 0;
        Map<Integer, Integer> previousAndResults = new HashMap<>();
        for (int currentNum : nums) {
            Map<Integer, Integer> currentAndResults = new HashMap<>();
            for (Map.Entry<Integer, Integer> entry : previousAndResults.entrySet()) {
                int previousAndValue = entry.getKey();
                int count = entry.getValue();
                int newAndValue = currentNum & previousAndValue;
                currentAndResults.merge(newAndValue, count, Integer::sum);
            }
            currentAndResults.merge(currentNum, 1, Integer::sum);
            totalCount += currentAndResults.getOrDefault(k, 0);
            previousAndResults = currentAndResults;
        }
        return totalCount;
    }
}