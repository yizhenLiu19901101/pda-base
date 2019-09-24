package com.jiaxin.pda.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义异常
 * @author milo
 */
public class PDAException extends  RuntimeException{

    private static final Logger logger = LoggerFactory.getLogger(PDAException.class);

    public PDAException() {
        super();
    }

    public PDAException(String message) {
        super(message);
    }

    public PDAException(String message, Throwable cause) {
        super(message, cause);
    }

    public PDAException(Throwable cause) {
        super(cause);
    }

}
