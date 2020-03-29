 package ir.ac.aut;

 import ir.ac.aut.Board;
 import java.util.Scanner;

 enum X{
     A,
     B,
     C,
     D,
     E,
     F,
     G,
     H
 }
 public class Main {
    public static Board myOthelloBoard= new Board();
//    public static int size=8;
    public static Player playerWhite;
    public static Player playerBlack;

    public static void main(String args[]){
        Scanner wait=new Scanner(System.in);

        playerBlack = new Player(TYPE.BLACK);
        playerWhite = new Player(TYPE.WHITE);
        String nextMoveBlack;
        String nextMoveWhite;

        int newX=0, newY=0;
        while(myOthelloBoard.gameEnd()==false){
            //first move to black pawn
            playerBlack.nextMoveUpdate(myOthelloBoard);
            myOthelloBoard.printBoard(playerBlack.getMovesAvailable(), playerBlack.getTypeOfPlayer());
            boolean validInput=false;
            int numOfWrongInputs=0;
            do {
                if(numOfWrongInputs>0){
                    System.out.printf("you have to enter a valid direction of block, pay atention to the blocks filled with:");
                    System.out.printf("\033[0;31m"+"x\n"+"\033[0m");
                    validInput=false;
                }

                do{
                    nextMoveBlack=wait.nextLine();
                    try {
                        newY = Integer.parseInt(nextMoveBlack.charAt(0) + "");
                        newY--;
                        newX = X.valueOf(nextMoveBlack.charAt(1) + "").ordinal();
                        System.out.println("x is = " + newX + "  y = " + newY);
                        validInput = true;
                    } catch (IllegalArgumentException e) {
                        validInput = false;
                        System.out.println("the format should be IC with no space in between and the I should be a valid number");
                        System.out.println("the C should be a valid letter with Capital mode--> \"3C\"\n");
                    }
                }while (!validInput);
                numOfWrongInputs++;
            }while(!playerBlack.addDisk(newX, newY, myOthelloBoard));

            //-------
            playerWhite.nextMoveUpdate(myOthelloBoard);
            myOthelloBoard.printBoard(playerWhite.getMovesAvailable(), playerWhite.getTypeOfPlayer());
            validInput=false;
            numOfWrongInputs=0;
            do {
                if(numOfWrongInputs>0){
                    System.out.printf("you have to enter a valid direction of block, pay atention to the blocks filled with:");
                    System.out.printf("\033[1;37m"+"x\n"+"\033[0m");
                    validInput=false;
                }

                do{
                    nextMoveWhite=wait.nextLine();
                    try {
                        newY = Integer.parseInt(nextMoveWhite.charAt(0) + "");
                        newY--;
                        newX = X.valueOf(nextMoveWhite.charAt(1) + "").ordinal();
                        validInput = true;
                    } catch (IllegalArgumentException e) {
                        validInput = false;
                        System.out.println("the format should be IC with no space in between and the I should be a valid number");
                        System.out.println("the C should be a valid letter with Capital mode--> \"3C\"\n");
                    }
                }while (!validInput);
                numOfWrongInputs++;
            }while(!playerWhite.addDisk(newX, newY, myOthelloBoard));
        }




        System.out.printf("----------------\nend of main\n");
        myOthelloBoard.printBoard(playerBlack.getMovesAvailable(), playerBlack.getTypeOfPlayer());
        System.out.printf("end of main\n----------------\n");
    }
}
