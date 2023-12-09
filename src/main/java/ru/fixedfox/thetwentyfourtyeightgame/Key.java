package ru.fixedfox.thetwentyfourtyeightgame;

public class Key {
    int i;
    int j;

    /**
     * Конструктор класса Key
     * На входе имеет два параметра - это координаты поля.
     */
    public Key(int i, int j) {
        this.i = i;
        this.j = j;
    }

    /**
     * Геттер параметра I
     * Возвращает значение поля I ключа
     */
    public int getI() {
        return i;
    }

    /**
     * Геттер параметра j
     * Возвращает значение поля j ключа
     */
    public int getJ() {
        return j;
    }
}

