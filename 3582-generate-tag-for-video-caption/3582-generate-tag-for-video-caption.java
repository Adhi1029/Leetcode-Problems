class Solution {
    public String generateTag(String caption) {
        String[] words = caption.trim().split("\\s+");
        StringBuilder hashtagBuilder = new StringBuilder("#");
        for (int i = 0; i < words.length; i++) {
            String currentWord = words[i];
            if (currentWord.isEmpty()) continue;
            currentWord = currentWord.toLowerCase();
            if (i == 0) hashtagBuilder.append(currentWord);
            else {
                hashtagBuilder.append(Character.toUpperCase(currentWord.charAt(0)));
                if (currentWord.length() > 1) hashtagBuilder.append(currentWord.substring(1));
            }
            if (hashtagBuilder.length() >= 100) break;
        }
        return hashtagBuilder.length() > 100 ? hashtagBuilder.substring(0, 100) : hashtagBuilder.toString();
    }
}