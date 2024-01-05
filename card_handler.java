package Casino.src;

import java.util.HashSet;
import java.util.Random;

public class card_handler {
    static int newRandom;
    static HashSet<Integer> numbers = new HashSet<>();
    static int userHandVal = 0;
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
                printValue = ("Ace of Hearts");
            }else if(10 <= num){
                printValue = (num + " of Hearts");
            }
            if(num == 11){
                printValue = ("Jack of Hearts");
            }
            if(num == 12){
                printValue = ("Queen of Hearts");
            }
            if(num == 13){
                printValue = ("King of Hearts");
            }
        }else if(num<=26){
            num = num - 13;
            if(num == 1){
                printValue = ("Ace of Clubs");
            }else if(10 <= num){
                printValue = (num + " of Clubs");
            }
            if(num == 11){
                printValue = ("Jack of Clubs");
            }
            if(num == 12){
                printValue = ("Queen of Clubs");
            }
            if(num == 13){
                printValue = ("King of Clubs");
            }
        }else if(num<=39){
            num = num - 26;
            if(num == 1){
                printValue = ("Ace of Spades");
            }else if(10 <= num){
                printValue = (num + " of Spades");
            }
            if(num == 11){
                printValue = ("Jack of Spades");
            }
            if(num == 12){
                printValue = ("Queen of Spades");
            }
            if(num == 13){
                printValue = ("King of Spades");
            }
        }else{
            num = num - 39;
            if(num == 1){
                printValue = ("Ace of Diamonds");
            }else if(10 <= num){
                printValue = (num + " of Diamonds");
            }
            if(num == 11){
                printValue = ("Jack of Diamonds");
            }
            if(num == 12){
                printValue = ("Queen of Diamonds");
            }
            if(num == 13){
                printValue = ("King of Diamonds");
            }
        }
        return printValue;
    }
}
