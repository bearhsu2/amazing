package idv.kuma.amazing.register.service;

public class DuplicatedRecordException extends Exception {
    public DuplicatedRecordException(String message) {
        super(message);
    }
}
