import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Arena {
    private int width;
    private int height;
    private Hero hero;
    public Arena(int col, int row, Hero hero1){
        height = row;
        width = col;
        hero = hero1;
    }
    public void draw(Screen screen){
        hero.draw(screen);
    }
    public boolean processKey(KeyStroke key, Screen screen) throws IOException {
        System.out.println(key);
        if (key.getKeyType() == KeyType.ArrowUp){
            moveHero(hero.moveUp());
        }
        else if (key.getKeyType() == KeyType.ArrowDown){
            moveHero(hero.moveDown());
        }
        else if (key.getKeyType() == KeyType.ArrowRight){
            moveHero(hero.moveRight());
        }
        else if (key.getKeyType() == KeyType.ArrowLeft){
            moveHero(hero.moveLeft());
        }
        else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q'){
            screen.close();
        } else if (key.getKeyType() == KeyType.EOF) {
            return false;
        }
        return true;
    }
    public void moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
    }
    public boolean canHeroMove(Position position){
        if ((position.getX()>=width || position.getX()< 0) && (position.getX()>=height || position.getX()< 0)){
            return false;
        }
        return true;
    }
}
