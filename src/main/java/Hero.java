import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class Hero extends Element{

    public Hero(int x_, int y_){
        super(x_,y_);
    }
    public Position moveUp() {
        return new Position(super.getX(), super.getY() - 1);
    }
    public Position moveDown() {return new Position(super.getX(), super.getY() + 1);}
    public Position moveRight() {
        return new Position(super.getX() + 1, super.getY());
    }
    public Position moveLeft() {
        return new Position(super.getX() - 1, super.getY());
    }
    @Override
    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#800080"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(super.getX(), super.getY()), "X");

    }

}
