class Solution {
    public int[] singleNumber(int[] nums) {
        int xorOfTwoUniques = 0;
        for (int num : nums) xorOfTwoUniques ^= num;
        int differentiatingBit = xorOfTwoUniques & -xorOfTwoUniques;
        int firstUniqueNum = 0;
        for (int num : nums) {
            if ((num & differentiatingBit) != 0) firstUniqueNum ^= num;
        }
        int secondUniqueNum = xorOfTwoUniques ^ firstUniqueNum;
        return new int[] {firstUniqueNum, secondUniqueNum};
    }
}