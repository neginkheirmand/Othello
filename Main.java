 package ir.ac.aut;

 import ir.ac.aut.Board;

 import java.util.ArrayList;
 import java.util.Scanner;

 /**
  * an enum which will help to transform the number to the Capital letter in the top of the board
  */
 enum X{
     A,
     B,
     C,
     D,
     E,
     F,
     G,
     H;

     /**
      * a method which will return a String holding the specifiend char for the input integer
      * @param xNum
      * @return
      */
     public static String valueOfInt(int xNum){
       for(int i=0; i<X.values().length; i++){
           if(X.values()[i].ordinal()==xNum){
               return X.values()[i]+"";
           }
       }
       return "";
     }
 }


 /**
  * This is the Main class
  * @author negin
  * @version 1.0
  */
 public class Main {
    public static void main(String args[]){
        new Run();
    }
 }
