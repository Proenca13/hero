import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {

    private Screen screen;

    private Arena arena;
    public Game() throws IOException{
        int col, row;
        col = 40;
        row = 40;
        TerminalSize terminalSize = new TerminalSize(col, row);
        DefaultTerminalFactory terminalFactory = new
                DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        Terminal terminal = terminalFactory.createTerminal();

        screen = new TerminalScreen(terminal);
        screen.setCursorPosition(null); // we don't need a cursor
        screen.startScreen(); // screens must be started
        screen.doResizeIfNecessary(); // resize screen if necessary
        arena = new Arena(col, row, new Hero(10,10));


    }
    private void draw() throws IOException{
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }
    public void run() throws IOException {
        boolean run = true;
        while(run) {
            draw();
            KeyStroke key = screen.readInput();
            run = processKey(key);
            arena.moveMonsters();
            if(arena.verifyMonsterColision()){
                System.out.println("Bateu em um monstro.GAME OVER");
                screen.close();
            }

        }
    }
    private boolean processKey(KeyStroke key) throws IOException {
        return arena.processKey(key,screen);
    }
}
