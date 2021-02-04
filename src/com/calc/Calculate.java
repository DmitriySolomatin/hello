package com.calc;

public class Calculate {
    String mathSymbol;
    int arabicDigitOne;
    int arabicDigitTwo;
    boolean romanType;
    int result;

    Calculate(Parser Values) {
        mathSymbol = Values.mathSymbol;
        arabicDigitOne = Values.arabicDigitOne;
        arabicDigitTwo = Values.arabicDigitTwo;
        if (Values.romanDigitOne != null && Values.romanDigitTwo != null) {
            romanType = true;
        }

    }

    public String CalcExpression() {
        result = 0;
        switch (mathSymbol.charAt(0)) {
            case '+':
                result = arabicDigitOne + arabicDigitTwo;
                break;
            case '-':
                result = arabicDigitOne - arabicDigitTwo;
                break;
            case '*':
                result = arabicDigitOne * arabicDigitTwo;
                break;
            case '/':
                result = arabicDigitOne / arabicDigitTwo;
                break;
        }

        String resultString;
                if (romanType) {
            resultString = Parser.convertToRoman(result);
        } else {
            resultString = String.valueOf(result);
        }

        return resultString;
    }

}
