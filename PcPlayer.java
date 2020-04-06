package ir.ac.aut;

import java.util.ArrayList;

public class PcPlayer extends Player {
//    protected final TYPE typeOfPlayer;
//    protected ArrayList<Place> movesAvailable;
//    protected long timer;

    static int shomarevurudi=0;

    public PcPlayer(TYPE pcPlayerType){
        super(pcPlayerType);

    }

//    public ArrayList<Place> getMovesAvailable(){
//    }
//
//    public TYPE getTypeOfPlayer(){
//    }
//
//    public int nextMoveUpdate(Board gameBoard){
//        movesAvailable=gameBoard.updateAvailableMoves(typeOfPlayer);
//        return movesAvailable.size();
//    }

    public void printInfo(){
        System.out.println("This is the info of Pc Player:\nTYPE of this player :"+typeOfPlayer.name()+"\nand has"+ movesAvailable.size()+"moves available to do");

        for(int i=0;i<movesAvailable.size();i++){
            movesAvailable.get(i).printInfo();
        }
        System.out.println("\n\n");
    }
//
//    public boolean canAddDisk(int x, int y, Board othelloBoard){
//        boolean canBePlaced=false;
//        for(int i=0; i<movesAvailable.size(); i++){
//            if(movesAvailable.get(i).getY()==y&&movesAvailable.get(i).getX()==x){
//                return true;
//            }
//        }
//        return false;
//    }
//      //we actually dont need this function but i dont know what to do with it

//    public void addDisk(int x, int y, Board othelloBoard){
//        othelloBoard.addDiskToBoard(x, y, this.typeOfPlayer);
//        return;
//    }

    public void decideNextMove(Board othelloBoard){
        if(movesAvailable.size()>1) {
            int index = decidePlace(othelloBoard);
            System.out.println("i am going to choose the "+ index +" place in the movesAvailable arrayList :");
            movesAvailable.get(index).printInfo();
            System.out.println("that was the info about that block");

            addDisk(movesAvailable.get(index).getX(), movesAvailable.get(index).getY(), othelloBoard);
            shomarevurudi++;
            return;
        }
        else if(movesAvailable.size()==1){
            addDisk(movesAvailable.get(0).getX(),movesAvailable.get(0).getY(), othelloBoard);
            shomarevurudi++;
            return;
        }
        shomarevurudi++;
        System.out.println("PASS");
        return;
    }

    private int decidePlace(Board othelloBoard){
        int index=0;
        int maxOfFirstBranches=-96;
        for(int i=0; i<movesAvailable.size(); i++){
            othelloBoard.addDiskToBoard(movesAvailable.get(i).getX(), movesAvailable.get(i).getY(), this.typeOfPlayer);
            //boro payintar tuye graph
            int ai=ai(othelloBoard, 1, -96, 96, TYPE.getOtherTYPE(typeOfPlayer), false);
            if(maxOfFirstBranches<ai){
                maxOfFirstBranches=ai;
                index=i;
            }
            othelloBoard.hollowDiskOffBoard(movesAvailable.get(i).getX(), movesAvailable.get(i).getY());
        }
        return index;
    }

    private int ai(Board othelloBoard, int repetition, int alpha, int beta, TYPE playerOfTurn, boolean passTurnBefore){
//        Alpha is the best value that the maximizer currently can guarantee at that level or above.
//        Beta is the best value that the minimizer currently can guarantee at that level or above.
        if(shomarevurudi>24) {
            System.out.println("othelloBoard.gameEnd() is: " + othelloBoard.gameEnd());
            System.out.println("repetition==? is: " + repetition);
            System.out.println("othelloBoard.getHollowBlocks() is: " + othelloBoard.getHollowBlocks());
        }
        if(othelloBoard.gameEnd() || (repetition==10/* && othelloBoard.getHollowBlocks()>3*/) ){
            if(shomarevurudi>24) {
                System.out.println("was able to make it");
            }
            return othelloBoard.getPoint(this.typeOfPlayer);
        }
        ArrayList<Place> movesForThisPlayer = othelloBoard.updateAvailableMoves(typeOfPlayer);
        if(movesForThisPlayer.size()==0){
            return ai(othelloBoard, repetition+1,alpha, beta, TYPE.getOtherTYPE(playerOfTurn), true);
        }

        if(playerOfTurn.name().equals(this.typeOfPlayer.name())) {
            //its my turn so better if the score is higher
            int max=-96;//(6*6* 1) + (6*4* 2) + (4* 3)=96
            for (int i = 0; i < movesForThisPlayer.size(); i++) {
                othelloBoard.addDiskToBoard(movesForThisPlayer.get(i).getX(), movesForThisPlayer.get(i).getY(), playerOfTurn);
                int temp=ai(othelloBoard, repetition+1, alpha, beta, TYPE.getOtherTYPE(playerOfTurn), false);
                if(temp>max){
                    max=temp;
                }
                if(passTurnBefore==false) {
                    if (max >= beta) {
                        othelloBoard.hollowDiskOffBoard(movesForThisPlayer.get(i).getX(), movesForThisPlayer.get(i).getY());
                        break;
                    } else {
                        beta = max;
                    }
                }
                othelloBoard.hollowDiskOffBoard(movesForThisPlayer.get(i).getX(), movesForThisPlayer.get(i).getY());
            }


            return max;


        }
        else{
            //turn of opponent
            int min=96;
            for(int i=0; i<movesForThisPlayer.size(); i++){
                othelloBoard.addDiskToBoard(movesForThisPlayer.get(i).getX(), movesForThisPlayer.get(i).getY(), playerOfTurn);
                int temp=ai(othelloBoard, repetition+1,alpha, beta, TYPE.getOtherTYPE(playerOfTurn), false);
                if(temp<min){
                    min=temp;
                }
                if(passTurnBefore==true) {
                    if (alpha >= min) {
                        othelloBoard.hollowDiskOffBoard(movesForThisPlayer.get(i).getX(), movesForThisPlayer.get(i).getY());
                        break;
                    } else {
                        alpha = min;
                    }
                }
                othelloBoard.hollowDiskOffBoard(movesForThisPlayer.get(i).getX(), movesForThisPlayer.get(i).getY());
            }
            return min;
        }
    }

}
