package ir.ac.aut;

public class PcPlayer extends Player {
//    protected final TYPE typeOfPlayer;
//    protected ArrayList<Place> movesAvailable;
//    protected long timer;


//    public ArrayList<Place> getMovesAvailable(){}
//
//    public TYPE getTypeOfPlayer(){}
//
//    public int nextMoveUpdate(Board gameBoard){
//        movesAvailable=gameBoard.updateAvailableMoves(typeOfPlayer);
//        return movesAvailable.size();
//    }
//    public void printInfo(){}

//    public boolean addDisk(int x, int y, Board othelloBoard){
//        boolean canBePlaced=false;
//        for(int i=0; i<movesAvailable.size(); i++){
//            if(movesAvailable.get(i).getY()==y&&movesAvailable.get(i).getX()==x){
//                canBePlaced=true;
//                break;
//            }
//        }
//        othelloBoard.addDiskToBoard(x, y, this.typeOfPlayer);
//        return true;
//    }


    public PcPlayer(TYPE pcPlayerType){
        super(pcPlayerType);

    }
}
