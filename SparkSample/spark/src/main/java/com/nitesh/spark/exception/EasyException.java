package com.nitesh.spark.exception;

/**
 * Created by Nitesh Singh(killer) on 9/1/2016.
 */
public class EasyException extends Exception {
    public EasyException() {
    }

    public EasyException(String detailMessage) {
        super(detailMessage);
    }

    public EasyException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public EasyException(Throwable throwable) {
        super(throwable);
    }
}
