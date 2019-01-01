package com.kentwen.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by 毕文 on 2018/12/27.
 */
@Getter
@Setter
public class UserNotExistException extends RuntimeException {

//    private static final long ser

    private String id;

    public UserNotExistException(String id) {
        super("user not exist");
        this.id = id;
    }
}
