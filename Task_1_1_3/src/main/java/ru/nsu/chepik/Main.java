package ru.nsu.chepik;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Scanner scanner = new Scanner(System.in);
        //Expression e = new Div(new Number(1), new Variable("x"));
        //Expression e = new Add(new Number(3), new Mul(new Number(2), new Variable("x")));
        //e.print();

        //System.out.println("\n" + e.eval("x = 0; y = 13"));

        //String fconsole = scanner.nextLine();
        String fconsole = "x*y+z";

        System.out.print("\n\n");
        Expression ex = new Parser().parse(fconsole);
        ex.print();
        System.out.println("\n" + ex.eval("x = 5;   y=2; z = 3"));
    }
}