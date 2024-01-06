package Casino.src;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import static Casino.src.Main.*;
import static Casino.src.card_handler.*;
import static Casino.src.card_handler.bjCardVal;
import static Casino.src.card_handler.GotAnAce;

public class Blackjack {
    static String[] userCards = new String[8];static String[] dealerCards = new String[8];
    static boolean split_chance = false;
    static int user_BJ_val = 0; static int dealer_BJ_val = 0;static int user_bet = 0;
    static String green = "\u001B[32m";static String reset = "\u001B[0m";
    static int card_deck_num = 3;static int user_card_num = 1;static int dealer_card_num = 1;

    public static void main() throws IOException {
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
        String[] parts = userCards[0].split(" ", 2);
        String firstPart = parts[0];
        user_BJ_val = bjCardVal;
        userCards[1] = card_handler.getCard();
        String[] parts2 = userCards[1].split(" ", 2);
        String secondPart = parts2[0];
        if(firstPart.equals(secondPart)){
            split_chance = true;
        }
        user_BJ_val = user_BJ_val + bjCardVal;
        dealerCards[0] = card_handler.getCard();
        dealer_BJ_val = bjCardVal;
        System.out.println("\nUser card: " + green + userCards[0] + reset);
        timeDelay(1);
        System.out.println("\nUser card: " + green + userCards[1] + reset);
        timeDelay(1);
        System.out.println("\nDealer card: " + green + dealerCards[0] + reset);
        timeDelay(1);

    }
    public static void BlackJackCont() throws IOException {
        boolean continue_playing = true;
        while(continue_playing){
            System.out.println("\nWhat would you like to do now");
            System.out.println("1 - Hit\n2 - Stay");
            if(user_bet*2<userTotal){
                System.out.println("3 - Double");
            }
            if(split_chance){
                System.out.println("4 - Split");
            }
            Scanner scanner = new Scanner(System.in);
            System.out.print("> ");
            int Blackjack_input = Integer.parseInt(scanner.nextLine());
            if (Blackjack_input == 1){
              bj_Hit();
            }
            if (Blackjack_input == 2){
                continue_playing = false;
                Dealer_cards_deal();
                if(user_BJ_val>dealer_BJ_val){
                    System.out.println("User win.");
                    userTotal = userTotal + user_bet;
                    System.out.println("New user total: "+green+userTotal+reset);
                    updateAccountValue(String.valueOf(userTotal));
                    timeDelay(1);
                    game_selection();
                    System.exit(0);
                }
                if(dealer_BJ_val>user_BJ_val){
                    System.out.println("User Lost.");
                    userTotal = userTotal - user_bet;
                    System.out.println("New user total: "+green+userTotal+reset);
                    updateAccountValue(String.valueOf(userTotal));
                    timeDelay(1);
                    game_selection();
                }
                if(dealer_BJ_val == user_BJ_val){
                    System.out.println("Push");
                    timeDelay(1);
                    game_selection();
                    System.exit(0);
                }
            }
            if (Blackjack_input == 3){
                if(user_bet*2<userTotal){
                    user_bet = user_bet * 2;
                    System.out.println("User bet: "+green+user_bet+reset);
                    bj_Hit();
                }
                Dealer_cards_deal();
                if(user_BJ_val>dealer_BJ_val){
                    System.out.println("User win.");
                    userTotal = userTotal + user_bet;
                    System.out.println("New user total: "+green+userTotal+reset);
                    updateAccountValue(String.valueOf(userTotal));
                    timeDelay(1);
                    game_selection();
                    System.exit(0);
                }
                if(dealer_BJ_val>user_BJ_val){
                    System.out.println("User Lost.");
                    userTotal = userTotal - user_bet;
                    System.out.println("New user total: "+green+userTotal+reset);
                    updateAccountValue(String.valueOf(userTotal));
                    game_selection();
                    System.exit(0);
                }
                if(dealer_BJ_val == user_BJ_val){
                    System.out.println("Push");
                    timeDelay(1);
                    game_selection();
                    System.exit(0);
                }
            }
            if (Blackjack_input == 4){
                //bj_Split();
            }
            if(user_BJ_val>21){
                if(GotAnAce){
                    user_BJ_val = user_BJ_val - 10;
                }else{
                    continue_playing = false;
                    System.out.println("User bust, bet lost");
                    userTotal = userTotal - user_bet;
                    System.out.println("New user total: "+green+userTotal+reset);
                    updateAccountValue(String.valueOf(userTotal));
                    timeDelay(1);
                    game_selection();
                    System.exit(0);
                }

            }
        }
    }
    public static void bj_Hit(){
        user_card_num++;
        userCards[user_card_num] = card_handler.getCard();
        user_BJ_val = user_BJ_val + bjCardVal;
        timeDelay(1);
        System.out.println("User card: "+green+userCards[user_card_num]+reset);
    }
    public static void Dealer_cards_deal() throws IOException {
        while(dealer_BJ_val<18){
            dealer_card_num++;
            dealerCards[dealer_card_num] = card_handler.getCard();
            dealer_BJ_val = dealer_BJ_val + bjCardVal;
            timeDelay(0.5);
            System.out.println("Dealer card: "+green+dealerCards[dealer_card_num]+reset);
            if(dealer_BJ_val>21){
                System.out.println("Dealer bust");
                userTotal = userTotal + user_bet;
                System.out.println("New user total: "+userTotal);
                updateAccountValue(String.valueOf(userTotal));
                timeDelay(1);
                game_selection();
            }
        }

    }
    public static void updateAccountValue(String newValue) throws IOException {
        String filename = "Casino_logins";
        File file = new File(filename);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<String> lines = new ArrayList<>();
        String line;
        boolean found = false;
        String enter_pass = encryption_tool(1,user_password);
        username = encryption_tool(1,username);
        newValue = encryption_tool(1,newValue);
        while ((line = reader.readLine()) != null) {
            lines.add(line);
            if (line.equals(username)) { // Username found
                found = true;
                lines.add(enter_pass);
                reader.readLine(); // Skip password line
                lines.add(String.valueOf(newValue)); // Update account value
                reader.readLine(); // Skip old account value line
            }
        }
        reader.close();

        if (found) {
            // Write the updated content back to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (String l : lines) {
                writer.write(l);
                writer.newLine();
            }
            writer.close();
        } else {
            System.out.println("Username not found.");
        }
    }


    public static void timeDelay(double seconds){
        double realTime = seconds * 1000;
        try {
            Thread.sleep((long) realTime);
        } catch (InterruptedException ignored) {}
    }
}
