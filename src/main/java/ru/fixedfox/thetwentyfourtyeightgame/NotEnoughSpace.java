package ru.fixedfox.thetwentyfourtyeightgame;

public class NotEnoughSpace extends Exception{
    public NotEnoughSpace() {super();};

    public NotEnoughSpace(String message) { super(message);}
}
