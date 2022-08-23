package com.lind.common.core.captcha;


import com.googlecode.aviator.AviatorEvaluator;

public abstract class ArithmeticCaptchaAbstract extends Captcha {
    private String arithmeticString;
    protected static int difficulty = 10;
    protected static int algorithmSign = 4;

    public ArithmeticCaptchaAbstract() {
        this.setLen(2);
    }

    protected char[] alphas() {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < this.len; ++i) {
            sb.append(num(difficulty));
            if (i < this.len - 1) {
                int type = num(1, algorithmSign);
                if (type == 1) {
                    sb.append(Symbol.ADD.getValue());
                } else if (type == 2) {
                    sb.append(Symbol.SUB.getValue());
                } else if (type == 3) {
                    sb.append(Symbol.MUL.getValue());
                }
            }
        }

        this.chars = String.valueOf(AviatorEvaluator.execute(sb.toString().replace("x", "*")));
        sb.append("=?");
        this.arithmeticString = sb.toString();
        return this.chars.toCharArray();
    }

    public String getArithmeticString() {
        this.checkAlpha();
        return this.arithmeticString;
    }

    public void setArithmeticString(String arithmeticString) {
        this.arithmeticString = arithmeticString;
    }

    public void setDifficulty(int difficulty) {
        ArithmeticCaptchaAbstract.difficulty = difficulty;
    }

    public void supportAlgorithmSign(int algorithmSign) {
        ArithmeticCaptchaAbstract.algorithmSign = algorithmSign;
    }
}
