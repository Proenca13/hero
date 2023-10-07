import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Element {
    private Position position;
    public Element(int x_,int y_){
        position = new Position(x_,y_);
    }
    public Position getPosition(){
        return position;
    }
    public void setPosition(Position p1){
        position.setX(p1.getX());
        position.setY(p1.getY());
    }
    public int getX(){return position.getX();}
    public int getY(){return position.getY();}
    abstract void draw(TextGraphics graphics);

}
