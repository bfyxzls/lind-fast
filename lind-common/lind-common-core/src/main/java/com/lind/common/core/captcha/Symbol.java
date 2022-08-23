package com.lind.common.core.captcha;


public enum Symbol {
    NUM('n', false),
    ADD('+', false),
    SUB('-', false),
    MUL('x', true),
    DIV('÷', true);

    private final char value;
    private final boolean priority;

    public static Symbol of(char c) {
        Symbol[] values = values();
        Symbol[] var2 = values;
        int var3 = values.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Symbol value = var2[var4];
            if (value.value == c) {
                return value;
            }
        }

        throw new IllegalArgumentException("不支持的标识符，仅仅支持(+、-、×、÷)");
    }

    public char getValue() {
        return this.value;
    }

    public boolean isPriority() {
        return this.priority;
    }

    private Symbol(char value, boolean priority) {
        this.value = value;
        this.priority = priority;
    }
}
