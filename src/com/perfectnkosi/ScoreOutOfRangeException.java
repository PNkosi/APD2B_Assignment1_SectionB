package com.perfectnkosi;

public class ScoreOutOfRangeException extends Exception {

    public ScoreOutOfRangeException() {
        super("Score out of legal range.");
    }

    public ScoreOutOfRangeException(String message) {
        super(message);
    }
}
