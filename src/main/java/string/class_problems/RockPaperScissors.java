package string.class_problems;
import java.util.Random;

public class RockPaperScissors {
    private static final String[] MOVES = {"Rock", "Paper", "Scissors"};
    private static final Random random = new Random();

    public static String playRound(String playerMove, String computerMove) {
        if (playerMove.equalsIgnoreCase(computerMove)) {
            return "Draw";
        }
        if ((playerMove.equalsIgnoreCase("Rock") && computerMove.equalsIgnoreCase("Scissors")) ||
            (playerMove.equalsIgnoreCase("Paper") && computerMove.equalsIgnoreCase("Rock")) ||
            (playerMove.equalsIgnoreCase("Scissors") && computerMove.equalsIgnoreCase("Paper"))) {
            return "Player Wins";
        }
        return "Computer Wins";
    }

    public static void main(String[] args) {
        String[] samplePlayerMoves = {"Rock", "Paper", "Scissors", "Rock", "Paper"};
        int wins = 0, losses = 0, draws = 0;

        System.out.printf("%-8s | %-12s | %-13s | %-13s%n", "Round", "Player Move", "Computer Move", "Result");
        System.out.println("---------------------------------------------------------");

        for (int i = 0; i < samplePlayerMoves.length; i++) {
            String playerMove = samplePlayerMoves[i];
            String computerMove = MOVES[random.nextInt(3)];
            String result = playRound(playerMove, computerMove);

            if (result.equals("Player Wins")) wins++;
            else if (result.equals("Computer Wins")) losses++;
            else draws++;

            System.out.printf("%-8d | %-12s | %-13s | %-13s%n", (i + 1), playerMove, computerMove, result);
        }

        double winPercentage = ((double) wins / samplePlayerMoves.length) * 100;
        System.out.println("---------------------------------------------------------");
        System.out.printf("Final Summary: Wins: %d | Losses: %d | Draws: %d | Win %% = %.1f%%%n", 
                          wins, losses, draws, winPercentage);
    }
}
