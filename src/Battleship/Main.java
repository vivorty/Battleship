package Battleship;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        // Create a a board that has 10 rows and 10 columns
        Board board = new Board(10, 10);
        System.out.println("This is a Battleship game!");
        // Asks for the name for player 1
        System.out.print("What i your name player 1: ");
        String playerName1 = s.nextLine();
        Player player1 = new Player(playerName1, board);
        // Asks for the name for player 2
        System.out.print("What is your name player 2 ");
        String playerName2 = s.nextLine();
        Player player2 = new Player(playerName2, board);
        while (true) {
            // displays the current state of the board without any moves
            System.out.println(board.toString());
            // Player 1 takes a turn
            if (player1.takeTurn(board)) {
                player1.setScore(player1.getScore() + 1); // Update the player's score
                break; // Player 1 won, exit the game loop
            }
            // Display the current state of the game board, this time updated and with symbols
            System.out.println(board.toString());
            // Player 2 takes a turn 
            if (player2.takeTurn(board)) {
                player2.setScore(player2.getScore() + 1); // player's score is updated
                break; // Player 2 won, exit the game loop
            }
        }
        // Display the final scores
        System.out.println(player1.getName() + "'s Score: " + player1.getScore());
        System.out.println(player2.getName() + "'s Score: " + player2.getScore());

    }
}