package ru.fixedfox.thetwentyfourtyeightgame;

import java.util.Random;

public class Game2048 implements Game {
    GameHelper helper = new GameHelper();
    Random random = new Random();
    private final Board<Key, Integer> board = new SquareBoard<>(GAME_SIZE);

    public static final int GAME_SIZE = 4;

    public Game2048() {
    }

    @Override
    public void init() {
        var game2048 = new Game2048();
    }

    @Override
    public boolean canMove() { //TODO: Нужна проверка по по пустым ячейкам и по возможности слияния
        return !board.availableSpace().isEmpty();
    }

    @Override
    public boolean move(Direction direction) {
        switch (direction) {
            case LEFT:
                break;
            case RIGHT:
                break;
            case UP:
                break;
            case DOWN:
                break;
        }
        return false;
    }

    @Override
    public void addItem() {
    }

    @Override
    public Board getGameBoard() { //TODO: Row use, надо подумать, что с этим сделать.
        return board;
    }

    @Override
    public boolean hasWin() {
        return board.hasValue(2048);
    }
}
