package Casino.src;

import java.util.Scanner;

import static Casino.src.Main.game_selection;
import static Casino.src.Main.userTotal;
import static Casino.src.card_handler.*;

public class Blackjack {
    static String[] userCards = new String[8];static String[] dealerCards = new String[8];
    static boolean split_chance = false;
    static int user_BJ_val = 0; static int dealer_BJ_val = 0;static int user_bet = 0;
    static String green = "\u001B[32m";static String reset = "\u001B[0m";
    static int card_deck_num = 3;static int user_card_num = 1;static int dealer_card_num = 1;

    public static void main() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\noi ello m8. Welcome to Blackjack");
        System.out.println("Current account value: "+userTotal);
        System.out.println("How much would you like to bet?");
        System.out.print("> ");
        user_bet = Integer.parseInt(scanner.nextLine());
        if(user_bet>userTotal){
            System.out.println("bruv you dont have the money");
            main();
        }
        System.out.println("\nGood luck!");
        BlackJackStart();
        BlackJackCont();
    }

    public static void BlackJackStart(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Dealing Cards...........");
        timeDelay(1);
        userCards[0] = card_handler.getCard();
        userCards[1] = card_handler.getCard();
        System.out.println("\nUser card: "+ green +userCards[0]+ reset);
        System.out.println("\nUser card: "+ green +userCards[1]+ reset);

    }
    public static void BlackJackCont(){
        boolean continue_playing = true;
        boolean user_bust = false;
        while(continue_playing){
            System.out.println("\nWhat would you like to do now");
            System.out.println("1 - Hit\n2 - Stay\n3 - Double");
            if(split_chance){
                System.out.println("4 - Split");
            }
            Scanner scanner = new Scanner(System.in);
            System.out.print("> ");
            int Blackjack_input = Integer.parseInt(scanner.nextLine());
            if (Blackjack_input == 1){
               // if(!Grab_user_card()){
                    System.out.println("User busted");
                    //code in user busted
                }
            }
    }

    public static void timeDelay(double seconds){
        double realTime = seconds * 1000;
        try {
            Thread.sleep((long) realTime);
        } catch (InterruptedException ignored) {}
    }
}
