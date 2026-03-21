class Solution {
    public long minimumSteps(String s) {
        long totalSteps = 0;
        int onesCount = 0;
        int stringLength = s.length();
        for (int currentIndex = stringLength - 1; currentIndex >= 0; currentIndex--) {
            if (s.charAt(currentIndex) == '1') {
                onesCount++;
                totalSteps += (stringLength - currentIndex - onesCount);
            }
        }
        return totalSteps;
    }
}