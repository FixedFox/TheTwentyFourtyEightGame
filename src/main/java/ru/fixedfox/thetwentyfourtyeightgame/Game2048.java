package ru.fixedfox.thetwentyfourtyeightgame;

import java.util.Random;

public class Game2048 implements Game {
    GameHelper helper = new GameHelper();
    Random random = new Random();
    Board board;

    public Game2048(Board board) {
        this.board = board;
    }

    @Override
    public void init() {
    }

    @Override
    public boolean canMove() {
        return false;
    }

    @Override
    public boolean move(Direction direction) {
        return false;
    }

    @Override
    public void addItem() {

    }

    @Override
    public Board getGameBoard() {
        return null;
    }

    @Override
    public boolean hasWin() {
        return false;
    }
}
