 package ir.ac.aut;
import java.util.ArrayList;

public class Board {
    private ArrayList<ArrayList<Place>> gameBoard;
    final int SIZE;

    public Board(){
        SIZE=8;
        gameBoard=new ArrayList<ArrayList<Place>>();
        for(int i=0; i<SIZE; i++){
            gameBoard.add(new ArrayList<Place>());
            for(int j=0; j<SIZE; j++){
                gameBoard.get(i).add(new Place(j, i));
            }
        }
        gameBoard.get(3).get(3).fillBlock(TYPE.WHITE);
        gameBoard.get(4).get(4).fillBlock(TYPE.WHITE);
        gameBoard.get(3).get(4).fillBlock(TYPE.BLACK);
        gameBoard.get(4).get(3).fillBlock(TYPE.BLACK);
    }

    public Board(int size){
        SIZE=size;
        gameBoard=new ArrayList<ArrayList<Place>>();
        for(int i=0; i<SIZE; i++){
            gameBoard.add(new ArrayList<Place>());
            for(int j=0; j<SIZE; j++){
                gameBoard.get(i).add(new Place(j, i));
            }
        }
        //bayad dar nazar begiri ke koja mikhay add koni un chartaye vasato pas agar ham
        //az in constructor gharare estefade koni bayad aval check koni ke zarib 4
        //va add kardn yadet nare
    }

    public ArrayList<ArrayList<Place>> getGameBoard(){
        return gameBoard;
    }

    public ArrayList<Place> updateAvailableMoves(TYPE playerType){
        ArrayList<Place> newMoves= new ArrayList<>();
        for(int i=0; i<SIZE; i++){
            for(int j=0; j<SIZE; j++){
                if(gameBoard.get(i).get(j).isFull()){
                    continue;
                }
                if(canMoveTo(i, j, playerType)){
                    newMoves.add(gameBoard.get(i).get(j));
                }

            }
        }
        return newMoves;
    }

    private boolean canMoveTo(int y, int x, TYPE typePlayer){

        //----------------------
        int otherTypeInBetween=0;
        //horizontally forward
        if(x+1<SIZE) {
            for (int j = x + 1; j < SIZE; j++) {
                if (gameBoard.get(y).get(j).isFull() == false) {
                    break;
                } else if (gameBoard.get(y).get(j).giveType().equals(typePlayer.name()) == false) {
                    //unequal TYPEs
                    otherTypeInBetween++;
                } else {
                    //equal TYPEs
                    if (otherTypeInBetween > 0) {
                        return true;
                    }
                    break;
                }
            }
        }

        //horizontally backwards
        otherTypeInBetween=0;
        if(x>0) {
            for (int j = x-1; j>=0; j--){
                if(gameBoard.get(y).get(j).isFull() == false){
                    break;
                }
                else if(gameBoard.get(y).get(j).giveType().equals(typePlayer.name()) == false){
                    //unequal TYPEs
                    otherTypeInBetween++;
                }else {
                    //equal TYPEs
                    if(otherTypeInBetween>0){
                        return true;
                    }
                    break;
                }
            }
        }


        //----------------------
        //vertically forward
        otherTypeInBetween=0;
        if(y<SIZE-1){
            for (int j = y + 1; j < SIZE; j++) {
                if (gameBoard.get(j).get(x).isFull() == false) {
                    break;
                } else if (gameBoard.get(j).get(x).giveType().equals(typePlayer.name()) == false) {
                    //unequal TYPEs
                    otherTypeInBetween++;
                } else {
                    if (otherTypeInBetween > 0) {
                        return true;
                    }
                    break;
                }
            }
        }

        //vertically backward
        otherTypeInBetween=0;
        if(y>0) {
            for (int j = y - 1; j >=0; j--) {
                if (gameBoard.get(j).get(x).isFull() == false) {
                    break;
                } else if (gameBoard.get(j).get(x).giveType().equals(typePlayer.name()) == false) {
                    //unequal TYPEs
                    otherTypeInBetween++;
                } else {
                    if (otherTypeInBetween > 0) {
                        return true;
                    }
                    break;
                }
            }
        }


        //----------------------

        //daimeter forward(\)
        otherTypeInBetween=0;
        if(y+1<SIZE&&x+1<SIZE) {
            for (int i = y + 1, j = x + 1; j < SIZE && i < SIZE; i++, j++) {
                if (gameBoard.get(i).get(j).isFull() == false) {
                    break;
                } else if (gameBoard.get(i).get(j).giveType().equals(typePlayer.name()) == false) {
                    //unequal TYPEs
                    otherTypeInBetween++;
                } else {
                    if (otherTypeInBetween > 0) {
                        return true;
                    }
                    break;
                }
            }
        }

        //daimeter backward(/)
        otherTypeInBetween=0;
        if(y>0&&x>0) {
            for (int i = y - 1, j = x - 1; j < SIZE && i < SIZE; i--, j--) {
                if (gameBoard.get(i).get(j).isFull() == false) {
                    break;
                } else if (gameBoard.get(i).get(j).giveType().equals(typePlayer.name()) == false) {
                    //unequal TYPEs
                    otherTypeInBetween++;
                } else {
                    if (otherTypeInBetween > 0) {
                        return true;
                    }
                    break;
                }
            }
        }


        //diameter forward(\)
        if(x>0&&y<SIZE-1){
            for(int i=y+1 ,j=x-1;i<SIZE&&j>=0; i++, j-- ){
                if (gameBoard.get(i).get(j).isFull() == false) {
                    break;
                } else if (gameBoard.get(i).get(j).giveType().equals(typePlayer.name()) == false) {
                    //unequal TYPEs
                    otherTypeInBetween++;
                } else {
                    if (otherTypeInBetween > 0) {
                        return true;
                    }
                    break;
                }
            }
        }

        //diameter backward(\)
        if(y>0&&x<SIZE-1){
            for(int i=y-1 ,j=x+1;j<SIZE&&i>=0; i--, j++ ){
                if (gameBoard.get(i).get(j).isFull() == false) {
                    break;
                } else if (gameBoard.get(i).get(j).giveType().equals(typePlayer.name()) == false) {
                    //unequal TYPEs
                    otherTypeInBetween++;
                } else {
                    if (otherTypeInBetween > 0) {
                        return true;
                    }
                    break;
                }
            }
        }




        return false;
    }

    public boolean gameEnd(){
        //no moves available for the players
        if(updateAvailableMoves(TYPE.BLACK).size()==0 && updateAvailableMoves(TYPE.WHITE).size()==0){
            return true;
        }
        return false;
    }

    public void printBoard(ArrayList<Place> movesAvailable , TYPE playerTurn){
        System.out.printf("\033[1;36m"+"  ");
        for(int i=0; i<8; i++){
            System.out.printf("  "+X.values()[i]+" ");
        }
        System.out.println("\033[0m");
        for(int i=0; i<SIZE; i++){
            System.out.printf("  ");
            for(int j=0; j<SIZE;j++){
                System.out.printf("\033[1;32m"+"+---");
            }
            System.out.println("+"+"\033[0m");
            //end of midline
            System.out.printf("\033[1;36m"+ (i+1)+" "+"\033[0m");

            for(int j=0; j<SIZE; j++){
                String typeOfPlace=gameBoard.get(i).get(j).giveType();
                if(isMoveBlock(i, j, movesAvailable)){
                    if(playerTurn.name().equals("BLACK")){
                        System.out.printf("\033[1;32m"+"|"+ "\033[0;31m"+" x "+"\033[0m");
                    }else if(playerTurn.name().equals("WHITE")){
                        System.out.printf("\033[1;32m"+"|"+"\033[1;37m"+" x "+"\033[0m");
                    }
                    continue;
                }
                if(typeOfPlace.equals("BLACK")){
                    System.out.printf("\033[1;32m"+"|"+ "\033[0;31m"+" O "+"\033[0m");
                }else if(typeOfPlace.equals("WHITE")){
                    System.out.printf("\033[1;32m"+"|"+"\033[1;37m"+" O "+"\033[0m");
                }else{
                    System.out.printf("\033[1;32m"+"|   ");
                }
                if(j==SIZE-1){
                    System.out.printf("\033[1;32m"+"|"+"\033[0m");
                }
            }
            System.out.println();
        }
        System.out.printf("  ");
        for(int j=0; j<SIZE;j++){
            System.out.printf("\033[1;32m"+"+---");
        }
        System.out.println("+"+"\033[0m");

    }


    private boolean isMoveBlock(int y, int x, ArrayList<Place> moves){
        for(int i=0; i<moves.size(); i++){
            if(moves.get(i).getX()==x && moves.get(i).getY()==y){
//                System.out.println("\n\n\n x= " + x + " y= "+ y);
                return true;
            }
        }
        return false;
    }

    public void addDiskToBoard(int x, int y, TYPE typeOfPlayer){
        //we are sure that this player can move a disk to this block, it was checked before callling this method
        gameBoard.get(y).get(x).fillBlock(typeOfPlayer);
//        updateBoard(x, y, typeOfPlayer);
    }

    private void updateBoard(int x, int y, TYPE typeOfPlayer){

        //----------------------
        //horizontally forward
        if(x+1<SIZE) {
            for (int j = x + 1; j < SIZE; j++) {
                if (gameBoard.get(y).get(j).isFull() == false) {
                    break;
                } else if (gameBoard.get(y).get(j).giveType().equals(typeOfPlayer.name()) == false) {
                    //unequal TYPEs
                    gameBoard.get(y).get(j).fillBlock(typeOfPlayer);
                } else {
                    //equal TYPEs
                    break;
                }
            }
        }

        //horizontally backwards
        if(x>0) {
            for (int j = x-1; j>=0; j--){
                if(gameBoard.get(y).get(j).isFull() == false){
                    break;
                }
                else if(gameBoard.get(y).get(j).giveType().equals(typeOfPlayer.name()) == false){
                    //unequal TYPEs
                    gameBoard.get(y).get(j).fillBlock(typeOfPlayer);
                }else {
                    //equal TYPEs
                    break;
                }
            }
        }


        //----------------------
        //vertically forward
        if(y<SIZE-1){
            for (int j = y + 1; j < SIZE; j++) {
                if (gameBoard.get(j).get(x).isFull() == false) {
                    break;
                } else if (gameBoard.get(j).get(x).giveType().equals(typeOfPlayer.name()) == false) {
                    //unequal TYPEs
                    gameBoard.get(j).get(x).fillBlock(typeOfPlayer);
                } else {
                    break;
                }
            }
        }

        //vertically backward
        if(y>0) {
            for (int j = y - 1; j >=0; j--) {
                if (gameBoard.get(j).get(x).isFull() == false) {
                    break;
                } else if (gameBoard.get(j).get(x).giveType().equals(typeOfPlayer.name()) == false) {
                    //unequal TYPEs
                    gameBoard.get(j).get(x).fillBlock(typeOfPlayer);
                } else {
                    break;
                }
            }
        }


        //----------------------
        //daimeter forward(/)
        if(y+1<SIZE&&x+1<SIZE) {
            for (int i = y + 1, j = x + 1; j < SIZE && i < SIZE; i++, j++) {
                if (gameBoard.get(i).get(j).isFull() == false) {
                    break;
                } else if (gameBoard.get(i).get(j).giveType().equals(typeOfPlayer.name()) == false) {
                    //unequal TYPEs
                    gameBoard.get(i).get(j).fillBlock(typeOfPlayer);
                } else {
                    break;
                }
            }
        }

        //daimeter backward(/)
        if(y>0&&x>0) {
            for (int i = y - 1, j = x - 1; j < SIZE && i < SIZE; i--, j--) {
                if (gameBoard.get(i).get(j).isFull() == false) {
                    break;
                } else if (gameBoard.get(i).get(j).giveType().equals(typeOfPlayer.name()) == false) {
                    //unequal TYPEs
                    gameBoard.get(i).get(j).fillBlock(typeOfPlayer);
                } else {
                    break;
                }
            }
        }


        //---------------------
        //diameter forward(\)
        if(x>0&&y<SIZE-1){
            for(int i=y+1 ,j=x-1;i<SIZE&&j>=0; i++, j-- ){
                if (gameBoard.get(i).get(j).isFull() == false) {
                    break;
                } else if (gameBoard.get(i).get(j).giveType().equals(typeOfPlayer.name()) == false) {
                    //unequal TYPEs
                    gameBoard.get(i).get(j).fillBlock(typeOfPlayer);
                } else {
                    break;
                }
            }
        }

        //diameter backward(\)
        if(y>0&&x<SIZE-1){
            for(int i=y-1 ,j=x+1;j<SIZE&&i>=0; i--, j++ ){
                if (gameBoard.get(i).get(j).isFull() == false) {
                    break;
                } else if (gameBoard.get(i).get(j).giveType().equals(typeOfPlayer.name()) == false) {
                    //unequal TYPEs
                    gameBoard.get(i).get(j).fillBlock(typeOfPlayer);
                } else {
                    break;
                }
            }
        }
    }
}
