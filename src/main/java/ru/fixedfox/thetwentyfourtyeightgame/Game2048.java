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
        new Game2048();
        board.fillBoard(asList(
                null, null, null, null,
                null, null, null, null,
                null, null, null, null,
                null, null, null, null));
        addItem();
        addItem();
    }

    @Override
    public boolean canMove() {
        if (!board.availableSpace().isEmpty()) {
            return true;
        }
        for (int i = 0; i < GAME_SIZE; i++) {
            if (hasMatch(board.getValues(board.getRow(i))) || hasMatch(board.getValues(board.getColumn(i)))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean move(Direction direction) {
        List<Key> keyBuffer;
        var notRotatedBoard = new ArrayList<Integer>();
        var resultBoard = new ArrayList<Integer>();
        List<Integer> bufferLine;
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
                resultBoard = (ArrayList<Integer>) rotate90Deg(notRotatedBoard);
                board.fillBoard(resultBoard);
                break;
            case DOWN:
                for (int i = 0; i < GAME_SIZE; i++) {
                    reverse(keyBuffer = board.getColumn(i));
                    bufferLine = helper.moveAndMergeEqual(board.getValues(keyBuffer));
                    reverse(bufferLine);
                    notRotatedBoard.addAll(bufferLine);
                }
                resultBoard = (ArrayList<Integer>) rotate90Deg(notRotatedBoard);

                board.fillBoard(resultBoard);
                break;
        }
        addItem();
        return true;
    }

    @Override
    public void addItem() {
        try {
            if (!board.availableSpace().isEmpty()) {
                Key newItemKey = board.availableSpace().get(random.nextInt(board.availableSpace().size()));
                if (random.nextInt(10) < 9) {
                    board.addItem(newItemKey, 2);
                } else {
                    board.addItem(newItemKey, 4);
                }
            } else {
                throw new NotEnoughSpace("Not enough space, but game must go on!");
            }
    } catch (NotEnoughSpace e){
        System.out.println("GAME MUST GO ON");
        }
    }

    @Override
    public Board<Key, Integer> getGameBoard() {
        return board;
    }

    @Override
    public boolean hasWin() {
        return board.hasValue(2048);
    }

    private boolean hasMatch(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).equals(list.get(i + 1))) {
                return true;
            }
        }
        return false;
    }

    private List<Integer> rotate90Deg(List<Integer> list) {
        var newList = new ArrayList<Integer>();
        for (int i = 0; i < GAME_SIZE; i++) {
            for (int j = 0; j < GAME_SIZE; j++) {
                newList.add(list.get(j * 4 + i));
            }
        }
        return newList;
    }
}
