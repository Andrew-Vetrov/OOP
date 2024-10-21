package ru.nsu.chepik;

import java.util.HashMap;
import java.util.Map;

/**
 * Основной класс выражения.
 */
public abstract class Expression {
    protected static Map<String, Integer> dict;

    /**
     * Означивание переменных.
     *
     * @return результат выражения.
     * @throws Exception возможное исключение.
     */
    protected abstract int evalMap() throws Exception;

    /**
     * Печать выражения.
     */
    public abstract void print();

    /**
     * Нахождение производной.
     *
     * @param variable дифференцируемая переменная.
     * @return выражение.
     */
    public abstract Expression derivative(String variable);

    /**
     * Создание словаря для означиваемых переменных.
     *
     * @param arg строка с переменными и их значениями.
     * @return результат выражения.
     */
    public int eval(String arg) {
        int flag = 0;
        char now;

        StringBuilder st = new StringBuilder();
        StringBuilder nm = new StringBuilder();
        String mapString = "";

        dict = new HashMap<>();

        for (int i = 0; i < arg.length(); i++) {
            now = arg.charAt(i);

            if (flag == 0 && (now >= 'a' && now <= 'z' || now >= 'A' && now <= 'Z')) {
                st = new StringBuilder();
                flag = 1;
            }

            if (flag == 1) {
                if (now >= 'a' && now <= 'z' || now >= 'A' && now <= 'Z') {
                    st.append(now);
                } else {
                    mapString = st.toString();
                    flag = 2;
                }
            }

            if (flag == 2 && now == '=') {
                flag = 3;
            }

            if (flag == 3 && (now >= '0' && now <= '9' || now == '-')) {
                nm = new StringBuilder();
                flag = 4;
            }

            if (flag == 4) {
                if (now >= '0' && now <= '9' || now == '-') {
                    nm.append(now);
                } else {
                    try {
                        dict.put(mapString, Integer.valueOf(nm.toString()));
                    } catch (Exception e) {
                        System.out.println("Exception in function Integer.valueOf(): " + e);

                        return -1;
                    }

                    flag = 5;
                }
            }

            if (flag == 5 && now == ';') {
                flag = 0;
            }
        }

        try {
            dict.put(mapString, Integer.valueOf(nm.toString()));
        } catch (Exception e) {
            System.out.println("Exception in function Integer.valueOf(): " + e);

            return -1;
        }

        try {
            return evalMap();
        } catch (Exception e) {
            System.out.println("Exception in function evalMap(): " + e);

            return -1;
        }
    }
}
