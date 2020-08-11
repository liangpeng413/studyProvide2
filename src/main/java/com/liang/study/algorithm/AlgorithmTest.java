package com.liang.study.algorithm;

import java.util.HashSet;

/**
 * @author: liang
 * @Date: 2020/8/4 18:28
 */
public class AlgorithmTest {

    public static String returnLongString(){
        String initString = "sadfjyaoahfslalajlkdf";
        String returnData = "";
        for (int i = 0; i < initString.length() ; i++) {
            char c = initString.charAt(i);
            for (int j = i+1; j < initString.length(); j++) {
                if(c == initString.charAt(j)){
                    if(returnData.length()<initString.substring(i+1,j).length()){
                        returnData = initString.substring(i,j);
                    }
                }
            }
        }
        return returnData;
    }



    public static void main(String[] args) {
        System.out.println(returnLongString());
    }
}
