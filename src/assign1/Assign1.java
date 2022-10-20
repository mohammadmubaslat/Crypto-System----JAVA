/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assign1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 *
 * @author PC
 */
public class Assign1 {

    static String key;
    static String globalKey = "";
    static String spacedText;

    static String removeDigits(String text) {
        text = text.replaceAll("[-+.^:*/;&%|$#@!~)({},’'?“”]", "");
        text = text.replaceAll("\\d", "");
        return text;
    }

    static String removeSpaces(String text) {
        text = text.replaceAll("\\s+", "");
        return text;
    }

    static void randomKey() {
        
        String ltrs = "abcdefghijklmnopqrstuvwxyz";
        ArrayList<Character> ltrs_arr = new ArrayList<>();
        for(int i=0; i< ltrs.length(); i++){
            ltrs_arr.add( ltrs.charAt(i));
        }
        Collections.shuffle(ltrs_arr);   
        for (int i = 0; i < 26; i++) {
            globalKey += ltrs_arr.get(i);
        }
        key = globalKey.toUpperCase();
        globalKey = "";
        ltrs_arr.clear();
    }

    static void Encrypt(String text, int key) {
        Scanner sc = new Scanner(System.in);
        char[][] cipherMat = new char[key][text.length()];
        int down = 1, up = 0, row = 0;
        String cipherText = "";
        int index = 0;

        for (int i = 0; i < key; i++) {
            for (int j = 0; j < text.length(); j++) {
                cipherMat[i][j] = '*';
            }
        }
        for (int i = 0; i < text.length(); i++) {

            if (row == 0) {
                down = 1;
                up = 0;
            }
            if (row == key - 1) {
                up = 1;
                down = 0;
            }

            cipherMat[row][i] = text.charAt(i);
            if (down == 1) {
                row++;
            }
            if (up == 1) {
                row--;
            }
        }

        System.out.println("The Rail Fence Cipher Matrix is:\n\n");
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < text.length(); j++) 
                System.out.print(cipherMat[i][j] + "  ");
            System.out.println();
        }

        System.out.println('\n');
        for (int i = 0; i < key; i++) 
            for (int j = 0; j < text.length(); j++) 
                if (cipherMat[i][j] != '*') 
                    cipherText += cipherMat[i][j];
           
        System.out.println("\nThe Ciphered Text is: " + cipherText + "\n\n");
    }

    static void Decrypt(String text, int key) {
        char[][] cipheredMat = new char[key][text.length()];
        int down = 0, up = 0, row = 0, col = 0;
        int var = 0;
        String result = "";

        for (int i = 0; i < text.length(); i++) {
            if (row == 0) {
                down = 1;
                up = 0;
            }
            if (row == key - 1) {
                up = 1;
                down = 0;
            }

            cipheredMat[row][i] = '*';
            if (down == 1) {
                row++;
            }
            if (up == 1) {
                row--;
            }
        }

        for (int i = 0; i < key; i++) {
            for (int j = 0; j < text.length(); j++) {
                if (cipheredMat[i][j] == '*' && var < text.length()) {
                    cipheredMat[i][j] = text.charAt(var);
                    var++;
                }
            }
        }

        down = 0;
        up = 0;
        row = 0;
        col = 0;
        for (int i = 0; i < text.length(); i++) {
            if (row == 0) {
                down = 1;
                up = 0;
            }
            if (row == key - 1) {
                up = 1;
                down = 0;
            }
            if (cipheredMat[row][col] != '*') {
                result += cipheredMat[row][col++];
            }
            if (down == 1) {
                row++;
            }
            if (up == 1) {
                row--;
            }
        }
        System.out.println("\nThe original message is: " + result + "\n");
    }

    static void Crack(String text) {
        for (int i = 2; i < 11; i++) {
            Decrypt(text, i);
        }
    }

    static void Question1() {
        Scanner sc = new Scanner(System.in);
        int choice = 1;
        int key;
        String text;

        System.out.println("  -------------------------------------------- YOU ARE IN THE FIRST SYSTEM ------------------------------------------" + "\n\n");
        while (choice != 0) {
            System.out.println("        ----------------------");
            System.out.println("\tpress [ 1 ] to Encrypt.\n\tpress [ 2 ] to Decrypt.\n\tpress [ 3 ] to Crack.\n\tpress [ 0 ] to exit.");
            System.out.println("        ----------------------");

            choice = sc.nextInt();
            Scanner scan = new Scanner(System.in);
            switch (choice) {
                case 1:
                    System.out.println("Please enter the text you want to encrypt!");
                    text = text = scan.nextLine();
                    text = removeDigits(text);
                    spacedText = text;
                    System.out.println("\nPlease enter the key");
                    key = sc.nextInt();
                    Encrypt(spacedText, key);
                    break;
                case 2:
                    System.out.println("Please enter the text you want to Decrypt!");
                    text = text = scan.nextLine();
                    text = removeDigits(text);
                    spacedText = text;
                    System.out.println("\nPlease enter the key");
                    key = sc.nextInt();
                    Decrypt(spacedText, key);
                    break;
                case 3:
                    System.out.println("Please enter the text you want to Crack!");
                    text = text = scan.nextLine();
                    text = removeDigits(text);
                    spacedText = text;
                    Crack(spacedText);
                    break;
                case 0:
                    System.out.println("-------------------------- YOU ARE OUT THE FIRST SYSTEM ---------------------------");
                    break;
            }

        }
    }

    static void Q2Encrypt(String text) {
        String ltrs = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String res = "";
        text = text.toUpperCase();
        Map<Character, Integer> myMap = new HashMap<>();

        for (int i = 0; i < ltrs.length(); i++) 
            myMap.put(ltrs.charAt(i), i);
        
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                res += " ";
            } else if(text.charAt(i) >='A' && text.charAt(i) <='Z')  {
                res += key.charAt(myMap.get(text.charAt(i)));
            }
        }

        System.out.println("\nThe Ciphertext is:\n" + res.toLowerCase());
    }

    static void Q2Decrypt(String text) {
        text = text.toUpperCase();
        Map<Character, Integer> myMap = new HashMap<>();
        String ltrs = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String res = "";

        for (int i = 0; i < ltrs.length(); i++) {
            myMap.put(key.charAt(i), i);
        }
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                res += " ";
            } else  {
                res += ltrs.charAt(myMap.get(text.charAt(i)));
            }
        }
        System.out.println("\nThe Plaintext is:\n" + res.toLowerCase());
    }

    static void Q2Analysis(String text) {
        Scanner sc = new Scanner(System.in);
        Map<Character, Integer> myMap = new HashMap<>();
        char ch = 'a';
        String result = "";
        char[] lettersArr = {'E', 'T', 'A', 'O', 'I', 'N', 'S', 'R', 'H', 'D', 'L', 'U', 'C', 'M', 'F', 'Y', 'W', 'G', 'P', 'B', 'V', 'K', 'X', 'Q', 'J', 'Z'};
        ArrayList<Integer> hashArrList = new ArrayList<>();

        for (int i = 0; i < text.length(); i++) {
            if (myMap.containsKey(text.charAt(i))) {
                myMap.put(text.charAt(i), myMap.get(text.charAt(i)) + 1);
            } else {
                if(text.charAt(i)!=' ')
                myMap.put(text.charAt(i), 1);
            }
        }
        System.out.println();
        System.out.println("The count of repeation is: ");
        System.out.println(myMap);
        for (Entry<Character, Integer> entry : myMap.entrySet()) {
            hashArrList.add(entry.getValue());
        }
        Collections.sort(hashArrList);

        System.out.println();
        System.out.println("\nThe ascending sorted array is: (The values will reverse)");
        for (int i = 0; i < hashArrList.size(); i++) {
            System.out.print(hashArrList.get(i) + " ");
        }

        for (int i = 0; i < text.length(); i++) {
            if (myMap.containsKey(text.charAt(i))) {
                for (int j = 0; j < hashArrList.size(); j++) {
                    if (hashArrList.get(hashArrList.size() - j - 1) == myMap.get(text.charAt(i))) {
                        result += lettersArr[j];
                        break;
                    }
                }
            }
            else if(text.charAt(i) == ' ')
                result += " ";
        }
  
        System.out.println("\n\n The Plaintext is: \n" + result.toLowerCase());
        System.out.println();
        System.out.println("The analysis: \n");
        int length = text.length();
        for (Entry<Character, Integer> entry : myMap.entrySet()) {
            System.out.print("  " + entry.getKey() + "\t" );
            for (int i = 0; i < entry.getValue(); i++) {
                System.out.print("* ");
            }
            System.out.println("  " + entry.getValue()+ "  ");
        }
    }

    static void Question2() {

        System.out.println("-------------------------- YOU ARE IN THE SECOND SYSTEM ---------------------------");
        Scanner sc = new Scanner(System.in);
        int choice = 1;
        int subInput;
        String subKey = key;

        String text;
        while (choice != 0) {
            System.out.println("\n        ----------------------");
            System.out.println("\tpress [ 1 ] to Encrypt.\n\tpress [ 2 ] to Decrypt.\n\tpress [ 3 ] to Analysis.\n\tpress [ 0 ] to exit.");
            System.out.println("        ----------------------");
            choice = sc.nextInt();
            Scanner scan = new Scanner(System.in);
            switch (choice) {
                case 1:
                    System.out.println("Please enter the text you want to encrypt!");
                    text = scan.nextLine();
                    text = removeDigits(text);
                    spacedText = text.toUpperCase();
                    //text = removeSpaces(text);
                    System.out.println("\n\t\tPress [ 1 ] to generate random key.\n\t\tPress [ 2 ] to enter a 26 character's key.");
                    subInput = sc.nextInt();

                    if (subInput == 2) {
                        System.out.println("\t\tEnter a 26 character's key.");
                        subKey = sc.next();
                        subKey = removeDigits(subKey);
                        subKey = removeSpaces(subKey);

                        if (subKey.length() == 26) {
                            key = subKey.toUpperCase();
                            Q2Encrypt(spacedText);
                        } else {
                            System.out.println("\t\tFailed!!! The key must be exactly 26 characters (No digits accepted!)");
                        }
                    } else if (subInput == 1) {
                        randomKey();
                        Q2Encrypt(spacedText);
                        System.out.println("The used key is:\n" + key.toLowerCase());
                    } else {
                        System.out.println("False input, try again");
                    }
                    break;
                case 2:
                    System.out.println("Please enter the text you want to Decrypt!");
                    text = scan.nextLine();
                    text = removeDigits(text);
                    spacedText = text.toUpperCase();
                    //text = removeSpaces(text);
                    System.out.println("\nEnter a 26 character's key.");
                    subKey = sc.next();
                    subKey = removeDigits(subKey);
                    subKey = removeSpaces(subKey);

                    if (subKey.length() == 26) {
                        key = subKey.toUpperCase();
                        Q2Decrypt(spacedText);
                    } else {
                        System.out.println("\nFailed!!! The key must be exactly 26 characters (No digits accepted!)");
                    }

                    break;
                case 3:
                    System.out.println("Please enter the Ciphertext you want to Analysis!");
                    text = scan.nextLine();
                    text = removeDigits(text);
                    Q2Analysis(text);
                    break;
                case 0:
                    System.out.println("-------------------------- YOU ARE OUT THE FIRST SYSTEM ---------------------------");
                    break;
            }

        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = 1;
        while (choice != 0) {
            System.out.println("\n\n  Hello Sir,\n  Welcome in our simple double's cipher system!\n  press [ 1 ] to first system.\n  press [ 2 ] to second system.\n  press [ 0 ] to exit.");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    Question1();
                    break;
                case 2:
                    Question2();
                    break;
                case 0:
                    System.out.println("Thank you!");
                    break;
            }
        }

    }

}
