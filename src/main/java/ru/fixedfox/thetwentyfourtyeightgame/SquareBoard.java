package ru.fixedfox.thetwentyfourtyeightgame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class SquareBoard extends Board {

    public SquareBoard(int size) {
        super(size, size);
    }

    /**
     * Заполняем поле элементами из входного списка.
     * Если нужно задать пустой элемент, указываем null.
     */
    @Override
    public void fillBoard(List<Integer> list) {
        Iterator<Integer> bufferOfValue = list.iterator();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (bufferOfValue.hasNext()) {
                    addItem(new Key(i, j), bufferOfValue.next());
                }
            }
        }
    }

    /**
     * Возвращаем все ключи, у которых значение null.
     */
    @Override
    public List<Key> availableSpace() {
        var listOfEmptyCells = new ArrayList<Key>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                var a = getValue(getKey(i, j));
                if (a == null) {
                    listOfEmptyCells.add(getKey(i, j));
                }
            }
        }
        return listOfEmptyCells;
    }

    /**
     * Добавляем элемент {@param value} по ключу {@param key}.
     */
    @Override
    public void addItem(Key key, Integer value) {
        board.put(key, value);
    }

    /**
     * Ищем уже существующий ключ по координатам {@param i} {@param j}.
     */
    @Override
    public Key getKey(int i, int j) {
        if (board.containsKey(new Key(i, j)) == true) {
            return new Key(i, j);
        } else {
            return null;
        }
    }

    /**
     * Получаем значение по {@param key}
     */
    @Override
    public Integer getValue(Key key) {
        return board.get(key);
    }

    /**
     * Получаем столбец ключей, по которым потом можно будет выбрать значения.
     */
    @Override
    public List<Key> getColumn(int i) {
        var resultList = new ArrayList<Key>();
        for (int j = 0; j < height; j++) {
            resultList.add(getKey(j, i));
        }
        return resultList;
    }

    /**
     * Получаем строку ключей, по которым потом можно будет выбрать значения.
     */
    @Override
    public List<Key> getRow(int j) {
        var resultList = new ArrayList<Key>();
        for (int i = 0; i < height; i++) {
            resultList.add(getKey(j, i));
        }
        return resultList;
    }

    /**
     * Проверяем, есть ли такое значение на поле.
     */
    @Override
    public boolean hasValue(Integer value) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                var a = value;
                var b = board.get(getKey(i, j));
                if (a == b) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Получаем строку значений по строке ключей.
     */
    @Override
    public List<Integer> getValues(List<Key> keys) {
        var resultList = new ArrayList<Integer>();
        for (Key key : keys) {
            resultList.add(board.get(key));
        }
        return resultList;
    }
}
