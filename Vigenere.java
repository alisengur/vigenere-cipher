package vigenere;

import java.util.Scanner;

public class Vigenere {

    private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String message;
    private String key;




    public String encrypt(String originalText, String key) {

        StringBuilder genKey = new StringBuilder();                     //  girilen key'in, originalText'in uzunluğuna tamamlanması

        int j = 0;
        for (int i = 0; i < originalText.length(); i++) {
            j = j % key.length();                                   // j değişkeni key'in uzunluğuna her ulaştığında, tekrar 0'dan başlıyor.
            genKey.append(key.charAt(j));
            j++;

        }

        System.out.println("Integrated key = "+genKey);

        StringBuilder newOriginalText = new StringBuilder();      // şifrelenmiş text'i tutmak için değişken oluşturuldu.

        int sumIndex = 0;
        for (int i = 0; i < originalText.length(); i++) {

            int index1 = alphabet.indexOf(originalText.charAt(i)) + 1;    // girilen text'in i. harfinin alfabedeki index numarası tutuluyor, alfabe stringinin
            // indexi 0 dan başladığı için +1 eklendi
            int index2 = alphabet.indexOf(genKey.charAt(i)) + 1;         // aynı işlem key için de uygulandı.
            sumIndex = (sumIndex + ((index1 + index2) - 1)) % 26;
            newOriginalText.append(alphabet.charAt(sumIndex));      // text'in i. harfi ile key'in i. harfinin index numarası toplanıp, alfabedeki o index
            // numarasında bulunan harf, newOriginalText'e eklendi.
            sumIndex = 0;                // her defasında sumIndex tekrar 0 olmalı

        }
        System.out.println("Encrypted text = "+newOriginalText.toString());
        System.out.println("Original text  = "+decrypt(newOriginalText.toString(), genKey.toString()));    // şifrelenmiş text ve key ile birlikte decrypt metodu çağırıldı.


        return newOriginalText.toString();


    }









    public String toUpperString(String text)
    {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            sb.append(Character.toUpperCase(text.charAt(i)));       // text'in her harfi büyük harfe çevrilip sb değişkenine eklendi.
        }
        return sb.toString();
    }





    public String decrypt(String encryptedText, String key)
    {

        StringBuilder originalText = new StringBuilder();

        int subsIndex = 0;
        for (int i = 0; i < encryptedText.length(); i++) {

            int index1 = alphabet.indexOf(encryptedText.charAt(i)) + 1;
            int index2 = alphabet.indexOf(key.charAt(i)) + 1;
            int substract = index1-index2;
            subsIndex = (subsIndex + (substract + 26)) % 26;

            originalText.append(alphabet.charAt(subsIndex - 1));

            subsIndex = 0;

        }


        return originalText.toString();

    }




    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Vigenere mesaj = new Vigenere();
        System.out.println("Enter the message : ");
        mesaj.message= input.nextLine();
        System.out.println("Enter the key : ");
        mesaj.key = input.nextLine();

        mesaj.encrypt(mesaj.toUpperString(mesaj.message), mesaj.toUpperString(mesaj.key));
    }
}


