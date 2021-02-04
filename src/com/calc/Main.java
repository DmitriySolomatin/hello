package com.calc;
import java.util.Scanner;

public class Main {



    public static void main(String[] args) {



        Scanner in = new Scanner(System.in);
        System.out.print("Input a number: ");
        String str = in.nextLine();
        in.close();

        Parser Values = new Parser(str);

        Calculate myCalc = new Calculate(Values);

        System.out.println(myCalc.CalcExpression());


    }



}

