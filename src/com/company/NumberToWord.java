package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringJoiner;

/**
 * Created by kayode.oguntimehin on 10/01/2017.
 */
public class NumberToWord {

    //constructor for class NumberToWord
    public NumberToWord(){

    }

    public String UnitValue(int unitVal) {
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
            return "Invalid unit value";
        }
            return unitWordMap.get(unitVal);
    }
//
//    public String TensValue(int tensVal) {
//        int[] tens = new int[] {
//                10,11,12,13,
//                14,15,16,17,
//                18,19
//        };
//        String[] tenswords = new String[] {
//                "ten","eleven","twelve",
//                "thirteen","fourteen","fifteen",
//                "sixteen","seventeen","eighteen",
//                "nineteen"
//        };
//        HashMap<Integer, String> tensWordMap = new HashMap<>();
//        for(int i = 0; i < tens.length; i++) {
//            tensWordMap.put(tens[i], tenswords[i]);
//        }
//        if(String.valueOf(tensVal).length() > 2) {
//            return "Invalid tens value";
//        }
//
//        return tensWordMap.get(tensVal);
//
//    }

    public String TensValue(int tensVal) {
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
            return tensWordMap10_19.get(tensVal);
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
        return tensWordMap.get(tensVal);

    }

    public String HundredsValue(int hundredsValue) {
        String hundred = "hundred";
        int[] tens = new int[] {10,11,12,13,14,15,16,17,18,19};
        int[] hundreds = new int[]{100,200,300,400,500,600,700,800,900};
        if(inArray(hundredsValue, hundreds)){
            return UnitValue(Integer.parseInt(String.valueOf
                                                (String.valueOf(hundredsValue).charAt(0))
                                              )
            ) + Constant.HUNDRED;
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
        return "Invalid hundred value";
    }

    public String ThousandValue(int thousandValue){
        int inputLength = String.valueOf(thousandValue).length();
        String thousand = "thousand";
        String million =  "million";

        switch (inputLength){
            case 4:
                return manipulateGreaterThanThousand(thousandValue,1,1,4,thousand);

            case 5:
                return manipulateGreaterThanThousand(thousandValue,2,2,5,thousand);

            case 6:
                return manipulateGreaterThanThousand(thousandValue,3,3,6,thousand);

            case 7:
                return manipulateGreaterThanThousand(thousandValue,1,3,6,million);

            case 8:
                return manipulateGreaterThanThousand(thousandValue,2,3,6,million);

            case 9:
                return manipulateGreaterThanThousand(thousandValue,3,3,6,million);
            default:
                return "Invalid thousand value";

        }

    }


    public String MillionValue(int millionValue){
        int inputMillionLength = String.valueOf(millionValue).length();
        String million = "million";
        switch (inputMillionLength){
            case 7:
                return manipulateGreaterThanThousand(millionValue,1,1,4,million);

            case 8:
                return manipulateGreaterThanThousand(millionValue,2,2,5,million);

            case 9:
                return manipulateGreaterThanThousand(millionValue,3,3,6,million);

            default:
                return "Invalid million value";

        }

    }
//    public String MillionValue(int millionValue){
//        int inputLength = String.valueOf(millionValue).length();
//        StringBuilder thousandToWord = new StringBuilder();
//        int firstValue;
//        String firstValInWord;
//        String firstThousand;
//        String otherThousandVal;
//        switch (inputLength){
//            case 7:
//                firstValue = Integer.parseInt(String.valueOf(String.valueOf(millionValue).charAt(0)));
//                firstValInWord = UnitValue(firstValue);
//                firstThousand = firstValInWord + " million, ";
//                thousandToWord.append(firstThousand);
//                otherThousandVal = String.valueOf(inputValue).substring(3,6);
//                thousandToWord.append(HundredsValue(Integer.parseInt(otherThousandVal)));
//                return thousandToWord.toString();
//                return "";
//            case 8:
//                return "";
//            case 9:
//                return "";
//            default:
//                return "";
//        }

//    }

    private String manipulateGreaterThanThousand(int inputValue, int secondIndex, int indexFrom, int indexTo, String unitType){
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
                        else if(Integer.parseInt(othernumbersVal.substring(0, 1)) == 0 && Integer.parseInt(othernumbersVal.substring(1, 2)) > 0) {
                            numberToWord.append(TensValue(Integer.parseInt(othernumbersVal)));
                        }
                        else if(Integer.parseInt(othernumbersVal.substring(0, 1)) == 0 && Integer.parseInt(othernumbersVal.substring(1, 2)) == 0
                                && Integer.parseInt(othernumbersVal.substring(2, 3)) > 0) {
                            numberToWord.append(UnitValue(Integer.parseInt(othernumbersVal)));
                        }
                        else{
                            //  str = str.substring(0, str.length()-1);
                            String str = numberToWord.substring(0, numberToWord.length()-2);
                            numberToWord.setLength(0);
                            numberToWord.append(str);
                        }

                        return numberToWord.toString();
                    case "million":
                        othernumbersVal = String.valueOf(inputValue).substring(indexFrom,indexTo);
                        if(Integer.parseInt(othernumbersVal.substring(0, 1)) > 0){
                            numberToWord.append(ThousandValue(Integer.parseInt(othernumbersVal)));
                        }
                        else{
                            //  str = str.substring(0, str.length()-1);
                            String str = numberToWord.substring(0, numberToWord.length()-2);
                            numberToWord.setLength(0);
                            numberToWord.append(str);
                        }
                        return numberToWord.toString();
                    default:
                        return "invalid";

                }




    }

    private static boolean inArray(int a, int arrayList[]){
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
