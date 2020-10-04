package idv.kuma.amazing.register.controller;

public enum Type {

    EMAIL(0), PHONE(1);

    public final int unique;

    private Type(int unique) {
        this.unique = unique;
    }
}
