package com.highspot.mixtape.takehome.exceptions;

/**
 * exception indicate play list is empty.
 */
public class EmptyPlayListException extends Exception {
    /**
     * constructor.
     * @param errorMsg error message.
     */
    public EmptyPlayListException(final String errorMsg) {
        super(errorMsg);
    }
}
