package com.wmj.mmall.exception;

import com.wmj.mmall.enums.ResultEnum;

/**
 * unchecked 的异常 不需要手动的去抛 即 try/cath 继承RuntimeException的异常是这个
 * checked  是需要手动去抛的异常 直接继承 Exception
 */

public class MmallException extends RuntimeException{
    public MmallException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
    }
}
