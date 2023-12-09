package ru.fixedfox.thetwentyfourtyeightgame;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Key)) return false;
        Key key = (Key) o;
        return getI() == key.getI() && getJ() == key.getJ();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getI(), getJ());
    }
}

