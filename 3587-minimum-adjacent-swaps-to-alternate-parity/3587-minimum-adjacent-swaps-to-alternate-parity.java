class Solution {
    private List<Integer>[] positionsByParity = new List[2];
    private int[] nums;
    public int minSwaps(int[] nums) {
        this.nums = nums;
        Arrays.setAll(positionsByParity, index -> new ArrayList<>());
        for (int i = 0; i < nums.length; ++i) {
            int parity = nums[i] & 1;
            positionsByParity[parity].add(i);
        }
        int evenCount = positionsByParity[0].size();
        int oddCount = positionsByParity[1].size();
        if (Math.abs(evenCount - oddCount) > 1) {
            return -1;
        }
        if (evenCount > oddCount) {
            return calculateSwaps(0); 
        }
        if (evenCount < oddCount) {
            return calculateSwaps(1);
        }
        return Math.min(calculateSwaps(0), calculateSwaps(1));
    }
    private int calculateSwaps(int startingParity) {
        int totalSwaps = 0;
        for (int targetPosition = 0; targetPosition < nums.length; targetPosition += 2) {
            int elementIndex = targetPosition / 2;
            int currentPosition = positionsByParity[startingParity].get(elementIndex);
            totalSwaps += Math.abs(currentPosition - targetPosition);
        }
        return totalSwaps;
    }
}