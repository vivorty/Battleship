package Battleship;

public class Battleship {
    private boolean isSunk;
    private int health;
    private int size;

    public Battleship(int size) {
        this.isSunk = false;
        this.size = 2; // Each battleship is 2 squares in size
        this.health = size;
    }

    public boolean isSunk() { // ,ethod that checks if the battleship is sunk
        return isSunk;
    }

    public int getHealth() {
        return health;
    }

    public void takeHit() { //method to check if battleship health is 0 or reduced
        health--;
        if (health == 0) {
            isSunk = true; // if 0 then ship has been destroyed
        }
    }

    public int getSize() {
        return size;
    }
}
