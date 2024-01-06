package Casino.src;
import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    static int userTotal = 0; static String username = ""; static String user_password = "";
    public static void main(String[] args) throws IOException {

        System.out.println("\n\nHello User Welcome to The Clam Betting Services");
        System.out.println("Here you will be able to play a list of ever increasing games");
        System.out.println("\nThis is a continuation of my old casino project that includes other non-casino games");
        System.out.println("Created 12/21/23\n\n");

        System.out.println("Don't gamble responsibly, try drinking while playing");

        System.out.println("\nDo you have an account associated with us?\n\n");
        System.out.println("1 - Login\n2 - Create Account");
        System.out.print("\n> ");

        Scanner scanner = new Scanner(System.in);
        int user_sel_1 = Integer.parseInt(scanner.nextLine());
        if (user_sel_1 == 1) {
            login();
        }
        if (user_sel_1 == 2) {
            Create_Account();
        }
        game_selection();

    }

    public static void game_selection() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWhat would you like to play today?");
        System.out.println("Choose from the following selection");
        System.out.println("1 - Blackjack");
        System.out.print("> ");
        int user_game_choice = Integer.parseInt(scanner.nextLine());
        if (user_game_choice == 1){
            Blackjack.main();
        }
    }
    public static void login() throws IOException {
        System.out.print("Enter username: ");
        Scanner scanner = new Scanner(System.in);
        String userFind = scanner.nextLine();
        String fileName = "Casino_logins";
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;int lineReader = 0;
        boolean reader = false;
        while (!reader) {
            String temp_line = bufferedReader.readLine();
            line = encryption_tool(2,temp_line);
            lineReader++;
            try{
                assert line != null;
                if (line.equals(userFind)) {
                    if((lineReader+2)%3==0){
                        reader = true;
                        System.out.println("Account found!");
                        String Tuserpass = encryption_tool(2,bufferedReader.readLine());
                        String TuservalueA = encryption_tool(2,bufferedReader.readLine());
                        userTotal = Integer.parseInt(TuservalueA);
                        System.out.print("Enter in password: ");
                        String Tpass = scanner.nextLine();
                        if (Tpass.equals(Tuserpass)) {
                            System.out.println("Account login successful!");
                            username = userFind;
                            user_password = Tuserpass;
                            System.out.println("Current account value: " + userTotal);
                            break;
                        }else{
                            System.out.println("Invalid Password, reenter password");
                            login();
                        }
                    } else{
                        System.out.println("you really tried to enter your password");
                    }
                }

            } catch (NullPointerException e) {
                System.out.println("Invalid Login");
                System.exit(0);
            }
        }

    }
    public static void Create_Account() throws IOException {
        String filepath = "Casino_logins";
        Scanner scanner;
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath, true));
        scanner = new Scanner(System.in);
        System.out.print("\nEnter in Username: ");
        String username_cre = scanner.nextLine();
        System.out.println("Is this correct: " + username_cre + "?\nY or N");
        System.out.print("> ");
        String confirm = scanner.nextLine();
        if (confirm.equals("n") || confirm.equals("N")) {
            System.out.print("\nReenter username");
            Create_Account();
        }

        System.out.print("\nEnter in password: ");
        String password = scanner.nextLine();
        System.out.print("\nReenter password: ");
        String password_check = scanner.nextLine();
        if (password_check.equals(password)) {
            System.out.println("Passwords match");
            username = username_cre;
            user_password = password;
            writer.write(Objects.requireNonNull(encryption_tool(1, username_cre)));
            writer.newLine();
            writer.write(Objects.requireNonNull(encryption_tool(1,password)));
            writer.newLine();
        } else {
            System.out.println("Passwords don't match, reenter account details");
            Create_Account();
        }
        String temp_int_deposit = account_create_deposit();
        writer.write(Objects.requireNonNull(encryption_tool(1, temp_int_deposit)));
        writer.newLine();
        writer.close();
        }
    public static String account_create_deposit(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("How much would you like to deposit?");
        System.out.print("> ");
        String user_deposit = scanner.nextLine();
        System.out.println("Is this the correct amount for deposit: " + user_deposit);
        System.out.println("Enter Y or N");
        String user_response = scanner.nextLine();
        userTotal = Integer.parseInt(user_deposit);
        if (user_response.equals("Y") || user_response.equals("y")){
            return user_deposit;
        }else{
            return account_create_deposit();
        }
    }
    public static String encryption_tool(int dec, String content) throws IOException {
        if (dec == 1) {                                         //encryptor
            String[] dissect = content.split("");
            int unicodeValue;
            int[] changed = new int[dissect.length];
            for (int i = 0; i < dissect.length; i++) {
                char c = dissect[i].charAt(0);
                if (Character.isLetter(c)) {
                    unicodeValue = (int) c;
                    int tempval = (unicodeValue * 3);
                    changed[i] = tempval;
                } else if (Character.isDigit(c)) {
                    unicodeValue = (int) c;
                    int tempval = (unicodeValue * 3);
                    changed[i] = tempval;
                } else {
                    changed[i] = 777;
                }
            }
            StringBuilder Encrypted = new StringBuilder();
            Encrypted.append(changed[0]);
            for (int i = 1; i < changed.length; i++) {
                Encrypted.append(":").append(changed[i]);
            }
            return Encrypted.toString();
        }
        if(dec == 2){                                       //Decrypt
            String[] disect = content.split(":");
            int[] disectNew = new int[disect.length];
            for (int i = 0; i < disect.length; i++) {
                disectNew[i] = Integer.parseInt(disect[i]);
            }
            char[] changed = new char[disectNew.length];

            for (int i = 0; i < disect.length; i++) {
                int numericValue = disectNew[i];
                char decodedChar;

                if (numericValue == 777) {
                    decodedChar = ' ';
                } else {
                    int tempval = (numericValue / 3);
                    char[] originalCharacter = Character.toChars(tempval);
                    decodedChar = originalCharacter[0];

                }
                changed[i] = decodedChar;
            }
            StringBuilder Decrypted = new StringBuilder();
            for (char c : changed) {
                Decrypted.append(c);
            }
            return Decrypted.toString();
        }
        return null;
    }
    // 1 - Encryption   2 - get the decoded phrase
}