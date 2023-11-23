package Battleship;

public class Square {
    private int row;
    private int col;
    private boolean hasShip;
    private Battleship battleship;
    private boolean hasShot;
    private char symbol; // Added symbol to represent hit x or miss o

    public Square(int row, int col) {
        this.row = row;
        this.col = col;
        this.hasShip = false;
        this.battleship = null;
        this.hasShot = false;
        this.symbol = ' '; // Default symbol for unshot and unused squares
    }

    // Getters and setters for square class
    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean hasShip() {
        return hasShip;
    }

    public Battleship getBattleship() {
        return battleship;
    }

    public boolean hasShot() {
        return hasShot;
    }

    public void setHasShot(boolean hasShot) {
        this.hasShot = hasShot;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public void setHasShip(boolean hasShip) {
        this.hasShip = hasShip;
    }

    public void setBattleship(Battleship battleship) {
        this.battleship = battleship;
    }

    @Override
    public String toString() { 
        if (hasShot) {  // checks if square has been shot
            if (hasShip) {
                return " x "; //chnages the box to x if ship has been hit and is in that location
            } else {
                return " o "; // if fired and no ship 
            }
        } else {
            return " - "; // if player has not shot at the square
        }
    }
}
