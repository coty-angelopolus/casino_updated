package Casino.src;

import java.util.HashSet;
import java.util.Random;

public class card_handler {
    static int newRandom;
    static HashSet<Integer> numbers = new HashSet<>();
    static int bjCardVal = 0;
    static boolean GotAnAce = false;
    public static String getCard() {
        Random rand = new Random();
        do{
            newRandom = rand.nextInt(52)+1;
        }while(numbers.contains(newRandom));
        numbers.add(newRandom);
        return NumToCard(newRandom);

    }
    public static String NumToCard(int num){
        String printValue = "";
        if (num <= 13){
            if(num == 1){
                printValue = "Ace of Hearts";
                bjCardVal = 11;
                GotAnAce = true;
            } else if (num >= 2 && num <= 9) {
                printValue = num + " of Hearts";
                bjCardVal = num;
            } else if (num == 10) {
                printValue = "10 of Hearts";
                bjCardVal = 10;
            } else if (num == 11) {
                printValue = "Jack of Hearts";
                bjCardVal = 10;
            } else if (num == 12) {
                printValue = "Queen of Hearts";
                bjCardVal = 10;
            } else if (num == 13) {
                printValue = "King of Hearts";
                bjCardVal = 10;
            }
        }else if(num<=26){
            num = num - 13;
            if(num == 1){
                printValue = "Ace of Clubs";
                bjCardVal = 11;
                GotAnAce = true;
            } else if (num >= 2 && num <= 9) {
                printValue = num + " of Clubs";
                bjCardVal = num;
            } else if (num == 10) {
                printValue = "10 of Clubs";
                bjCardVal = 10;
            } else if (num == 11) {
                printValue = "Jack of Clubs";
                bjCardVal = 10;
            } else if (num == 12) {
                printValue = "Queen of Clubs";
                bjCardVal = 10;
            } else if (num == 13) {
                printValue = "King of Clubs";
                bjCardVal = 10;
            }
        }else if(num<=39){
            num = num - 26;
            if(num == 1){
                printValue = "Ace of Spades";
                bjCardVal = 11;
                GotAnAce = true;
            } else if (num >= 2 && num <= 9) {
                printValue = num + " of Spades";
                bjCardVal = num;
            } else if (num == 10) {
                printValue = "10 of Spades";
                bjCardVal = 10;
            } else if (num == 11) {
                printValue = "Jack of Spades";
                bjCardVal = 10;
            } else if (num == 12) {
                printValue = "Queen of Spades";
                bjCardVal = 10;
            } else if (num == 13) {
                printValue = "King of Spades";
                bjCardVal = 10;
            }
        }else{
            num = num - 39;
            if(num == 1){
                printValue = "Ace of Diamonds";
                bjCardVal = 11;
                GotAnAce = true;
            } else if (num >= 2 && num <= 9) {
                printValue = num + " of Diamonds";
                bjCardVal = num;
            } else if (num == 10) {
                printValue = "10 of Diamonds";
                bjCardVal = 10;
            } else if (num == 11) {
                printValue = "Jack of Diamonds";
                bjCardVal = 10;
            } else if (num == 12) {
                printValue = "Queen of Diamonds";
                bjCardVal = 10;
            } else if (num == 13) {
                printValue = "King of Diamonds";
                bjCardVal = 10;
            }
        }
        return printValue;
    }
}
