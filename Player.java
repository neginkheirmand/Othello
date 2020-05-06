package ir.ac.aut;

import ir.ac.aut.Place;
import ir.ac.aut.TYPE;

import java.util.ArrayList;


/**
 * This is a model class to hold player information
 *
 * @author negin
 * @version 1.0
 */

public class Player {

    //a protected field for rep
    protected final TYPE typeOfPlayer;

    //an array list to hold the places available for this player
    protected ArrayList<Place> movesAvailable;

    /**
     * a constructor
     * @param typeOfPlayer type of player
     */
    public Player(TYPE typeOfPlayer){
        this.typeOfPlayer=typeOfPlayer;
    }

    /**
     * a getter method for the movesAvailable field
     * @return movesAvailable
     */
    public ArrayList<Place> getMovesAvailable(){
        return movesAvailable;
    }

    /**
     * a getter method for the typeOfPlayer field
     * @return typeOfPlayer
     */
    public TYPE getTypeOfPlayer(){
        return typeOfPlayer;
    }

    /**
     * a method for updating the movesAvailable field and returning its size
     * @param gameBoard the game board
     * @return the size of movesAvailable field after being updated
     */
    public int nextMoveUpdate(Board gameBoard){
//        System.out.println("updating the next moves for the player: "+this.typeOfPlayer);
        movesAvailable = gameBoard.updateAvailableMoves(typeOfPlayer);
        return movesAvailable.size();
    }

    /**
     * a method for checking if a disk with TYPE of this players TYPE can be added to an specified location in the board
     * @param x x of the location
     * @param y y of the location
     * @param othelloBoard
     * @return a boolean to specify if a disk can be added to the block
     */
    public boolean canAddDisk(int x, int y, Board othelloBoard){
        boolean canBePlaced=false;
        for(int i=0; i<movesAvailable.size(); i++){
            if(movesAvailable.get(i).getY()==y&&movesAvailable.get(i).getX()==x){
                return true;
            }
        }
        return false;
    }

    /**
     * a method for adding a disk in the specified block
     * @param x x of the block
     * @param y y of the block
     * @param othelloBoard the game board
     */
    public void addDisk(int x, int y, Board othelloBoard){
        othelloBoard.addDiskToBoardAndUpdate(x, y, this.typeOfPlayer);
        return;
    }
}
