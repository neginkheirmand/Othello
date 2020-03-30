package ir.ac.aut;

import ir.ac.aut.Place;
import ir.ac.aut.TYPE;

import java.util.ArrayList;

public class Player {
    private final TYPE typeOfPlayer;
    private ArrayList<Place> movesAvailable;

    public Player(TYPE typeOfPlayer){
        this.typeOfPlayer=typeOfPlayer;
    }

    public ArrayList<Place> getMovesAvailable(){
        return movesAvailable;
    }

    public TYPE getTypeOfPlayer(){
        return typeOfPlayer;
    }

    public void nextMoveUpdate(Board gameBoard){
        movesAvailable=gameBoard.updateAvailableMoves(typeOfPlayer);
        return;
    }
    public void printInfo(){
        System.out.println("TYPE of this player :"+typeOfPlayer.name()+"\nand has"+ movesAvailable.size()+"moves available to do");

        for(int i=0;i<movesAvailable.size();i++){
            movesAvailable.get(i).printInfo();
        }
        System.out.println("\n\n");
    }

    public boolean addDisk(int x, int y, Board othelloBoard){
        boolean canBePlaced=false;
        for(int i=0; i<movesAvailable.size(); i++){
            if(movesAvailable.get(i).getY()==y&&movesAvailable.get(i).getX()==x){
                canBePlaced=true;
                break;
            }
        }
        if(canBePlaced==false){
            System.out.println("cannot add disk in this block");
            return false;
        }
        System.out.println("\ngonna add disk for player " + this.typeOfPlayer);
        othelloBoard.addDiskToBoard(x, y, this.typeOfPlayer);
        System.out.println("\nadding disk method ended, going back to main to print the board" + this.typeOfPlayer);
        return true;
    }
}
