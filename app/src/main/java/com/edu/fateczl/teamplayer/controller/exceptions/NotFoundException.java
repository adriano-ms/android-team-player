package com.edu.fateczl.teamplayer.controller.exceptions;

import com.edu.fateczl.teamplayer.R;

/**
 * @author Adriano M Sanchez
 */
public class NotFoundException extends RuntimeException {

    private final int messageStringCode;

    public NotFoundException(){
        messageStringCode = R.string.not_found_msg;
    }

    public int getMessageStringCode(){
        return messageStringCode;
    }
}
