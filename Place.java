 package ir.ac.aut;

//BLACK = 0
//WHITE = 1
enum TYPE{
    BLACK,
    WHITE;

    /**
     * a method for returning the TYPE opposite to the one passed to it
     * @param thisTYPE
     * @return
     */
    public static TYPE getOtherTYPE(TYPE thisTYPE){
        if(thisTYPE.equals(TYPE.BLACK)){
            return TYPE.WHITE;
        }
        return TYPE.BLACK;
    }
}

 /**
  * This is a model class to hold place information
  *
  * @author negin
  * @version 1.0
  */
public class Place {
    //the TYPE of disk in this block(if a disk is on it)
    private TYPE type;

    // boolean to specify if this block is full with a disk on it or not
    private boolean isFull;

    //a final field to specify the x of the block
    private final int y;

    //a final field to specify the x of the block
    private final int x;

     /**
      * constructor of the class (this constructor must be used for the declaration of empty blocks)
      * @param x
      * @param y
      */
    public Place(int x, int y){
        this.x=x;
        this.y=y;
        isFull=false;
    }

     /**
      * constructor of the class (this constructor must be used for the declaration of full blocks)
      * @param x
      * @param y
      * @param typeDiskHere type of the disk in this block
      */
    public Place(int x, int y, TYPE typeDiskHere){
        this.x=x;
        this.y=y;
        isFull=true;
        type=typeDiskHere;
    }

     /**
      * a getter method fot the x field
      * @return x
      */
    public int getX(){
        return x;
    }

     /**
      * a getter method fot the y field
      * @return y
      */
    public int getY(){
        return y;
    }

     /**
      * a method to see if the block is full
      * @return true if its full and false if else
      */
    public boolean isFull(){ 
        return isFull;
    }

     /**
      * a method to empty this block
      */
    public void  hollow(){
        isFull=false;
        type=null;
        return;
    }

     /**
      * a method to fill the block with a disk of TYPE typeOfBlock
      * @param typeOfBlock
      */
    public void fillBlock(TYPE typeOfBlock){
        isFull=true;
        type=typeOfBlock;
        return;
    }

     /**
      * a method for changing the color of the disk in this block
      */
    public void changeDisk(){
        if(isFull==true) {
            if (this.type == TYPE.WHITE) {
                this.type = TYPE.BLACK;
            } else {
                type = TYPE.WHITE;
            }
        }
        return;
    }

     /**
      * a method to change the color of the fisk in this block to the specified color
      * @param type
      */
    public void changeToType(TYPE type){
        this.type=type;
        return;
    }

     /**
      * a method for printing info about the block
      */
    public void printInfo(){
        System.out.println("  this is my y "+(y+1)+"   this is my x "+ X.valueOfInt(x));
        System.out.println("i am full: "+ isFull);
    }

     /**
      * a method to return the TYPE of this block in String type
      * @return the type of disk in this block
      */
    public String giveType(){
        if(isFull==false){
            return "notfilled";
        }
        else if(type==TYPE.BLACK){
            return "BLACK";
        }else{
            return "WHITE";
        }
    }

     /**
      * a getter method for the TYPE field
      * @return
      */
    public TYPE getType(){
        return this.type;
    }
}
