import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Arena {
    private int width;
    private int height;
    private Hero hero;
    private List<Wall> walls;
    public Arena(int col, int row, Hero hero1){
        height = row;
        width = col;
        hero = hero1;
        this.walls = createWalls();

    }
    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
    }
    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#e2725b"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new
                TerminalSize(width, height), ' ');
        hero.draw(graphics);
        for (Wall wall : walls) wall.draw(graphics);
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
        if ((position.getX()>=width || position.getX()< 0) || (position.getY()>=height || position.getY()< 0)){
            return false;
        }
        for (Wall wall : walls){
        if (wall.getPosition().equals(position)){
            return false;
        }}
        return true;
    }

}
