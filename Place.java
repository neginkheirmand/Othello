 package ir.ac.aut;

//BLACK = 0
//WHITE = 1
enum TYPE{
    BLACK,
    WHITE
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

    public void fillBlock(TYPE typeOfBlock){
        isFull=true;
        type=typeOfBlock;
        return;
    }

    public void printInfo(){
        System.out.println("this is my x "+ x+ "this is my y "+y);
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
