package Helpers;

import java.util.Random;


public class DataHelper {
    //Data Users Register
    private static String generateRandomString(int targetStringLength){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);

        }
        String generatedString = buffer.toString();

        return generatedString;
    }
    public static String generateRandomEmail(){
        return String.format("%s@testemail.com" , generateRandomString(7));
    }
    public static String generateRandomName(){
        return String.format("%s" , generateRandomString(10));
    }
    //Articles create
    public static String generateRandomTitle(){
        return String.format("%s" , generateRandomString(10));
    }
    public static String generateRandomContent(){
        return String.format("%s" , generateRandomString(50));
    }
    //Post Create
    public static String generateTitlePost(){
        return String.format("%s" , generateRandomString(10));
    }
    public static String generateContentPost(){
        return String.format("%s" , generateRandomString(30));
    }
    // comments create
    public static String generateNameComment(){
        return String.format("%s" , generateRandomString(10));
    }
    public static String generateComment(){
        return String.format("%s" , generateRandomString(30));
    }

}
