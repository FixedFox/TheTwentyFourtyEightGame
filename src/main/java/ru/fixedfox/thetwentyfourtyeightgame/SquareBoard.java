package ru.fixedfox.thetwentyfourtyeightgame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SquareBoard<V> extends Board<Key,V>  {

    public SquareBoard(int size) {
        super(size, size);
    }

    /**
     * Заполняем поле элементами из входного списка.
     * Если нужно задать пустой элемент, указываем null.
     */
    @Override
    public void fillBoard(List<V> list) {
        try {
            if (list.size() > 16) {
                throw new RuntimeException();
            }
        } catch (RuntimeException e) {
            throw new ExceptionInInitializerError();
        }
        Iterator<V> bufferOfValue = list.iterator();
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
        if( board.isEmpty()){
            return listOfEmptyCells;
        }

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
    public void addItem(Key key, V value) {
        board.put(key, value);
    }

    /**
     * Ищем уже существующий ключ по координатам {@param i} {@param j}.
     */
    @Override
    public Key getKey(int i, int j) {
        if (board.containsKey(new Key(i, j))) {
            return new Key(i, j);
        } else {
            return null;
        }
    }

    /**
     * Получаем значение по {@param key}
     */
    @Override
    public V getValue(Key key) {
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
    public boolean hasValue(V value) {
        return board.containsValue(value);
    }

    /**
     * Получаем строку значений по строке ключей.
     */
    @Override
    public List<V> getValues(List<Key> keys) {
        var resultList = new ArrayList<V>();
        for (Key key : keys) {
            resultList.add(board.get(key));
        }
        return resultList;
    }
}
