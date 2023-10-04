import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

public class Hero {
    private Position position;
    public Hero(int x_, int y_){
        position = new Position(x_,y_);
    }
    public Position get_position(){
        return position;
    }
    public void setPosition(Position p1){
        position.setX(p1.getX());
        position.setY(p1.getY());
    }
    public Position moveUp() {
        return new Position(position.getX(), position.getY() - 1);
    }
    public Position moveDown() {
        return new Position(position.getX(), position.getY() + 1);
    }
    public Position moveRight() {
        return new Position(position.getX() + 1, position.getY());
    }
    public Position moveLeft() {
        return new Position(position.getX() - 1, position.getY());
    }
    public void draw(Screen screen){
        screen.setCharacter(position.getX(), position.getY(), TextCharacter.fromCharacter('X')[0]);
    }

}
