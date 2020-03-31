 package ir.ac.aut;

//BLACK = 0
//WHITE = 1
enum TYPE{
    BLACK,
    WHITE;

    public static TYPE getOtherTYPE(TYPE thisTYPE){
        if(thisTYPE.equals(TYPE.BLACK)){
            return TYPE.WHITE;
        }
        return TYPE.BLACK;
    }
}

public class Place {
    private TYPE type;
    private boolean isFull;
    private final int y;
    private final int x;
    public Place(int x, int y){
        this.x=x;
        this.y=y;
        isFull=false;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public boolean isFull(){ 
        return isFull;
    }

    public void  hollow(){
        isFull=false;
        type=null;
        return;
    }

    public void fillBlock(TYPE typeOfBlock){
        isFull=true;
        type=typeOfBlock;
        return;
    }

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

    public void printInfo(){
        System.out.println("  this is my y "+(y+1)+"   this is my x "+ X.valueOfInt(x));
        System.out.println("i am full: "+ isFull);
    }
    
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
}
