class Solution {
    public boolean isSubstringPresent(String s) {
        boolean[][] reversedPairs = new boolean[26][26];
        int stringLength = s.length();
        for (int i = 0; i < stringLength - 1; ++i) {
            int currentChar = s.charAt(i) - 'a';
            int nextChar = s.charAt(i + 1) - 'a';
            reversedPairs[nextChar][currentChar] = true;
        }
        for (int i = 0; i < stringLength - 1; ++i) {
            int currentChar = s.charAt(i) - 'a';
            int nextChar = s.charAt(i + 1) - 'a';
            if (reversedPairs[currentChar][nextChar]) return true;
        }
        return false;
    }
}