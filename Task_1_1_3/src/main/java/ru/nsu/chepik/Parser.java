package ru.nsu.chepik;

import java.util.Objects;

/**
 * Класс парсера.
 */
public class Parser {
    int pos = 0, flag_min = 0, count;
    String token, rT;

    /**
     * Является ли символ числом.
     *
     * @param symbol проверяемый символ.
     * @return вердикт проверки.
     */
    private boolean isDigit(char symbol) {
        return symbol >= '0' && symbol <= '9';
    }

    /**
     * Является ли символ буквой латинского алфавита или числом.
     *
     * @param symbol проверяемый символ.
     * @return вердикт проверки.
     */
    private boolean isNumOrVar(char symbol) {
        return symbol >= '0' && symbol <= '9' || symbol >= 'a' && symbol <= 'z' || symbol >= 'A' && symbol <= 'Z';
    }

    /**
     * Считать токен.
     *
     * @param text текст, с которым работает парсер.
     * @param flag флаг для отрицательного числа.
     * @return считанный токен.
     */
    private String readToken(String text, boolean flag) {
        count = 0;
        while (pos + count < text.length() && text.charAt(pos + count) == ' ') {
            count++;
        }

        pos += count;

        if (pos >= text.length()) {
            token = "";

            return token;
        }

        if (text.charAt(pos) == '-') {
            if (flag || pos - count == 0) {
                token = "" + text.charAt(pos++);

                if (token.length() > 1) {
                    token += token.substring(1);
                }

                while (pos < text.length() && text.charAt(pos) == ' ') {
                    pos++;
                }

                if (pos < text.length() && text.charAt(pos) == '(') {
                    flag_min = 1;
                    token = "-1";

                    return token;
                }

                int left = pos;
                if (pos < text.length() && text.charAt(pos) == '0') {
                    token = "0";
                    pos++;

                    return token;
                }


                while (isNumOrVar(text.charAt(pos))) {
                    pos++;
                }

                token = token.charAt(0) + text.substring(left, pos);

                return token;
            } else {
                token = "" + text.charAt(pos++);

                return token;
            }
        } else if (text.charAt(pos) == '+' || text.charAt(pos) == '*' || text.charAt(pos) == '/' || text.charAt(pos) == '(' || text.charAt(pos) == ')') {
            token = "" + text.charAt(pos++);

            return token;
        }

        int left = pos;

        while (pos < text.length() && isNumOrVar(text.charAt(pos))) {
            pos++;
        }

        token = text.substring(left, pos <= text.length() ? pos : pos - 1);

        return token;
    }

    /**
     * То же, что и readToken, только без сдвига текущей позиции.
     *
     * @param text текст, с которым работает парсер.
     * @param flag флаг для отрицательного числа.
     * @return считанный токен.
     */
    private String peekToken(String text, boolean flag) {
        int oldPos = pos;

        rT = readToken(text, flag);
        pos = oldPos;

        return token;
    }

    /**
     * Парсинг переменной или константы.
     *
     * @param text текст, с которым работает парсер.
     * @param flag флаг для отрицательного числа.
     * @return считанный токен.
     */
    private Expression parseAtom(String text, boolean flag) {
        rT = peekToken(text, flag);

        if (!rT.isEmpty() && rT.charAt(0) == '(') {
            rT = readToken(text, flag);
            Expression first = parseExpr(text, true);
            rT = readToken(text, false);

            return first;
        } else {
            rT = readToken(text, flag);

            if (flag_min == 0) {
                if (!token.isEmpty() && (isDigit(token.charAt(0)) || token.charAt(0) == '-')) {
                    return new Number(Integer.parseInt(token));
                }

                if (!token.isEmpty() && isNumOrVar(token.charAt(0))) {
                    return new Variable(token);
                }

                return new Number(0);
            } else {
                flag_min = 0;

                return new Number(-1);
            }
        }
    }

    /**
     * Парсинг произведения.
     *
     * @param text текст, с которым работает парсер.
     * @param flag флаг для отрицательного числа.
     * @return считанный токен.
     */
    private Expression parseMonome(String text, boolean flag) {
        char oper;
        Expression first = parseAtom(text, flag);
        rT = peekToken(text, false);

        if (!rT.isEmpty() && (rT.charAt(0) == '*' || rT.charAt(0) == '/' || (rT.charAt(0) == '(' && Objects.equals(first, new Number(-1))))) {
            String nowString = readToken(text, false);
            Expression second = parseAtom(text, false);

            if (!nowString.isEmpty()) {
                oper = nowString.charAt(0);

                if (oper == '*' || oper == '(') {
                    if (Objects.equals(first, new Number(0)) || Objects.equals(second, new Number(0))) {
                        first = new Number(0);
                    }

                    else {
                        first = new Mul(first, second);
                    }
                } else {
                    first = new Div(first, second);
                }
            }
        }

        rT = peekToken(text, false);

        while (!rT.isEmpty() && (rT.charAt(0) == '*' || rT.charAt(0) == '/')) {
            String nowString = readToken(text, false);
            Expression second = parseAtom(text, false);

            if (!nowString.isEmpty()) {
                oper = nowString.charAt(0);

                if (oper == '*') {
                    if (Objects.equals(first, new Number(0)) || Objects.equals(second, new Number(0))) {
                        first = new Number(0);
                    }

                    else {
                        first = new Mul(first, second);
                    }
                } else {
                    first = new Div(first, second);
                }
            }

            rT = peekToken(text, false);
        }

        return first;
    }

    /**
     * Парсинг алгебраической суммы.
     *
     * @param text текст, с которым работает парсер.
     * @param flag флаг для отрицательного числа.
     * @return считанный токен.
     */
    private Expression parseExpr(String text, boolean flag) {
        char oper;
        Expression first = parseMonome(text, flag);
        rT = peekToken(text, false);

        while (!rT.isEmpty() && (rT.charAt(0) == '+' || rT.charAt(0) == '-')) {
            String nowString = readToken(text, false);
            Expression second = parseMonome(text, false);

            if (!nowString.isEmpty()) {
                oper = nowString.charAt(0);

                if (oper == '+') {
                    first = new Add(first, second);
                } else {
                    first = new Sub(first, second);
                }
            }

            rT = peekToken(text, false);
        }

        return first;
    }

    /**
     * Публичный метод для парсинга.
     *
     * @param express строка парсера.
     * @return выражение.
     */
    public Expression parse(String express) {
        express = express.replace("-", "- 1*");

        return parseExpr(express, false);
    }
}
