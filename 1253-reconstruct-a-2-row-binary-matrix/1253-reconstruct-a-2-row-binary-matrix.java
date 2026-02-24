class Solution {
    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        int numColumns = colsum.length;
        List<Integer> upperRow = new ArrayList<>();
        List<Integer> lowerRow = new ArrayList<>();
        for (int col = 0; col < numColumns; col++) {
            int upperValue = 0;
            int lowerValue = 0;
            if (colsum[col] == 2) {
                upperValue = 1;
                lowerValue = 1;
                upper--;
                lower--;
            } else if (colsum[col] == 1) {
                if (upper > lower) {
                    upperValue = 1;
                    upper--;
                } else {
                    lowerValue = 1;
                    lower--;
                }
            }
            if (upper < 0 || lower < 0) {
                break;
            }
            upperRow.add(upperValue);
            lowerRow.add(lowerValue);
        }
        return (upper == 0 && lower == 0) ? List.of(upperRow, lowerRow) : List.of();
    }
}