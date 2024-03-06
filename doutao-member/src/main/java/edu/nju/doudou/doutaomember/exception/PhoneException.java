package edu.nju.doudou.doutaomember.exception;

public class PhoneException extends RuntimeException {

    public PhoneException() {
        super("存在相同的手机号");
    }
}
