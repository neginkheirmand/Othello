package ir.ac.aut;

import java.util.ArrayList;
import java.util.Scanner;

public class Run {
    public static void main(String[] args) {
        Scanner wait=new Scanner(System.in);
        System.out.println("\033[0;35m"+"do you want to Play against the computer or against human opponent?");
        System.out.println("1)"+"\033[1;36m"+" AI"+"\033[0m"+" Opponent");
        System.out.println("\033[0;35m"+"1)"+"\033[0m"+" Human Opponent");
//        int Op
        System.out.println("do you want to be the black player or the white player?");
        System.out.println("\033[1;37m"+"1) the White Player");
        System.out.println("\033[0;31m"+"1) the Black Player");
        int playerType=wait.nextInt();
        while(playerType!=1||playerType!=2){
            System.out.println("Enter a valid number");
            playerType=wait.nextInt();
        }
        if(playerType==1){

        }else{
            
        }
        System.out.println("how do you want your disk:");
        System.out.println("1) "+"\u2022");
        System.out.println("this is the pcPlayer Version");
        System.out.println("the AI is supposted to be the black player, after you can change it mannually");
        PcPlayer playerBlack;
        Player playerWhite;
        //inja farz shode ke black ai hast
        playerBlack= new PcPlayer(TYPE.BLACK);
        playerWhite= new Player(TYPE.WHITE);
        Board myOthelloBoard=new Board();

        //---------------
        String nextMoveWhite;
        while(myOthelloBoard.gameEnd()==false){
            //first move to black pawn
            System.out.printf("TURN OF BLACK: \n");
            playerBlack.nextMoveUpdate(myOthelloBoard);
            myOthelloBoard.printBoard(playerBlack.getMovesAvailable(), playerBlack.getTypeOfPlayer());
            playerBlack.decideNextMove(myOthelloBoard);
            myOthelloBoard.printStableDisks(TYPE.BLACK);

            int newX=0, newY=0;
            if(playerWhite.nextMoveUpdate(myOthelloBoard)!=0) {
                System.out.println("TURN OF WHITE:");
                myOthelloBoard.printBoard(playerWhite.getMovesAvailable(), playerWhite.getTypeOfPlayer());
                boolean validInput = false;
                int numOfWrongInputs = 0;
                do {
                    if (numOfWrongInputs > 0) {
                        System.out.printf("you have to enter a valid direction of block, pay atention to the blocks filled with:");
                        System.out.printf("\033[1;37m" + "x\n" + "\033[0m");
                        validInput = false;
                    }

                    do {
                        nextMoveWhite = wait.nextLine();
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
                    } while (!validInput);
                    numOfWrongInputs++;
                } while (!playerWhite.canAddDisk(newX, newY, myOthelloBoard));
                playerWhite.addDisk(newX, newY, myOthelloBoard);
            }
            else{
                System.out.println("White cannot play\nPASS");
            }
        }




        System.out.printf("----------------\nend of game--------------\n");
        myOthelloBoard.printBoard( new ArrayList<Place>(), playerBlack.getTypeOfPlayer());
        myOthelloBoard.printInfo();
        //---------------
    }
}
