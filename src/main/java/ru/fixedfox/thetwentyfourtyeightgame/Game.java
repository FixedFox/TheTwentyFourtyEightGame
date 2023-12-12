package ru.fixedfox.thetwentyfourtyeightgame;

public interface Game {
    void init();
    boolean canMove();
    boolean move(Direction direction);
    void addItem() throws NotEnoughSpace;
    Board getGameBoard();
    boolean hasWin();
}
