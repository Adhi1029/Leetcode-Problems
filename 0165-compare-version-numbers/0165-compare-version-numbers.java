class Solution {
    public int compareVersion(String version1, String version2) {
        int version1Length = version1.length();
        int version2Length = version2.length();
        int index1 = 0;
        int index2 = 0;
        while (index1 < version1Length || index2 < version2Length) {
            int revision1 = 0;
            while (index1 < version1Length && version1.charAt(index1) != '.') {
                revision1 = revision1 * 10 + (version1.charAt(index1) - '0');
                index1++;
            }
            int revision2 = 0;
            while (index2 < version2Length && version2.charAt(index2) != '.') {
                revision2 = revision2 * 10 + (version2.charAt(index2) - '0');
                index2++;
            }
            if (revision1 != revision2) {
                return revision1 < revision2 ? -1 : 1;
            }
            index1++;
            index2++;
        }
        return 0;
    }
}