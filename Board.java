 package ir.ac.aut;

import java.util.ArrayList;

 public class Board {
    private ArrayList<ArrayList<Place>> gameBoard;
    final int SIZE;
    public Board(){
//        Date timer= new Date(0);
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

    public int getPoint(TYPE typeOfPlayer){
        int point=0;
         for(int i=0; i<SIZE; i++){
             for(int j=0; j<SIZE; j++){
                 if(gameBoard.get(i).get(j).isFull()==false){
                     continue;
                 }
                 else if(gameBoard.get(i).get(j).giveType().equals(typeOfPlayer.name())){
                   point++;
                 }
                 else{
                     point--;
                 }
             }
         }
         //azunjaE ke dashtn mohit gameBoard ahamiat bishtari dare:
         int numEdgeMe=0;
         int numEdgeOpponent=0;
 //----
         for(int i=0; i<SIZE; i++){
             if(gameBoard.get(0).get(i).isFull()==false){
                 continue;
             }
             else if(gameBoard.get(0).get(i).giveType().equals(typeOfPlayer.name())){
                 numEdgeMe++;
             }
             else{
                 numEdgeOpponent++;
             }
         }
//_____
         for(int i=0; i<SIZE; i++){
             if(gameBoard.get(SIZE-1).get(i).isFull()==false){
                 continue;
             }
             else if(gameBoard.get(SIZE-1).get(i).giveType().equals(typeOfPlayer.name())){
                 numEdgeMe++;
             }
             else{
                 numEdgeOpponent++;
             }
         }
// |--
         for(int i=0; i<SIZE; i++){
             if(gameBoard.get(i).get(0).isFull()==false){
                 continue;
             }
             else if(gameBoard.get(i).get(0).giveType().equals(typeOfPlayer.name())){
                 numEdgeMe++;
             }
             else{
                 numEdgeOpponent++;
             }
         }
// --|
         for(int i=0; i<SIZE; i++){
             if(gameBoard.get(i).get(SIZE-1).isFull()==false){
                 continue;
             }
             else if(gameBoard.get(i).get(SIZE-1).giveType().equals(typeOfPlayer.name())){
                 numEdgeMe++;
             }
             else{
                 numEdgeOpponent++;
             }
         }

        //stableDisks Strategy
         int addPoint=getNumStableDisks(typeOfPlayer);
         return point+addPoint;
    }

    private boolean existsInArray(ArrayList<Place> stables, int x, int y){
        for(int i=0; i<stables.size(); i++){
            if(x==stables.get(i).getX() && y==stables.get(i).getY()){
                return true;
            }
        }
        return false;
    }
    
    private int getNumStableDisks(TYPE typeOfPlayer){
        ArrayList<Place> stabledisks=new ArrayList<>();

        //up-left
        int numContinousBlocks=0;
        //first line
        for(int i=0; i<SIZE; i++){
            if(gameBoard.get(0).get(i).giveType().equals(typeOfPlayer.name())){
                numContinousBlocks++;
                stabledisks.add(gameBoard.get(0).get(i));
            }else{
                break;
            }
        }
        //other lines
        for(int i=1; i<SIZE; i++){
            int numNextLineContinousBlocks=0;
            for(int j=0; j<numContinousBlocks-1;j++){
                if(gameBoard.get(i).get(j).giveType().equals(typeOfPlayer.name())){
                    if(existsInArray(stabledisks, j, i)==false){
                        stabledisks.add(gameBoard.get(i).get(j));
                    }
                    numNextLineContinousBlocks++;
                }else{
                    break;
                }
            }
            numContinousBlocks=numNextLineContinousBlocks;
            if(numContinousBlocks==0){
                break;
            }
            else if(numContinousBlocks==1){
                numContinousBlocks=2;
            }
        }

        //up-right
        numContinousBlocks=0;
        //first line
        for(int i=SIZE; i>=0; i--){
            if(gameBoard.get(0).get(i).giveType().equals(typeOfPlayer.name())){
                numContinousBlocks++;
                if(existsInArray(stabledisks, i, 0)==false) {
                    stabledisks.add(gameBoard.get(0).get(i));
                }
            }else{
                break;
            }
        }
        //other lines
        for(int i=0; i<SIZE; i++){
            int numNextLineContinousBlocks=0;
            for(int j=SIZE; (SIZE-j)<numContinousBlocks-1;j--){
                if(gameBoard.get(i).get(j).giveType().equals(typeOfPlayer.name())){
                    if(existsInArray(stabledisks, j, i)==false){
                        stabledisks.add(gameBoard.get(i).get(j));
                    }
                    numNextLineContinousBlocks++;
                }else{
                    break;
                }
            }
            numContinousBlocks=numNextLineContinousBlocks;
            if(numContinousBlocks==0){
                break;
            }
            else if(numContinousBlocks==1){
                numContinousBlocks=2;
            }
        }

        //down-right
        numContinousBlocks=0;
        //first line
        for(int i=SIZE; i>=0; i--){
            if(gameBoard.get(7).get(i).giveType().equals(typeOfPlayer.name())){
                numContinousBlocks++;
                if(existsInArray(stabledisks, i, 7)==false) {
                    stabledisks.add(gameBoard.get(7).get(i));
                }
            }else{
                break;
            }
        }
        //other lines
        for(int i=SIZE; i>=0; i--){
            int numNextLineContinousBlocks=0;
            for(int j=SIZE; (SIZE-j)<numContinousBlocks-1;j--){
                if(gameBoard.get(i).get(j).giveType().equals(typeOfPlayer.name())){
                    if(existsInArray(stabledisks, j, i)==false){
                        stabledisks.add(gameBoard.get(i).get(j));
                    }
                    numNextLineContinousBlocks++;
                }else{
                    break;
                }
            }
            numContinousBlocks=numNextLineContinousBlocks;
            if(numContinousBlocks==0){
                break;
            }
            else if(numContinousBlocks==1){
                numContinousBlocks=2;
            }
        }

        //down-left
        numContinousBlocks=0;
        //first line
        for(int i=0; i<SIZE; i++){
            if(gameBoard.get(7).get(i).giveType().equals(typeOfPlayer.name())){
                numContinousBlocks++;
                if(existsInArray(stabledisks, i, 7)==false) {
                    stabledisks.add(gameBoard.get(7).get(i));
                }
            }else{
                break;
            }
        }
        //other lines
        for(int i=SIZE; i>=0; i--){
            int numNextLineContinousBlocks=0;
            for(int j=0; j<numContinousBlocks-1;j++){
                if(gameBoard.get(i).get(j).giveType().equals(typeOfPlayer.name())){
                    if(existsInArray(stabledisks, j, i)==false){
                        stabledisks.add(gameBoard.get(i).get(j));
                    }
                    numNextLineContinousBlocks++;
                }else{
                    break;
                }
            }
            numContinousBlocks=numNextLineContinousBlocks;
            if(numContinousBlocks==0){
                break;
            }
            else if(numContinousBlocks==1){
                numContinousBlocks=2;
            }
        }

        return stabledisks.size();
    }

     private int[][] makeStrategyDiagram(TYPE typeOfPcPlayer){

        int[][] strategyDiagram={
                 {99, -8, 8, 6, 6, 8, -8, 99},
                 {-8,-24,-4,-3,-3,-4,-24, -8},
                 { 8, -4, 7, 4, 4, 7, -4,  8},
                 { 6, -3, 4, 0, 0, 4, -3,  6},
                 { 6, -3, 4, 0, 0, 4, -3,  6},
                 { 8, -4, 7, 4, 4, 7, -4,  8},
                 {-8,-24,-4,-3,-3,-4,-24, -8},
                 {99, -8, 8, 6, 6, 8, -8, 99},
         };
        //the corners
         if(gameBoard.get(0).get(0).isFull()==true && gameBoard.get(0).get(0).giveType().equals(typeOfPcPlayer.name())){
            strategyDiagram[0][1]*=-1;
            strategyDiagram[1][0]*=-1;
            strategyDiagram[1][1]*=-1;

        }
         if(gameBoard.get(0).get(7).isFull()==true && gameBoard.get(0).get(7).giveType().equals(typeOfPcPlayer.name())){
             strategyDiagram[0][6]*=-1;
             strategyDiagram[1][6]*=-1;
             strategyDiagram[1][7]*=-1;

         }
         if(gameBoard.get(7).get(0).isFull()==true && gameBoard.get(7).get(0).giveType().equals(typeOfPcPlayer.name())){
             strategyDiagram[7][1]*=-1;
             strategyDiagram[6][0]*=-1;
             strategyDiagram[6][1]*=-1;

         }
         if(gameBoard.get(7).get(7).isFull()==true && gameBoard.get(7).get(7).giveType().equals(typeOfPcPlayer.name())){
             strategyDiagram[7][6]*=-1;
             strategyDiagram[6][6]*=-1;
             strategyDiagram[6][7]*=-1;

         }

         //top edge
         int numMyFull=0;
         for(int i=0; i<8; i++){
             if(gameBoard.get(0).get(i).isFull()==true&&gameBoard.get(0).get(i).giveType().equals(typeOfPcPlayer.name())){
                 numMyFull++;
                 continue;
             }
         }
         if(numMyFull==SIZE){
             strategyDiagram[0][1]=8;
             strategyDiagram[0][7]=8;
             for(int i=0; i<8; i++){
                 if(strategyDiagram[1][i]==-24){
                     strategyDiagram[1][i]=-7;
                 }
                 else if(strategyDiagram[1][i]<0){
                     strategyDiagram[1][i]*=-1;
                     continue;
                 }
             }
         }

         //right edge
         numMyFull=0;
         for(int i=0; i<8; i++){
             if(gameBoard.get(i).get(7).isFull()==true&&gameBoard.get(i).get(7).giveType().equals(typeOfPcPlayer.name())){
                 numMyFull++;
                 continue;
             }
         }
         if(numMyFull==SIZE){
             strategyDiagram[1][7]=8;
             strategyDiagram[6][7]=8;
             for(int i=0; i<8; i++){
                 if(strategyDiagram[i][6]==-24){
                     strategyDiagram[i][6]=-7;
                 }
                 else if(strategyDiagram[i][6]==-7){
                     strategyDiagram[i][6]=8;
                 }
                 else if(i==0||i==6){
                     strategyDiagram[i][6]=24;
                 }
                 else if(strategyDiagram[i][6]<0){
                     strategyDiagram[i][6]*=-1;
                     continue;
                 }
             }
         }

         //left edge
         numMyFull=0;
         for(int i=0; i<8; i++){
             if(gameBoard.get(i).get(0).isFull()==true&&gameBoard.get(i).get(0).giveType().equals(typeOfPcPlayer.name())){
                 numMyFull++;
             }
         }
         if(numMyFull==SIZE){
             for(int i=0; i<8; i++){
                 if(strategyDiagram[i][0]==-24){
                     strategyDiagram[i][0]=-7;
                 }
                 else if(strategyDiagram[i][0]==-7){
                     strategyDiagram[i][0]=7;
                 }
                 else if(strategyDiagram[i][0]<0){
                     strategyDiagram[i][0]*=-1;
                     continue;
                 }
             }
         }

         //downside edge
         numMyFull=0;
         for(int i=0; i<8; i++){
             if(gameBoard.get(7).get(i).isFull()==true&&gameBoard.get(7).get(i).giveType().equals(typeOfPcPlayer.name())){
                 numMyFull++;
                 continue;

             }
         }
         if(numMyFull==SIZE){
             for(int i=0; i<8; i++){
                 if(strategyDiagram[7][i]==-24){
                     strategyDiagram[7][i]=-7;
                 }
                 else if(strategyDiagram[7][i]==-7){
                     strategyDiagram[7][i]=7;
                 }
                 else if(strategyDiagram[7][i]<0){
                     strategyDiagram[7][i]*=-1;
                     continue;
                 }
             }
         }
         return strategyDiagram;
     }

     public int getHollowBlocks(){
         int hollowBlocks=0;
         for(int i=0; i<SIZE; i++){
             for(int j=0; j<SIZE; j++){
                 if(gameBoard.get(i).get(j).isFull()==false){
                     hollowBlocks++;
                 }
             }
         }
         return hollowBlocks;
     }

     public int getBlackDiskNum(){
         int num=0;
         for(int i=0; i<SIZE; i++){
             for(int j=0; j<SIZE; j++){
                 if(gameBoard.get(i).get(j).isFull()==true && gameBoard.get(i).get(j).giveType().equals(TYPE.BLACK)){
                     num++;
                 }
             }
         }
         return num;
     }

     public int getWhiteDiskNum(){
         int num=0;
         for(int i=0; i<SIZE; i++){
             for(int j=0; j<SIZE; j++){
                 if(gameBoard.get(i).get(j).isFull()==true && gameBoard.get(i).get(j).giveType().equals(TYPE.WHITE)){
                     num++;
                 }
             }
         }
         return num;
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
    //X and Y passed to this method are from (0, SIZE-1)

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
//                        System.out.println("\033[1;36m"+"--------1---the ones from before were accepted::     x= "+X.valueOfInt(x)+  " y= "+(y+1)+"is movable "+"\033[0m");
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
//                        System.out.println("\033[1;36m"+"--------2---the ones from before were accepted::     x= "+X.valueOfInt(x)+  " y= "+(y+1)+"is movable "+"\033[0m");
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
//                        System.out.println("\033[1;36m"+"--------3---the ones from before were accepted::     x= "+X.valueOfInt(x)+  " y= "+(y+1)+"is movable "+"\033[0m");
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
//                        System.out.println("\033[1;36m"+"--------4---the ones from before were accepted::     x= "+X.valueOfInt(x)+  " y= "+(y+1)+"is movable "+"\033[0m");
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
//                        System.out.println("\033[1;36m"+"--------5---the ones from before were accepted::     x= "+X.valueOfInt(x)+  " y= "+(y+1)+"is movable "+"\033[0m");
                        return true;
                    }

                    break;
                }
            }
        }

        //daimeter backward(\)
        otherTypeInBetween=0;
        if(y>0&&x>0) {
            for (int i = y - 1, j = x - 1; j >=0 && i >= 0; i--, j--) {
                if (gameBoard.get(i).get(j).isFull() == false) {
                    break;
                } else if (gameBoard.get(i).get(j).giveType().equals(typePlayer.name()) == false) {
                    //unequal TYPEs
                    otherTypeInBetween++;
                } else {
                    if (otherTypeInBetween > 0) {
//                        System.out.println("\033[1;36m"+"--------6---the ones from before were accepted::     x= "+X.valueOfInt(x)+  " y= "+(y+1)+"is movable "+"\033[0m");
                        return true;
                    }
                    break;
                }
            }
        }


        //diameter backward(/)
        otherTypeInBetween=0;
        if(x>0&&y<SIZE-1){
            for(int i=y+1 ,j=x-1;i<SIZE&&j>=0; i++, j-- ){
                if (gameBoard.get(i).get(j).isFull() == false) {
                    break;
                } else if (gameBoard.get(i).get(j).giveType().equals(typePlayer.name()) == false) {
                    //unequal TYPEs
                    otherTypeInBetween++;
                } else {
                    if (otherTypeInBetween > 0) {
//                        System.out.println("\033[1;36m"+"--------7---the ones from before were accepted::     x= "+X.valueOfInt(x)+  " y= "+(y+1)+"is movable "+"\033[0m");
                        return true;
                    }
                    break;
                }
            }
        }

        //diameter backward(/)
        otherTypeInBetween=0;
        if(y>0&&x<SIZE-1){
            for(int i=y-1 ,j=x+1;j<SIZE&&i>=0; i--, j++ ){
                if (gameBoard.get(i).get(j).isFull() == false) {
                    break;
                } else if (gameBoard.get(i).get(j).giveType().equals(typePlayer.name()) == false) {
                    //unequal TYPEs
                    otherTypeInBetween++;
                } else {
                    if (otherTypeInBetween > 0) {
//                        System.out.println("\033[1;36m"+"--------8---the ones from before were accepted::     x= "+X.valueOfInt(x)+  " y= "+(y+1)+"is movable "+"\033[0m");
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
        gameBoard.get(y).get(x).fillBlock(typeOfPlayer);
        return;
    }

     public void hollowDiskOffBoard(int x, int y){
         gameBoard.get(y).get(x).hollow();
         return;
     }

    public void addDiskToBoardAndUpdate(int x, int y, TYPE typeOfPlayer){
        //we are sure that this player can move a disk to this block, it was checked before callling this method
        gameBoard.get(y).get(x).fillBlock(typeOfPlayer);
//        System.out.printf("just added a disk for player "+typeOfPlayer.name()+" now we have to update the othello board\n");
        updateBoard(x, y, typeOfPlayer);
//        System.out.printf("the othello board was updated:\n");
    }

    private void updateBoard(int x, int y, TYPE typeOfPlayer){


        int numberOfBlocksToChange=0;
        //----------------------(Type of iteration: 1)
        //horizontally forward
        if(x+1<SIZE) {
            for (int j = x + 1; j < SIZE; j++) {
                if (gameBoard.get(y).get(j).isFull() == false) {
                    break;
                } else if (gameBoard.get(y).get(j).giveType().equals(typeOfPlayer.name()) == false) {
                    //unequal TYPEs
                    numberOfBlocksToChange++;
//                    System.out.println("1)unequal type is:   x = "+X.valueOfInt(j)+"  y= "+(y+1));
                } else {
                    //equal TYPEs
                    if(numberOfBlocksToChange>0){
                        color(x, y, numberOfBlocksToChange, typeOfPlayer,1);
                    }
                    break;
                }
            }
        }

        //horizontally backwards
        numberOfBlocksToChange=0;
        if(x>0) {
            for (int j = x-1; j>=0; j--){
                if(gameBoard.get(y).get(j).isFull() == false){
                    break;
                }
                else if(gameBoard.get(y).get(j).giveType().equals(typeOfPlayer.name()) == false){
                    //unequal TYPEs
//                    System.out.println("2)unequal type is:   x = "+X.valueOfInt(j)+"  y= "+(y+1));
                    numberOfBlocksToChange++;
                }else {
                    //equal TYPEs
                    if(numberOfBlocksToChange>0){
                        color(x-1-numberOfBlocksToChange, y, numberOfBlocksToChange, typeOfPlayer,1);
                    }
                    break;
                }
            }
        }

        //----------------------(2)
        //vertically forward
        numberOfBlocksToChange=0;
        if(y<SIZE-1){
            for (int j = y + 1; j < SIZE; j++) {
                if (gameBoard.get(j).get(x).isFull() == false) {
                    break;
                } else if (gameBoard.get(j).get(x).giveType().equals(typeOfPlayer.name()) == false) {
                    //unequal TYPEs
//                    System.out.println("3)unequal type is:   X = "+X.valueOfInt(x)+"  y= "+(j+1));
                    numberOfBlocksToChange++;
                } else {
                    if(numberOfBlocksToChange>0){
                        color(x, y, numberOfBlocksToChange, typeOfPlayer, 2);
                    }
                    break;
                }
            }
        }

        //vertically backward(
        numberOfBlocksToChange=0;
        if(y>0) {
            for (int j = y - 1; j >=0; j--) {
                if (gameBoard.get(j).get(x).isFull() == false) {
                    break;
                } else if (gameBoard.get(j).get(x).giveType().equals(typeOfPlayer.name()) == false) {
                    //unequal TYPEs
//                    System.out.println("4)unequal type is:   x = "+X.valueOfInt(x)+"  y= "+(j+1));
                    numberOfBlocksToChange++;
                } else {
                    if(numberOfBlocksToChange>0){
                        color(x, y-1-numberOfBlocksToChange, numberOfBlocksToChange, typeOfPlayer, 2);
                    }
                    break;
                }
            }
        }

        //----------------------(3)
        //daimeter forward(\)
        numberOfBlocksToChange=0;
        if(y+1<SIZE&&x+1<SIZE) {
            for (int i = y + 1, j = x + 1; j < SIZE && i < SIZE; i++, j++) {
                if (gameBoard.get(i).get(j).isFull() == false) {
                    break;
                } else if (gameBoard.get(i).get(j).giveType().equals(typeOfPlayer.name()) == false) {
                    //unequal TYPEs
//                    System.out.println("5)unequal type is:   x = "+X.valueOfInt(j)+"  y= "+(i+1));
                    numberOfBlocksToChange++;
                } else {
                    if(numberOfBlocksToChange>0){
                        color(x, y, numberOfBlocksToChange, typeOfPlayer, 3);
                    }
                    break;
                }
            }
        }
        //daimeter backward(\)
        numberOfBlocksToChange=0;
        if(y>0&&x>0) {
            for (int i = y - 1, j = x - 1; j >= 0 && i >= 0; i--, j--) {
                if (gameBoard.get(i).get(j).isFull() == false) {
                    break;
                } else if (gameBoard.get(i).get(j).giveType().equals(typeOfPlayer.name()) == false) {
                    //unequal TYPEs
//                    System.out.println("6)unequal type is:   x = "+X.valueOfInt(j)+"  y= "+(i+1));
                    numberOfBlocksToChange++;
                } else {
                    if(numberOfBlocksToChange>0){
                        color(x-1-numberOfBlocksToChange, y-1-numberOfBlocksToChange, numberOfBlocksToChange, typeOfPlayer, 3);
                    }
                    break;
                }
            }
        }

        //---------------------(4)
        //diameter forward(/)
        numberOfBlocksToChange=0;
        if(x>0&&y<SIZE-1){
            for(int i=y+1 ,j=x-1;i<SIZE&&j>=0; i++, j-- ){
                if (gameBoard.get(i).get(j).isFull() == false) {
                    break;
                } else if (gameBoard.get(i).get(j).giveType().equals(typeOfPlayer.name()) == false) {
                    //unequal TYPEs
//                    System.out.println("7)unequal type is:   x = "+X.valueOfInt(j)+"  y= "+(i+1));
                    numberOfBlocksToChange++;
                } else {
                    if(numberOfBlocksToChange>0){
                        color(x-1-numberOfBlocksToChange, y+1+numberOfBlocksToChange, numberOfBlocksToChange, typeOfPlayer, 4);
                    }
                    break;
                }
            }
        }

        //diameter backward(/)
        numberOfBlocksToChange=0;
        if(y>0&&x<SIZE-1){
            for(int i=y-1 ,j=x+1;j<SIZE&&i>=0; i--, j++ ){
                if (gameBoard.get(i).get(j).isFull() == false) {
                    break;
                } else if (gameBoard.get(i).get(j).giveType().equals(typeOfPlayer.name()) == false) {
//                    unequal TYPEs
//                    System.out.printf("8)unequal type is:   x = "+X.valueOfInt(j)+"  y= "+(i+1)+"\n");
                    numberOfBlocksToChange++;
                } else {
                    if(numberOfBlocksToChange>0){
                        color(x, y, numberOfBlocksToChange, typeOfPlayer, 4);
                    }
                    break;
                }
            }
        }

        return;
    }

    private void color(int x1, int y1,int numBlocksToChange, TYPE typeDisk, int typeOfIteration ){
        //the x1 is the argument x of the disk with type declared before and x2 is where it should end

        //  <-->
        if(typeOfIteration==1){
            for(int i=x1+1; i<x1+numBlocksToChange+1; i++){
                gameBoard.get(y1).get(i).changeDisk();
//                System.out.printf("*1*)changed the color of x= "+X.valueOfInt(i)+"  y="+(y1)+"\n");
            }
            return;
        }

        //  |
        else if(typeOfIteration==2){
            for(int j=y1+1 ; j<y1+1+numBlocksToChange; j++){
                gameBoard.get(j).get(x1).changeDisk();
//                System.out.printf("*2*)changed the color of x= "+X.valueOfInt(x1)+"  y="+(j+1)+"\n");
            }
            return;
        }

        //  \
        else if(typeOfIteration==3){
            for(int i=x1+1, j=y1+1; i<x1+1+numBlocksToChange; i++,j++) {
                gameBoard.get(j).get(i).changeDisk();
//                System.out.printf("*3*)--- %d --changed the color of x= " + X.valueOfInt(i) + "  y=" + (j + 1) + "  NUMBER OF BLOCKS TO BE CHANGED: %d\n", i - x1, numBlocksToChange);
            }
            return;
        }

        //   /
        else if(typeOfIteration==4){
            for(int i=x1+1, j=y1-1; i<x1+1+numBlocksToChange; i++, j--){
                gameBoard.get(j).get(i).changeDisk();
//                System.out.printf("*4*)changed the color of x= "+X.valueOfInt(i)+"  y="+(j+1)+"\n");
            }
            return;
        }
        return;
    }

    public void printInfo(){
        int numHollowBlocks=0;
        int numWhiteBlocks=0;
        int numBlackBlocks=0;
        for(int i=0; i<SIZE; i++){
            for(int j=0; j<SIZE; j++){
                if(gameBoard.get(i).get(j).isFull()==false){
                    numHollowBlocks++;
                }else if(gameBoard.get(i).get(j).giveType().equals("WHITE")){
                    numWhiteBlocks++;
                }else{
                    numBlackBlocks++;
                }
            }
        }
        if(numHollowBlocks!=0) {
            System.out.println("number of hollow blocks are: "+numHollowBlocks);
        }
        if(numWhiteBlocks>numBlackBlocks) {
            System.out.println("\033[1;32m"+"WINNER is    :"+"\033[0m"+"WHITE PLAYER");
        }
        System.out.println("timer: " );
        System.out.println("number of White blocks: "+numWhiteBlocks);
        if(numBlackBlocks>numWhiteBlocks){
            System.out.println("\033[1;32m"+"WINNER is :   "+"\033[0;31m"+"BLACK PLAYER"+"\033[0m");
        }
        System.out.println("\033[0;31m"+"timer: " );
        System.out.println("number of Black blocks: "+numBlackBlocks+"\033[0m");
        return;
    }
}
