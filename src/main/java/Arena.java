import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private int width;
    private int height;
    private Hero hero;
    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;
    public Arena(int col, int row, Hero hero1){
        height = row;
        width = col;
        hero = hero1;
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonters();

    }
    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            int coin_width = random.nextInt(width-2)+1;
            int coin_height = random.nextInt(height-2)+1;
            boolean add = true;
            if (new Position(coin_width,coin_height).equals(hero.getPosition()))add = false;
            if (add) coins.add(new Coin(coin_width, coin_height));
        }
        return coins;
    }
    private List<Monster> createMonters() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < 7; i++)
            monsters.add(new Monster(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
        return monsters;
    }
    private List<Wall> createWalls()  {
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
        for (Coin coin: coins) coin.draw(graphics);
        for (Monster monster: monsters) monster.draw(graphics);
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
            retrieveCoins(position);
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
    public void retrieveCoins(Position position){
        for(Coin coin:coins){
            if(position.equals(coin.getPosition())){
                coins.remove(coin);
                return;
            }
        }
    }
    public void moveMonsters(){
        for(Monster monster :monsters){
            Position position = monster.move();
            if (canHeroMove(position))monster.setPosition(position);
        }
    }
    public boolean verifyMonsterColision( ){
        for(Monster monster :monsters){
            if(monster.getPosition().equals(hero.getPosition()))return true;
        }
        return false;
    }

}
