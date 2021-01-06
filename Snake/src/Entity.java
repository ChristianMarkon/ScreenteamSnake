import java.awt.*;

public class Entity {
    public int x, y;
    public String type;



    Entity(String Type, int X, int Y){
        type=Type;
        x=X;
        y=Y;
    }

    Entity( int X, int Y){
        x=X;
        y=Y;
        type="unimportant";
    }

    public int getx(){
        return x;
    }

    public int gety(){
        return y;
    }

}
