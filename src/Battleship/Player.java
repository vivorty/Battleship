package Battleship;

import java.util.Scanner;

public class Player {
    private String name;
    private Board board;
    private int score;

    public Player(String name, Board board) {
        this.name = name;
        this.board = board;
        this.score = 0;
    }

    public boolean takeTurn(Board board) {
    Scanner s = new Scanner(System.in);
    System.out.println(getName() + ", it's your turn."); //messages with instructions
    System.out.print("What ia your move (row and column, separated by a comma, like 1 1): ");
    int row = s.nextInt();
    int col = s.nextInt();

    Square square = board.getSquare(row, col); // gets square based on the player's coordinates

    if (square.hasShot()) { //cheks if player already used those cordinates
        System.out.println("ou've already used these coordiantes in the past.");
        return false; // Player's turn continues
    }
    square.setHasShot(true); // Marks the square as shot
    if (square.hasShip()) { //cheks if sqaure has a ship on it
        Battleship battleship = square.getBattleship();
        battleship.takeHit(); //registers a hit on the ship
        square.setSymbol('x'); //changes the symbol to x
            if (battleship.isSunk()) { //cheks if ship has sunk
            System.out.println("Yes! You destroyed and sank a ship!");
            setScore(getScore() + 1); //increases players score
            if (board.allSunk()) { //checks if all ships are destroyed, if true the player won
                System.out.println(getName() + " won the battle");
                return true; // Player has won
            }
        } else {
            System.out.println("Damage");
        }
    } else {
        System.out.println("Missed it");
        square.setSymbol('o');
    }
    
    return false; // Player's turn goes on 
    }




    // Getters for Player attributes
    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
}
