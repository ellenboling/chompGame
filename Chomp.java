// This class mimics the Chomps game, where there are two users breaking off blocks
// until one user is forced to take the remaining block, indicating they lost.

import java.util.*;

public class Chomp extends AbstractStrategyGame{
    public static final int layers = 3; 
    public static final int gameNotOver = -1;
    public static final int playerTwo = 2;
    public static final int playerOne = 1;
    private int[][] grid;
    private int player;

    //Constructor method for Chomp.
    //Takes in int row and int col for the users input regarding row/col.
    public Chomp (int row, int col){
        grid = new int[row][col];
        player = 1;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                grid[i][j] = layers; 
            }
        }
    }

    // Behavior: 
    //   - This method returns instructions for Chomp.
    // Returns:
    //   - Returns instructions.
    public String instructions(){
        String instructionString = "To play Chomp, enter a row and column to select where on"
        + "the grid you want to remove. All squares to the right and below your selected"
        + "coordinate will be chomped. Whichever user is forced to select the last remaining"
        + "square loses.";
        return instructionString;
    }


    // Behavior: 
    //   - This method shows what the current state of the game looks like.
    // Returns:
    //   - Returns what the grid looks like at that moment.
    public String toString(){
        String currentGameState = "";
        for(int i = 0; i < grid.length; i++){
            currentGameState += "|";
            for(int j = 0; j < grid[0].length; j++){
                currentGameState += " " + grid[i][j] + " |";
            }
            currentGameState += "\n";
        }
        return currentGameState;
    }


    // Behavior: 
    //   - This method gets the winner.
    // Returns:
    //   - Returns -1 if game isn't over, returns 1 if player 1 won,
    //     and returns 2 if player 2 won.
    public int getWinner(){
        if(!isGameOver()){
            return gameNotOver;
        }else{
            if(player != playerTwo){
                return playerOne;
            }else{
                return playerTwo;
            }
        }
    }


    // Behavior: 
    //   - This method gets the next player.
    // Returns:
    //   - Returns -1 if game is not over, otherwise returns player.
    public int getNextPlayer(){
        if(isGameOver()){
            return gameNotOver;
        }else{
            return player;
        }
    }


    // Behavior: 
    //   - This method allows users to make moves. 
    // Parameters:
    //   - Scanner: for user input.
    // Exceptions:
    //   - if input is null, we throw an IllegalArgumentException. 
    //   - if the row/col is outside grid length or is less than zero, 
    //     we throw an IllegalArgumentException.
    public void makeMove(Scanner input){
        if (input == null) {
            throw new IllegalArgumentException();
        }
        int row = input.nextInt();
        int col = input.nextInt();
        isThisLegal(row, col);
        int tempNum = grid[row][col];
    
        for(int i = row; i < grid.length; i++){
            for(int j = col; j < grid[0].length; j++){

                if(tempNum == grid[i][j]){
                    grid[i][j]--;
                }
            }
        }
        if(player == playerOne) {
            player = playerTwo;
        } else {
            player = playerOne;
        }
    }

    // Behavior: 
    //   - This method checks whether or not the users input of row and column are valid.
    // Parameters:
    //   - int row and col to access users input regarding row and col. 
    // Returns:
    //   - returns true if it is legal.
    // Exceptions:
    //   - if the row/col is outside grid length or is less than zero, 
    //     we throw an IllegalArgumentException.
    private boolean isThisLegal(int row, int col){
        if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length){
            throw new IllegalArgumentException();
        }
        return true;
    }

    // Behavior: 
    //   - This method checks whether or not the game is over.
    // Returns:
    //   - returns true if top leftmost block is chomped, otherwise false. 
    public boolean isGameOver() {
        if (grid[0][0] != layers){
            return true;
        }
        return false;
    }
}
