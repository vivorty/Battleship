package Battleship;

import java.util.Random;

import java.util.Random;

public class Board {
    private int rows;
    private int cols;
    private Square[][] grid;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        grid = new Square[rows][cols];
        populate(); //method added here so that programme calls it whenewer it is used
        generateVariousShips();
    }

    public void populate() { //method that popultes the board 
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Square(i, j);
            }
        }
    }

    public void addRandomShip(Battleship battleship) { //mthod that makes sure that ships do not overlap or go out of bounds and chooses random coordinates
        Random random = new Random();
        int shipSize = battleship.getSize(); // Get the size of the battleship
        while (true) {
            int row = random.nextInt(rows);
            int col = random.nextInt(cols);
            boolean isHorizontal = random.nextBoolean();
            if (validPlacement(row, col, shipSize, isHorizontal)) {
                // Place the battleship on the board
                for (int i = 0; i < shipSize; i++) {
                    if (isHorizontal) {
                        grid[row][col + i].setHasShip(true);
                        grid[row][col + i].setBattleship(battleship);
                    } else {
                        grid[row + i][col].setHasShip(true);
                        grid[row + i][col].setBattleship(battleship);
                    }
                }
                break;
            }
        }
    }

    public Square getSquare(int row, int col) {
        return grid[row][col];
    }

    public void generateVariousShips() { //method that uses loops to places different ship sizes and quantities on the board
    // Loop that loops through the number of small battleships 
        for (int i = 0; i < SmallBattleship.totalSmallBattleships; i++) {
            addRandomShip(new SmallBattleship());
        }
    // loop for medium battleships 
        for (int i = 0; i < MediumBattleship.totalMediumBattleships; i++) {
            addRandomShip(new MediumBattleship());
        }
    // loop for large battleships
        for (int i = 0; i < LargeBattleship.totalLargeBattleships; i++) {
            addRandomShip(new LargeBattleship());
        }
    }

    public boolean validPlacement(int row, int col, int shipSize, boolean isHorizontal) {
        if (isHorizontal) {
            if (row + shipSize > cols) {
                return false; // Ship goes out of bounds horizontally
            }
            for (int i = 0; i < shipSize; i++) {
                if (grid[row][col + i].hasShip()) {
                    return false; // Overlapping with another ship
                }
            }
        } else {
            if (row + shipSize > rows) {
                return false; // ship is out of bounds vertically 
            }
            for (int i = 0; i < shipSize; i++) {
                if (grid[row + i][col].hasShip()) {
                    return false; // overlaps with another ship
                }
            }
        }

        return true; // Valid placement
    }


    public boolean allSunk() { //method that cheks if all ships are destroyed and determines if the game is over
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col].hasShip() && !grid[row][col].getBattleship().isSunk()) {
                    return false;
                }
            }
        }
        return true;
    }

    

    public void updatedBoard(Player player, int row, int col) { //updates the square status, and prints messages.
        Square square = grid[row][col]; // gets the square on the baord/grid
        if (!square.hasShot()) { //checks if the sqaure has been shot at before, if true it returns as shot
            square.setHasShot(true); //marks the square as shot, so player cannot use the same coordinates again
            if (square.hasShip()) {
                Battleship battleship = square.getBattleship(); //gets the battleship on the sqaure
                battleship.takeHit(); // registers a hit
                square.setSymbol('x'); //chnages the symbol on the grid
                if (battleship.isSunk()) { // all ship parts are hit and therfore ship has sunk
                    System.out.println(player.getName() + " destroyed a battleship!");
                    player.setScore(player.getScore() + 1); // player score increases
                    if (allSunk()) {
                        System.out.println("All battleships are destroyed. Nice" + player.getName() + " won the game!");
                        return; // Game is over, returns and exits the method
                    } else {
                        System.out.println("Damage!");
                    }
                } else {
                    System.out.println("Damage!");
                }
            } else {
                System.out.println("Missed it");
                square.setSymbol('o');
            }
        } else {
            System.out.println("You've already used these coordiantes");
        }
    }

    public void visualOfUpdatedBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(grid[i][j].toString());
            }
            System.out.println();
        }
    }

    @Override
    public String toString() {
        visualOfUpdatedBoard();
        return "";
    }
}
