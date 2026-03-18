class Solution {
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);
        int numPlayers = players.length;
        int numTrainers = trainers.length;
        int playerIndex = 0;
        int trainerIndex = 0;
        while (playerIndex < numPlayers && trainerIndex < numTrainers) {
            if (trainers[trainerIndex] >= players[playerIndex]) playerIndex++;
            trainerIndex++;
        }
        return playerIndex;
    }
}