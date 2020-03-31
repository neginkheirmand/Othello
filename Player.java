package ir.ac.aut;

import ir.ac.aut.Place;
import ir.ac.aut.TYPE;

import java.util.ArrayList;

public class Player {

    protected final TYPE typeOfPlayer;
    protected ArrayList<Place> movesAvailable;
    protected long timer;

    public Player(TYPE typeOfPlayer){
        this.typeOfPlayer=typeOfPlayer;
        timer=0;
    }

    public ArrayList<Place> getMovesAvailable(){
        return movesAvailable;
    }

    public TYPE getTypeOfPlayer(){
        return typeOfPlayer;
    }

    public int nextMoveUpdate(Board gameBoard){
//        System.out.println("updating the next moves for the player: "+this.typeOfPlayer);
        movesAvailable=gameBoard.updateAvailableMoves(typeOfPlayer);
        return movesAvailable.size();
    }
    public void printInfo(){
        System.out.println("TYPE of this player :"+typeOfPlayer.name()+"\nand has"+ movesAvailable.size()+"moves available to do");

        for(int i=0;i<movesAvailable.size();i++){
            movesAvailable.get(i).printInfo();
        }
        System.out.println("\n\n");
    }

    public boolean canAddDisk(int x, int y, Board othelloBoard){
        boolean canBePlaced=false;
        for(int i=0; i<movesAvailable.size(); i++){
            if(movesAvailable.get(i).getY()==y&&movesAvailable.get(i).getX()==x){
                return true;
            }
        }
        return false;
    }


    public void addDisk(int x, int y, Board othelloBoard){
        othelloBoard.addDiskToBoardAndUpdate(x, y, this.typeOfPlayer);
        return;
    }
}
