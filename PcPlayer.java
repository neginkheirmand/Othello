package ir.ac.aut;

import java.util.ArrayList;


/**
 * This is a model class to hold Pcplayer information
 * this class is a automatic opponents in the case the user wants to play against the ai
 * @author negin
 * @version 1.0
 */
public class PcPlayer extends Player {

    //a constructor
    public PcPlayer(TYPE pcPlayerType){
        super(pcPlayerType);
    }

    /**
     * the main method for this player to move if is its turn
     * @param othelloBoard the game board
     */
    public void decideNextMove(Board othelloBoard){
        if(movesAvailable.size()>1) {
            int index = decidePlace(othelloBoard);
            movesAvailable.get(index).printInfo();
            addDisk(movesAvailable.get(index).getX(), movesAvailable.get(index).getY(), othelloBoard);
            return;
        }
        else if(movesAvailable.size()==1){
            addDisk(movesAvailable.get(0).getX(),movesAvailable.get(0).getY(), othelloBoard);
            return;
        }
        return;
    }

    /**
     * the secundary method which is called in the decideNextMove method and will call the ai method in each of the primary branches of the availableMoves graph
     * @param othelloBoard
     * @return the index of the best branch (choice) in the availableMoves ArrayList
     */
    private int decidePlace(Board othelloBoard){
        int index=0;
        int maxOfFirstBranches=-1000;
        for(int i=0; i<movesAvailable.size(); i++){
            ArrayList<Place> changedBlocksContainer = othelloBoard.addDiskAndGetChangedDisks(movesAvailable.get(i).getX(), movesAvailable.get(i).getY(), this.typeOfPlayer);

            //boro payintar tuye graph
            int ai=ai(othelloBoard, 1, -1000, 1000, TYPE.getOtherTYPE(this.typeOfPlayer), false);
            if(maxOfFirstBranches<ai){
                maxOfFirstBranches=ai;
                index=i;
            }
            othelloBoard.changeDisksColor(changedBlocksContainer);
            othelloBoard.hollowDiskOffBoard(movesAvailable.get(i).getX(), movesAvailable.get(i).getY());
        }
        return index;
    }

    /**
     * the ai method which will search in the graph of the next provable moves of each player this method uses mini max algorithm
     * @param othelloBoard
     * @param repetition
     * @param alpha
     * @param beta
     * @param playerOfTurn
     * @param passTurnBefore
     * @return
     */
    private int ai(Board othelloBoard, int repetition, int alpha, int beta, TYPE playerOfTurn, boolean passTurnBefore){
//        Alpha is the best value that the maximizer currently can guarantee at that level or above.
//        Beta is the best value that the minimizer currently can guarantee at that level or above.

        if(othelloBoard.gameEnd() || (repetition==10) ){
            return othelloBoard.getPoint(this.typeOfPlayer);
        }
        ArrayList<Place> movesForThisPlayer = othelloBoard.updateAvailableMoves(playerOfTurn);
        if(movesForThisPlayer.size()==0){
            return ai(othelloBoard, repetition+1,alpha, beta, TYPE.getOtherTYPE(playerOfTurn), true);
        }

        if(playerOfTurn.name().equals(this.typeOfPlayer.name())) {
            //its my turn so better if the score is higher
            int max=-9999;
            for (int i = 0; i < movesForThisPlayer.size(); i++) {
//
                ArrayList<Place> changedBlocksContainer = othelloBoard. addDiskAndGetChangedDisks ( movesForThisPlayer.get(i).getX(), movesForThisPlayer.get(i).getY(), playerOfTurn);
                //since in othello when we add a disk, the game can impacted with more than 1 disk we have to keep the changed color blocks so  that later we can take them out
                //we could have created a new board and made the changes on that one so we wouldent have to take back our changes but that would takr more memory
                int temp=ai(othelloBoard, repetition+1, alpha, beta, TYPE.getOtherTYPE(playerOfTurn), false);
                if(temp>max){
                    max=temp;
                }
                if(passTurnBefore == false) {
                    if (max >= beta) {
                        othelloBoard.changeDisksColor(changedBlocksContainer);
                        othelloBoard.hollowDiskOffBoard(movesForThisPlayer.get(i).getX(), movesForThisPlayer.get(i).getY());
                        break;
                    } else {
                        beta = max;
                    }
                }
//                othelloBoard.printBoard(new ArrayList<Place>(), TYPE.WHITE);

                othelloBoard.changeDisksColor(changedBlocksContainer);
                othelloBoard.hollowDiskOffBoard(movesForThisPlayer.get(i).getX(), movesForThisPlayer.get(i).getY());
            }
            return max;
        }
        else{
            //turn of opponent
            int min=1000;
            for(int i=0; i<movesForThisPlayer.size(); i++){
//
                ArrayList<Place> changedBlocksContainer = othelloBoard. addDiskAndGetChangedDisks( movesForThisPlayer.get(i).getX(), movesForThisPlayer.get(i).getY(), playerOfTurn);

                int temp=ai(othelloBoard, repetition+1,alpha, beta, TYPE.getOtherTYPE( playerOfTurn ), false);
                if(temp<min){
                    min=temp;
                }
                if(passTurnBefore==false) {
                    if (alpha >= min) {
                        othelloBoard.changeDisksColor(changedBlocksContainer);
                        othelloBoard.hollowDiskOffBoard(movesForThisPlayer.get(i).getX(), movesForThisPlayer.get(i).getY());
                        break;
                    } else {
                        alpha = min;
                    }
                }
                othelloBoard.changeDisksColor(changedBlocksContainer);

                othelloBoard.hollowDiskOffBoard(movesForThisPlayer.get(i).getX(), movesForThisPlayer.get(i).getY());
            }
            return min;
        }
    }

}
