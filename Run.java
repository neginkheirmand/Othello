package ir.ac.aut;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * This class is the Run class
 * @author negin
 * @version 1.0
 */
public class Run {
    //the board in which the game is played
    private Board myOthelloBoard;

    //the user which plays the game
    private Player user;

    /**
     * the constructor of this class
     * this mehod is called in the Main class and excutes the game
     */
    public Run(){
        int[] typeInfo=startMenu();
        myOthelloBoard = new Board();

        if(typeInfo[1]==1){
            user= new Player(TYPE.WHITE);
        }else{
            user= new Player(TYPE.BLACK);
        }
        if(typeInfo[0]==1){
            //ai opponent
            runAgainstAI();
        }else{
            //Human opponent
            runAgainstHuman();
        }
    }

    /**
     * a method for take in information from human players in each round
     * @param playerTurn
     * @return an array holding the information
     */
    private int[] inputFromHuman(Player playerTurn){
        Scanner wait = new Scanner(System.in);
        String nextMovePlayer;
        int newX=0, newY=0;
        boolean validInput = false;
        int numOfWrongInputs = 0;
        do {
            if (numOfWrongInputs > 0) {
                System.out.printf("you have to enter a valid direction of block, pay attention to the blocks filled with:");
                if(playerTurn.getTypeOfPlayer().name().equals(TYPE.WHITE.name())){
                    //White Player
                    System.out.printf("\033[1;37m");
                }else{
                    //Black player
                    System.out.printf("\033[0;31m" );
                }
                System.out.printf("x\n" + "\033[0m");
                validInput = false;
            }

            do {
                nextMovePlayer = wait.nextLine();
                try {
                    newY = Integer.parseInt(nextMovePlayer.charAt(0) + "");
                    newY--;
                    newX = X.valueOf(nextMovePlayer.charAt(1) + "").ordinal();
                    System.out.println("x is = " + newX + "  y = " + newY);
                    validInput = true;
                } catch (IllegalArgumentException e) {
                    validInput = false;
                    System.out.println("the format should be IC with no space in between and the I should be a valid number");
                    System.out.println("the C should be a valid letter with Capital mode--> \"3C\"\n");
                }
            } while (!validInput);
            numOfWrongInputs++;
        } while (!playerTurn.canAddDisk(newX, newY, myOthelloBoard));
        int[] answer = {newX, newY};
        return answer;
    }

    /**
     * a method for the ai version of the game
     */
    private void runAgainstAI(){
        PcPlayer aiOpponent = new PcPlayer(TYPE.getOtherTYPE(user.typeOfPlayer));

        if (user.getTypeOfPlayer().name().equals(TYPE.BLACK.name())) {
            //the user shall begin the game
            while(myOthelloBoard.gameEnd()==false) {
                if (user.nextMoveUpdate(myOthelloBoard) != 0) {
                    System.out.printf("TURN OF BLACK: \n");
                    myOthelloBoard.printBoard(user.getMovesAvailable(), user.getTypeOfPlayer());
                    int[] input = inputFromHuman(user);
                    user.addDisk(input[0], input[1], myOthelloBoard);
                } else {
                    System.out.println("Black cannot play\n       PASS");
                }

                //--

                if (aiOpponent.nextMoveUpdate(myOthelloBoard) != 0) {
                    System.out.println("TURN OF WHITE:");
                    myOthelloBoard.printBoard(aiOpponent.getMovesAvailable(), aiOpponent.getTypeOfPlayer());
                    aiOpponent.decideNextMove(myOthelloBoard);

                } else {
                    System.out.println("White cannot play\n      PASS");
                }
            }
        }
        else{
            while(myOthelloBoard.gameEnd()==false) {
                if (aiOpponent.nextMoveUpdate(myOthelloBoard) != 0) {
                    System.out.println("TURN OF Black:");
                    aiOpponent.nextMoveUpdate(myOthelloBoard);
                    myOthelloBoard.printBoard(aiOpponent.getMovesAvailable(), aiOpponent.getTypeOfPlayer());
                    aiOpponent.decideNextMove(myOthelloBoard);

                } else {
                    System.out.println("Black cannot play\n      PASS");
                }
                if (user.nextMoveUpdate(myOthelloBoard) != 0) {
                    System.out.printf("TURN OF White: \n");
                    myOthelloBoard.printBoard(user.getMovesAvailable(), user.getTypeOfPlayer());
                    int[] input = inputFromHuman(user);
                    user.addDisk(input[0], input[1], myOthelloBoard);
                } else {
                    System.out.println("White cannot play\n       PASS");
                }

            }
        }
        System.out.printf("----------------\nend of game--------------\n");

        myOthelloBoard.printBoard(new ArrayList<Place>(), user.getTypeOfPlayer());
        myOthelloBoard.printInfo();
        System.exit(0);


    }

    /**
     * a method for the Human opponent version
     */
    private void runAgainstHuman(){
        user= new Player(TYPE.BLACK);
        Player humanOpponent=new Player(TYPE.WHITE);
            while(myOthelloBoard.gameEnd()==false) {
                if (user.nextMoveUpdate(myOthelloBoard) != 0) {
                    System.out.printf("TURN OF BLACK: \n");
                    myOthelloBoard.printBoard(user.getMovesAvailable(), user.getTypeOfPlayer());
                    int[] input = inputFromHuman(user);
                    user.addDisk(input[0], input[1], myOthelloBoard);
                } else {
                    System.out.println("Black cannot play\nPASS");
                }

                //--

                if (humanOpponent.nextMoveUpdate(myOthelloBoard) != 0) {
                    System.out.println("TURN OF WHITE:");
                    myOthelloBoard.printBoard(humanOpponent.getMovesAvailable(), humanOpponent.getTypeOfPlayer());
                    int[] input = inputFromHuman(humanOpponent);
                    humanOpponent.addDisk(input[0], input[1], myOthelloBoard);
                } else {
                    System.out.println("White cannot play\nPASS");
                }
            }
        System.out.printf("----------------\nend of game--------------\n");

        myOthelloBoard.printBoard(new ArrayList<Place>(), user.getTypeOfPlayer());
        myOthelloBoard.printInfo();
        System.exit(0);
    }

    /**
     * the menu method which will take in information about the printing way and the color of the user
     * @return an array holding the information
     */
    public static int[] startMenu(){
        int inputContainer[]=new int[3];
        Scanner wait=new Scanner(System.in);
        System.out.println("\033[0;35m"+"do you want to Play against the computer or against human opponent?");
        System.out.println("1)"+"\033[1;36m"+" AI"+"\033[0m"+" Opponent");
        System.out.println("\033[0;35m"+"2)"+"\033[0m"+" Human Opponent");
        int input= wait.nextInt();

        while(input!=1&&input!=2){
            System.out.println("Please enter a valid number");
            input= wait.nextInt();
        }
        inputContainer[0]=input;
        // inputContainer[0] = opponent brain

        System.out.println("\033[0;35m"+"do you want to be the black player or the white player?");
        System.out.println("1) "+"\033[1;37m"+"White Player");
        System.out.println("\033[0;35m"+"2)"+"\033[0;31m"+" Black Player"+"\033[0m");
        input = wait.nextInt();
        while(input!=1 && input!=2){
            System.out.println("Enter a valid number");
            input = wait.nextInt();
        }
        inputContainer[1]=input;
        // inputContainer[1] = player's color

        System.out.println("\033[0;35m"+"YOUR DISKS WILL LOOK LIKE:");
        if(input==1){
            //the user has choosen white player
            System.out.println("\033[1;37m"+"O");
        }else{
            //the user has chosen black player
            System.out.println("\033[0;31m"+"O");
        }
        return inputContainer;
    }

}







