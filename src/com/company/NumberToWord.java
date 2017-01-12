package com.company;

import java.util.HashMap;

/**
 * Created by kayode.oguntimehin on 10/01/2017.
 */
public class NumberToWord {

    //constructor for class NumberToWord
    public NumberToWord(){

    }

    public String ConvertToWord(long inputValue) {

        String response = "";

        try {
            long inputFormatted = Long.parseLong(String.valueOf(inputValue));
            int inputLength = String.valueOf(inputFormatted).length();
            //get the length and check if it is greater than hundreds
            if(inputLength > 3){

                return ThousandandAboveValue(inputFormatted);
            }
            else if(inputLength > 0  && inputLength <= 3){
                switch (inputLength) {
                    case 1:
                        response = UnitValue(inputFormatted);
                        break;

                    case 2:
                        response = TensValue(inputFormatted);
                        break;
                    case 3:
                        response = HundredsValue(inputFormatted);
                        break;
                    default:
                        //throw new Exception();
                        response =  "Invalid Number";
                        break;

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            response =  "Invalid Number";
        }
        return response;
    }

    private String UnitValue(long unitVal) {
        HashMap<Integer, String> unitWordMap = new HashMap<>();
        String[] unitwords = new String[] {
                "zero", "one", "two", "three",
                "four", "five", "six",
                "seven", "eight", "nine"
        };
        for(int i = 0; i < unitwords.length; i++) {
            unitWordMap.put(i, unitwords[i]);
        }
        if(String.valueOf(unitVal).length() > 1) {
            return "Invalid number";
        }
            return unitWordMap.get(Integer.parseInt(String.valueOf(unitVal)));
    }

    private String TensValue(long tensVal) {
        //for 10 - 19
        int[] tens10_19 = new int[] {
                10,11,12,13,
                14,15,16,17,
                18,19
        };
        String[] tenswords10_19 = new String[] {
                "ten","eleven","twelve",
                "thirteen","fourteen","fifteen",
                "sixteen","seventeen","eighteen",
                "nineteen"
        };
        HashMap<Integer, String> tensWordMap10_19 = new HashMap<>();
        for(int i = 0; i < tens10_19.length; i++) {
            tensWordMap10_19.put(tens10_19[i], tenswords10_19[i]);
        }

        int[] tens = new int[] {
                20,30,
                40,50,60,70,
                80,90
        };
        String[] tenswords = new String[] {
                "twenty","thirty",
                "forty","fifty","sixty",
                "seventy","eighty","ninety"

        };
        HashMap<Integer, String> tensWordMap = new HashMap<>();
        for(int i = 0; i < tens.length; i++) {
            tensWordMap.put(tens[i], tenswords[i]);
        }
        if(inArray(tensVal, tens10_19)){
            return tensWordMap10_19.get(Integer.parseInt(String.valueOf(tensVal)));
        } else {
            if (String.valueOf(tensVal).length() > 2) {
                return "Invalid tens value";
            } else if (String.valueOf(tensVal).length() == 2 && !inArray(tensVal, tens)) {
                //break it down and add the unit value to it
                String tensWord = "";
                int[] arrVal = new int[]{2, 3, 4, 5, 6, 7, 8, 9};
                for (int a : arrVal) {
                    if (a == Integer.parseInt(String.valueOf(String.valueOf(tensVal).charAt(0)))) {
                        int tensFirstElement = Integer.parseInt(String.valueOf(String.valueOf(tensVal).charAt(0)));
                        int tensInZeros = tensFirstElement * 10;
                        tensWord += tensWordMap.get(tensInZeros);
                        int tensSecondElement = Integer.parseInt(String.valueOf(String.valueOf(tensVal).charAt(1)));
                        String unitVal = UnitValue(tensSecondElement);
                        tensWord += " " + unitVal;
                        return tensWord;
                    }
                }

            }
        }
        return tensWordMap.get(Integer.parseInt(String.valueOf(tensVal)));

    }

    private String HundredsValue(long hundredsValue) {
        String hundred = "hundred";
        int[] hundreds = new int[]{100,200,300,400,500,600,700,800,900};
        if(inArray(hundredsValue, hundreds)){
            return UnitValue(Integer.parseInt(String.valueOf
                                                (String.valueOf(hundredsValue).charAt(0))
                                              )
            ) + "hundred";
        }
        else if(String.valueOf(hundredsValue).length() == 3 && !inArray(hundredsValue, hundreds)) {
            int firstVal = Integer.parseInt(String.valueOf(String.valueOf(hundredsValue).charAt(0)));
            int lastTwoVals = Integer.parseInt(String.valueOf(hundredsValue).substring(1,3));


            String firstValInWord = UnitValue(firstVal);

            StringBuilder lastTwoValsInWord = new StringBuilder();

            //check if the last two numbers has been reduced to one if so call unitValue() to get the equivalent word
            if(String.valueOf(lastTwoVals).length() > 1){
                lastTwoValsInWord.append(TensValue(lastTwoVals));
            }
            else{
                lastTwoValsInWord.append(UnitValue(lastTwoVals));
            }

            return firstValInWord + " "+ hundred + " and " + lastTwoValsInWord.toString();

        }
        return "Invalid number";
    }

    private String ThousandandAboveValue(long thousandValue){
        int inputLength = String.valueOf(thousandValue).length();
        String thousand = "thousand";
        String million =  "million";
        String billion = "billion";
        String trillion = "trillion";
        String zillion = "zillion";

        switch (inputLength){
            case 4: case 5: case 6:
                return manipulateGreaterThanThousand(thousandValue,inputLength - 3,inputLength - 3,inputLength,thousand);

            case 7: case 8: case 9:
                return manipulateGreaterThanThousand(thousandValue,inputLength - 6,inputLength - 6,inputLength,million);

            case 10: case 11: case 12:
                return manipulateGreaterThanThousand(thousandValue,inputLength - 9,inputLength - 9,inputLength,billion);

            case 13: case 14: case 15:
                return manipulateGreaterThanThousand(thousandValue,inputLength - 12,inputLength - 12,inputLength,trillion);

            case 16: case 17: case 18:
                return manipulateGreaterThanThousand(thousandValue,inputLength - 15,inputLength - 15,inputLength,zillion);

            default:
                return "Invalid number";

        }

    }

    private String manipulateGreaterThanThousand(long inputValue, int secondIndex, int indexFrom, int indexTo, String unitType){
        StringBuilder numberToWord = new StringBuilder();
        int firstValue = Integer.parseInt(String.valueOf(inputValue).substring(0,secondIndex));
        String firstValInWord;
        String othernumbersVal;


                switch (String.valueOf(firstValue).length()) {
                    case 1:
                        firstValInWord = UnitValue(firstValue);
                        break;
                    case 2:
                        firstValInWord = TensValue(firstValue);
                        break;
                    case 3:
                        firstValInWord = HundredsValue(firstValue);
                        break;
                    default:
                        return "Invalid Number";
                }
                String firstNumber = firstValInWord + " "+unitType+", ";
                numberToWord.append(firstNumber);
                switch (unitType) {

                    case "thousand":
                        othernumbersVal = String.valueOf(inputValue).substring(indexFrom,indexTo);
                        if(Integer.parseInt(othernumbersVal.substring(0, 1)) > 0){
                            numberToWord.append(HundredsValue(Integer.parseInt(othernumbersVal)));
                        }
                        else if(Integer.parseInt(othernumbersVal.substring(0, 1)) == 0
                                && Integer.parseInt(othernumbersVal.substring(1, 2)) > 0) {

                            String appendVal = "and"+ TensValue(Integer.parseInt(othernumbersVal));
                            numberToWord.append(appendVal);
                        }
                        else if(Integer.parseInt(othernumbersVal.substring(0, 1)) == 0
                                && Integer.parseInt(othernumbersVal.substring(1, 2)) == 0
                                && Integer.parseInt(othernumbersVal.substring(2, 3)) > 0) {
                            String appendVal = "and"+ UnitValue(Integer.parseInt(othernumbersVal));
                            numberToWord.append(appendVal);
                        }
                        else{
                            String str = numberToWord.substring(0, numberToWord.length()-2);
                            numberToWord.setLength(0);
                            numberToWord.append(str);
                        }

                        return numberToWord.toString().replace(", and", " and ");
                    case "million":
                        othernumbersVal = String.valueOf(inputValue).substring(indexFrom,indexTo);
                        if(Integer.parseInt(othernumbersVal.substring(0, 1)) > 0){
                            numberToWord.append(ThousandandAboveValue(Integer.parseInt(othernumbersVal)));
                        }
                        else if(Integer.parseInt(othernumbersVal.substring(0, 1)) == 0 && Integer.parseInt(othernumbersVal) != 0) {
                            int newOtherNumbersVal = Integer.parseInt(othernumbersVal);
                            int newOtherNumbersLenght = String.valueOf(newOtherNumbersVal).length();
                            String appendVal;
                            switch (newOtherNumbersLenght) {
                                case 5: case 4:
                                    numberToWord.append(ThousandandAboveValue(newOtherNumbersVal));
                                    break;
                                case 3:
                                    numberToWord.append(HundredsValue(newOtherNumbersVal));
                                    break;
                                case 2:
                                    appendVal = "and"+ TensValue(newOtherNumbersVal);
                                    numberToWord.append(appendVal);
                                    break;
                                case 1:
                                    appendVal = "and"+ UnitValue(newOtherNumbersVal);
                                    numberToWord.append(appendVal);
                            }

                        }
                        else {
                            String str = numberToWord.substring(0, numberToWord.length()-2);
                            numberToWord.setLength(0);
                            numberToWord.append(str);
                        }
                        return numberToWord.toString().replace(", and", " and ");


                    case "billion":
                        othernumbersVal = String.valueOf(inputValue).substring(indexFrom,indexTo);
                        if(Integer.parseInt(othernumbersVal.substring(0,1)) > 0){
                            String result = ThousandandAboveValue(Long.parseLong(String.valueOf(othernumbersVal)));
                            numberToWord.append(result);
                        }
                        else if(Integer.parseInt(othernumbersVal.substring(0, 1)) == 0
                                && Integer.parseInt(othernumbersVal) != 0) {
                            int newOtherNumbersVal = Integer.parseInt(othernumbersVal);
                            int newOtherNumbersLenght = String.valueOf(newOtherNumbersVal).length();
                            String appendVal;
                            switch (newOtherNumbersLenght) {
                                case 8: case 7: case 6: case 5: case 4:
                                    numberToWord.append(ThousandandAboveValue(Long.parseLong(String.valueOf(newOtherNumbersVal))));
                                    break;
                                case 3:
                                    numberToWord.append(HundredsValue(newOtherNumbersVal));
                                    break;
                                case 2:
                                    appendVal = "and"+ TensValue(newOtherNumbersVal);
                                    numberToWord.append(appendVal);
                                    break;
                                case 1:
                                    appendVal = "and"+ UnitValue(newOtherNumbersVal);
                                    numberToWord.append(appendVal);
                            }


                        }
                        else {
                            String str = numberToWord.substring(0, numberToWord.length()-2);
                            numberToWord.setLength(0);
                            numberToWord.append(str);
                        }
                        return numberToWord.toString().replace(", and", " and ");



                    case "trillion":
                        othernumbersVal = String.valueOf(inputValue).substring(indexFrom,indexTo);
                        if(Integer.parseInt(othernumbersVal.substring(0,1)) > 0){
                            String result = ThousandandAboveValue(Long.parseLong(String.valueOf(othernumbersVal)));
                            numberToWord.append(result);
                        }
                        else if(Integer.parseInt(othernumbersVal.substring(0, 1)) == 0
                                && Integer.parseInt(othernumbersVal) != 0) {
                            int newOtherNumbersVal = Integer.parseInt(othernumbersVal);
                            int newOtherNumbersLenght = String.valueOf(newOtherNumbersVal).length();
                            String appendVal;
                            switch (newOtherNumbersLenght) {
                                case 11: case 10: case 9: case 8:
                                case 7: case 6: case 5: case 4:
                                    numberToWord.append(ThousandandAboveValue(Long.parseLong(String.valueOf(newOtherNumbersVal))));
                                    break;
                                case 3:
                                    numberToWord.append(HundredsValue(newOtherNumbersVal));
                                    break;
                                case 2:
                                    appendVal = "and" + TensValue(newOtherNumbersVal);
                                    numberToWord.append(appendVal);
                                    break;
                                case 1:
                                    appendVal = "and" + UnitValue(newOtherNumbersVal);
                                    numberToWord.append(appendVal);
                            }
                        } else {
                            String str = numberToWord.substring(0, numberToWord.length()-2);
                            numberToWord.setLength(0);
                            numberToWord.append(str);
                        }
                        return numberToWord.toString().replace(", and", " and ");


                    case "zillion":
                        othernumbersVal = String.valueOf(inputValue).substring(indexFrom,indexTo);
                        if(Integer.parseInt(othernumbersVal.substring(0,1)) > 0){
                            String result = ThousandandAboveValue(Long.parseLong(String.valueOf(othernumbersVal)));
                            numberToWord.append(result);
                        }
                        else if(Integer.parseInt(othernumbersVal.substring(0, 1)) == 0
                                && Integer.parseInt(othernumbersVal) != 0) {
                            int newOtherNumbersVal = Integer.parseInt(othernumbersVal);
                            int newOtherNumbersLenght = String.valueOf(newOtherNumbersVal).length();
                            String appendVal;
                            switch (newOtherNumbersLenght) {
                                case 14: case 13: case 12: case 11: case 10:
                                case 9: case 8: case 7: case 6: case 5: case 4:
                                    numberToWord.append(ThousandandAboveValue(Long.parseLong(String.valueOf(newOtherNumbersVal))));
                                    break;
                                case 3:
                                    numberToWord.append(HundredsValue(newOtherNumbersVal));
                                    break;
                                case 2:
                                    appendVal = "and" + TensValue(newOtherNumbersVal);
                                    numberToWord.append(appendVal);
                                    break;
                                case 1:
                                    appendVal = "and" + UnitValue(newOtherNumbersVal);
                                    numberToWord.append(appendVal);
                            }
                        } else {
                            String str = numberToWord.substring(0, numberToWord.length()-2);
                            numberToWord.setLength(0);
                            numberToWord.append(str);
                        }
                        return numberToWord.toString().replace(", and", " and ");


                    default:
                        return "invalid number";

                }




    }

    private static boolean inArray(long a, int arrayList[]){
        for (int ch: arrayList) {
            if(a == ch){
                return true;
            }
            else{
                continue;
            }
        }

        return false;
    }

}
