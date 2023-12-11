package ru.fixedfox.thetwentyfourtyeightgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.util.Arrays.asList;
import static java.util.Collections.reverse;

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
        board.fillBoard(asList(
                null, null, null, null,
                null, null, null, null,
                null, null, null, null,
                null, null, null, null));
        addItem();
        addItem();
    }

    @Override
    public boolean canMove() { //TODO: Нужна проверка по по пустым ячейкам и по возможности слияния
        return !board.availableSpace().isEmpty();
    }

    @Override
    public boolean move(Direction direction) {
        List<Key> keyBuffer = new ArrayList<Key>();
        var notRotatedBoard = new ArrayList<Integer>();
        var resultBoard = new ArrayList<Integer>();
        List<Integer> bufferLine = new ArrayList<>();
        switch (direction) {
            case LEFT:
                for (int i = 0; i < GAME_SIZE; i++) {
                    resultBoard.addAll(helper.moveAndMergeEqual(board.getValues(board.getRow(i))));
                }
                board.fillBoard(resultBoard);
                break;
            case RIGHT:
                for (int i = 0; i < GAME_SIZE; i++) {
                    reverse(keyBuffer = board.getRow(i));
                    bufferLine = helper.moveAndMergeEqual(board.getValues(keyBuffer));
                    reverse(bufferLine);
                    resultBoard.addAll(bufferLine);
                }
                board.fillBoard(resultBoard);
                break;
            case UP:
                for (int i = 0; i < GAME_SIZE; i++) {
                    notRotatedBoard.addAll(helper.moveAndMergeEqual(board.getValues(board.getColumn(i))));
                }
                for (int i = 0; i < GAME_SIZE; i++) {
                    for (int j = 0; j < GAME_SIZE; j++) {
                        resultBoard.add(notRotatedBoard.get(j * 4 + i));
                    }
                }
                board.fillBoard(resultBoard);
                break;
            case DOWN:
                for (int i = 0; i < GAME_SIZE; i++) {
                    reverse(keyBuffer = board.getColumn(i));
                    bufferLine = helper.moveAndMergeEqual(board.getValues(keyBuffer));
                    reverse(bufferLine);
                    notRotatedBoard.addAll(bufferLine);
                }
                for (int i = 0; i < GAME_SIZE; i++) {
                    for (int j = 0; j < GAME_SIZE; j++) {
                        resultBoard.add(notRotatedBoard.get(j * 4 + i));
                    }
                }
                board.fillBoard(resultBoard);
                break;
        }
        addItem();
        return true;
    }

    @Override
    public void addItem() {
        Key newItemKey;
        do {
            newItemKey = new Key(random.nextInt(GAME_SIZE), random.nextInt(GAME_SIZE));
        } while (null != board.getValue(newItemKey));
        if (random.nextInt(10) < 9) {
            board.addItem(newItemKey, 2);
        } else {
            board.addItem(newItemKey, 4);
        }
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
