import java.util.Scanner;

public class GeneralATM {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);


        String user_choice;
        while (true) {
            System.out.println("""
                    >>>>> GENERAL ATM
                    ENTER [1] TO WORK WITH BANK ACCOUNT DATA
                    ENTER [0] TO EXIT:""");
            user_choice = in.next();

            if (user_choice.equals("1"))
                start(in);

            else if (user_choice.equals("0"))
                break;

            else
                System.out.println(">>>>> ERROR: Incorrect input.");
        }
    }

    public static void start(Scanner os) {
        String user_input, type = "none";
        int user_index = 0;
        System.out.println(">>>>> LOG IN\n" + "Please, enter number of an account: ");
        user_input = os.next();

        int i;
        for (i = 0; i < Database.database.length; i++) {
            if (user_input.equals(Database.database[i].getAccountNumber()) && Database.database[i] instanceof NationalBankAccount && !(((NationalBankAccount) Database.database[i]).isBlocked)) {
                type = "nba";
                user_index = i;
                break;
            }

            else if (user_input.equals(Database.database[i].getAccountNumber()) && Database.database[i] instanceof CityBankAccount && !(((CityBankAccount) Database.database[i]).isBlocked)) {
                type = "cba";
                user_index = i;
                break;
            }
        }

        if (type.equals("nba")) {
            System.out.println("The account was found.");

            if (correctPinIsReached(user_index, os)) {
                System.out.print("Correct password. You will be redirected to National Bank ATM menu.");
                nationalBankATM(user_index);
            }

            else {
                System.out.println(">>>>> ERROR: More than 3 attempts. Account is blocked :(");
                Database.database[user_index].setIsBlocked(true);
            }
        }

        else if (type.equals("cba")) {
            System.out.println("The account was found.");

            if (correctPinIsReached(user_index, os)) {
                System.out.print("Correct password. You will be redirected to City Bank ATM menu.");
                cityBankATM(user_index);
            }

            else {
                System.out.println(">>>>> ERROR: More than 3 attempts. Account is blocked :(");
                Database.database[user_index].setIsBlocked(true);
            }
        }

        else
            System.out.println(">>>>> ERROR: No such account in database or account is blocked.");
    }

    public static void nationalBankATM(int account_index) {
        System.out.println("""
                >>>>> NATIONAL BANK ATM""");
        NationalBankATM nationalBankATM = new NationalBankATM(account_index);
        nationalBankATM.main(null);
    }

    public static void cityBankATM(int account_index) {
        System.out.println("""
                >>>>> CITY BANK ATM""");
        CityBankATM cityBankATM = new CityBankATM(account_index);
        cityBankATM.main(null);
    }

    public static boolean checkPIN(String input, String necessary) {
        return input.equals(necessary);
    }

    public static boolean correctPinIsReached(int account_index, Scanner os) {
        String user_input;
        boolean isReached = false;

        System.out.print("Please, enter PIN-code: ");
        user_input = os.next();
        if (checkPIN(user_input, Database.database[account_index].getPinCode()))
            isReached = true;

        else {
            int i = 0;
            while (i < 3) {
                System.out.print(">>>>> ERROR: Incorrect PIN. You have " + (3 - i) + " attempt(s).\nPlease, enter correct PIN: ");
                user_input = os.next();

                if (checkPIN(user_input, Database.database[account_index].getPinCode())) {
                    isReached = true;
                    break;
                }

                else
                    i++;
            }
        }

        return isReached;
    }
}
