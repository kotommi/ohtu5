package ohtu;

import java.util.HashMap;

public class TennisGame {

    private int player1Score = 0;
    private int player2Score = 0;
    private String player1Name;
    private String player2Name;
    private HashMap<Integer, String> scoreNames;
    private final int LOVE = 0;
    private final int FIFTEEN = 1;
    private final int THIRTY = 2;
    private final int FORTY = 3;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        initScoreNames();
    }

    private void initScoreNames() {
        this.scoreNames = new HashMap<>();
        scoreNames.put(LOVE, "Love");
        scoreNames.put(FIFTEEN, "Fifteen");
        scoreNames.put(THIRTY, "Thirty");
        scoreNames.put(FORTY, "Forty");
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name))
            player1Score += 1;
        else
            player2Score += 1;
    }

    public String getEvenScore() {
        String score = scoreNames.getOrDefault(player1Score, "Deuce");
        if (score.equals("Deuce")) {
            return score;
        } else return score + "-All";
    }

    public String getScore() {
        if (player1Score == player2Score) {
            return getEvenScore();
        } else if (player1Score > FORTY || player2Score > FORTY) {
            return getAdvantageOrWin();
        } else {
            return getNormalScore();
        }
    }

    private String getNormalScore() {
        return scoreNames.get(player1Score) + "-" + scoreNames.get(player2Score);
    }

    private String getAdvantageOrWin() {
        String score;
        int scoreDifference = player1Score - player2Score;
        if (Math.abs(scoreDifference) == 1) {
            score = "Advantage ";
            return scoreDifference > 0 ? score + player1Name : score + player2Name;
        }
        score = "Win for ";
        return scoreDifference > 0 ? score + this.player1Name : score + this.player2Name;
    }
}