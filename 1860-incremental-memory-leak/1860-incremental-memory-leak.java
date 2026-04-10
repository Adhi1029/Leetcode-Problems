class Solution {
    public int[] memLeak(int memory1, int memory2) {
        int currentSecond = 1;
        while (currentSecond <= Math.max(memory1, memory2)) {
            if (memory1 >= memory2) {
                memory1 -= currentSecond;
            } else {
                memory2 -= currentSecond;
            }
            currentSecond++;
        }
        return new int[] {currentSecond, memory1, memory2};
    }
}