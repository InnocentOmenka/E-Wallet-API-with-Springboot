package com.innocodes.EWalletAPIwithSpringBoot.exception;

public class MailSendingException extends RuntimeException{
    public MailSendingException(String errorMessage) {
        super(errorMessage);
    }
}
