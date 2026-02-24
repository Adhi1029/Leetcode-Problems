class Solution {
    public String maskPII(String s) {
        if (Character.isLetter(s.charAt(0))) {
            s = s.toLowerCase();
            int atIndex = s.indexOf('@');
            return s.substring(0, 1) + "*****" + s.substring(atIndex - 1);
        }
        StringBuilder digitsBuilder = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                digitsBuilder.append(c);
            }
        }
        String digitsOnly = digitsBuilder.toString();
        int countryCodeLength = digitsOnly.length() - 10;
        String maskedSuffix = "***-***-" + digitsOnly.substring(digitsOnly.length() - 4);
        if (countryCodeLength == 0) {
            return maskedSuffix;
        } else {
            return "+" + "*".repeat(countryCodeLength) + "-" + maskedSuffix;
        }
    }
}