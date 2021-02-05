package com.calc;

public class Parser {
    String[] parts;
    String mathSymbol;
    int arabicDigitOne;
    int arabicDigitTwo;
    String romanDigitOne;
    String romanDigitTwo;

    final char[] arabicDigits = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
    final char[] romanDigits = {'I', 'V', 'X'};

    Parser(String expression) {
        prepareString(expression);
        searchMathSymbol();

        if (compareDigits(this.parts[0], arabicDigits) && compareDigits(this.parts[2], arabicDigits)) {
            setArabicDigitOne(this.parts[0]);
            setArabicDigitTwo(this.parts[2]);
        } else if (compareDigits(this.parts[0], romanDigits) && compareDigits(this.parts[2], romanDigits)) {
            setRomanDigitOne(this.parts[0]);
            setRomanDigitTwo(this.parts[2]);
        } else {
            throw new IllegalArgumentException("Illegal Argument");
        }

    }

    private void prepareString(String str) {
        //Разрезаем по пробелам, создаём массив подстрок
        String[] parts = str.split(" ");

        //Количество элементов должно быть равно 3 (число, знак, число)
        if (parts.length > 3) {
            throw new IllegalArgumentException("Illegal Argument:" + str);
        }

        this.parts = parts;

    }

    private void searchMathSymbol () {
        final char[] mathSymbols = {'+', '-', '*', '/'};
        if (this.parts[1].length() > 1) {
            throw new IllegalArgumentException("Illegal Argument:" + this.parts[1]);
        }
        boolean firstEqual = false;
        for (char symbol : mathSymbols) {
            if (symbol == this.parts[1].charAt(0)) { // Если длина второго символа не 1 или такой знак не зарегистрирован
                firstEqual = true;
                break;
            }
        }
        if (firstEqual) {
            this.mathSymbol = this.parts[1];
        } else {
            throw new IllegalArgumentException("Illegal Argument:" + this.parts[1]);
        }
    }

    private static boolean compareDigits (String digitString, char[] digitPattern) {
        boolean singleType = true;

        char[] digitsArray = digitString.toCharArray(); // Разбиваем строку на символы

        for (char digit : digitsArray) { // Каждый символ сверяем с паттерном
            boolean digitSingleType = false;
            for (char symbol : digitPattern) {
                if (symbol == digit) {
                    digitSingleType = true;
                }
            }
            singleType &= digitSingleType; // Если хоть один символ не совпал с патерном, то ошибка
        }

        return singleType;

    }

    // !!! Ниже идут два одинаковых метода, т.к. я не разобрался как передать в метод ссылку на параметр или как конвертировать строку в параметр.
    public void setArabicDigitOne (String digitString) {
        int digitValue = Integer.parseInt(digitString); //Конвертируем в число
        if (digitValue > 0 & digitValue <= 10 ) {
            this.arabicDigitOne = digitValue; // Записываем параметр
        }  else {
            throw new IllegalArgumentException("Illegal Argument");
        }
    }

    public void setArabicDigitTwo (String digitString) {
        int digitValue = Integer.parseInt(digitString);
        if (digitValue > 0 & digitValue <= 10 ) {
            this.arabicDigitTwo = digitValue;
        }  else {
            throw new IllegalArgumentException("Illegal Argument");
        }
    }

    public void setRomanDigitOne (String digitString) {
        romanDigitOne = digitString;
        int value;
        char[] digitsArray = digitString.toCharArray(); // Разбиваем по символам
        int[] sumArray;
        sumArray = new int[digitString.length()]; // Готовим массив для работы с цифрами
        int pos = 0;
        for (char digit : digitsArray) { // Записываем в подготовленный массив арабские цифры
            switch (digit) {
                case 'I':
                    sumArray[pos] = 1;
                    break;
                case 'V':
                    sumArray[pos] = 5;
                    break;
                case 'X':
                    sumArray[pos] = 10;
                    break;

            }
            pos++;
        }

        if (sumArray.length > 1) { // Если римский знак один, то нет смысла ничего с ним делать
            for (int i = 0; i < sumArray.length - 1; i++) { // Сначала ищем пару где i < i-1
                if (sumArray[i] < sumArray[i + 1]) {
                    int j = 0;
                    while (j < i) { // А потом ищем предыдущие "уменьшители"
                        if (sumArray[j] == sumArray[i]) {
                            sumArray[j] *= -1;
                        }
                        j++;
                    }
                    sumArray[i] *= -1;
                }
            }
        }

        int sum = 0;
        for (int digit : sumArray) {
            sum += digit;
        }

        digitString = String.valueOf(sum);

        this.setArabicDigitOne(digitString);
    }

    public void setRomanDigitTwo (String digitString) {
        romanDigitTwo = digitString;
        int value;
        char[] digitsArray = digitString.toCharArray();
        int[] sumArray;
        sumArray = new int[digitString.length()];
        int pos = 0;
        for (char digit : digitsArray) {
            switch (digit) {
                case 'I':
                    sumArray[pos] = 1;
                    break;
                case 'V':
                    sumArray[pos] = 5;
                    break;
                case 'X':
                    sumArray[pos] = 10;
                    break;

            }
            pos++;
        }

        if (sumArray.length > 1) {
            for (int i = 0; i < sumArray.length - 1; i++) {
                if (sumArray[i] < sumArray[i + 1]) {
                    int j = 0;
                    while (j < i) {
                        if (sumArray[j] == sumArray[i]) {
                            sumArray[j] *= -1;
                        }
                        j++;
                    }
                    sumArray[i] *= -1;
                }
            }
        }

        int sum = 0;
        for (int digit : sumArray) {
            sum += digit;
        }

        digitString = String.valueOf(sum);

        this.setArabicDigitTwo(digitString);
    }

    public static String convertToRoman (int value) {
        int romanX = 0;
        int romanI = 0;

        String resultString = "";

        if (value < 0) {
            resultString += "-";
            value *= -1;
        }


        romanX = value / 10;
        if (romanX > 0) {
            switch (romanX) {
                case 1:	resultString += "X"; break;
                case 2:	resultString += "XX"; break;
                case 3:	resultString += "XXX"; break;
                case 4:	resultString += "XL"; break;
                case 5:	resultString += "L"; break;
                case 6:	resultString += "LX"; break;
                case 7:	resultString += "LXX"; break;
                case 8:	resultString += "LXXX"; break;
                case 9:	resultString += "XC"; break;
            }
        }

        romanI = value - romanX * 10;
        if (romanI == 0 && romanX == 0) {
            resultString += "0";
        } else if (romanI < 4) {
            for (int i = 0; i < romanI; i++) {
                resultString += "I";
            }
        } else if (romanI == 4) {
            resultString += "IV";
        } else if (romanI == 5) {
            resultString += "V";
        } else if (romanI < 9) {
            resultString += "V";
            for (int i = 0; i < romanI - 5; i++) {
                resultString += "I";
            }
        } else if (romanI == 9) {
            resultString += "IX";
        }


        return resultString;
    }

}
