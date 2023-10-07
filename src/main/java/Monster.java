import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public class Monster extends Element{
    public Monster(int x,int y){super(x,y);}
    public Position move(){
        Random random = new Random();
        int direction = random.nextInt(4);
        return switch (direction) {
            case 0 -> new Position(super.getX(), super.getY() - 1);
            case 1 -> new Position(super.getX(), super.getY() + 1);
            case 2 -> new Position(super.getX() - 1, super.getY());
            case 3 -> new Position(super.getX() + 1, super.getY());
            default -> null;
        };
    }
    @Override
    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#0000FF"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(super.getX(), super.getY()), "M");

    }

}