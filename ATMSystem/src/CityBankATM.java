import java.util.Scanner;

public class CityBankATM {
    int index;

    public CityBankATM(int index) {
        this.index = index;
    }

    public void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String user_choice;
        label:
        while (true) {
            System.out.println("""
                    >>>>> City Bank ATM
                    ENTER [1] TO CASH WITHDRAWAL
                    ENTER [2] TO VIEW BALANCE
                    ENTER [3] TO CHANGE PIN CODE
                    ENTER [4] TO CASH IN ACCOUNT
                    PRESS [5] TO VIEW ACCOUNT DATA
                    PRESS [5] TO CHANGE ACCOUNT TYPE (NB ATM)
                    PRESS [6] TO EXIT:""");
            user_choice = in.next();

            switch (user_choice) {
                case "1":
                    cashWithdrawal(in, index);
                    break;
                case "2":
                    viewBalance(index);
                    break;
                case "3":
                    changePinCode(in, index);
                    break;
                case "4":
                    cashInMoney(in, index);
                    break;
                case "5":
                    viewAccountData(index);
                    break;
                case "6":
                    convertAccountType(in, index);
                    break;
                case "0":
                    break label;
            }
        }
    }

    public static void cashWithdrawal(Scanner os, int user_index) {
        System.out.print(">>>>> WITHDRAW MONEY\n" +
                "Enter sum of withdrawal money: ");
        int withdrawal_money = os.nextInt();

        if (Database.database[user_index].getIsConverted()) {
            if (Database.database[user_index].totalBalance() < (withdrawal_money + 200))
                System.out.println(">>>>> ERROR: Not enough balance to withdraw. (Current balance: " + Database.database[user_index].totalBalance() + ")");
            else {
                Database.database[user_index].creditBalance((withdrawal_money + 200));
                System.out.println(">>>>> Successful operation");
            }
        }

        else {
            if (Database.database[user_index].totalBalance() < withdrawal_money)
                System.out.println(">>>>> ERROR: Not enough balance to withdraw. (Current balance: " + Database.database[user_index].totalBalance() + ")");
            else {
                Database.database[user_index].creditBalance(withdrawal_money);
                System.out.println(">>>>> Successful operation");
            }
        }
    }

    public static void viewBalance(int user_index) {
        System.out.println(">>>>> CURRENT BALANCE: " + Database.database[user_index].totalBalance());
    }

    public static void changePinCode(Scanner os, int user_index) {
        String user_input;
        System.out.print(">>>>> CHANGE PIN-CODE\nEnter current PIN: ");
        user_input = os.next();

        if (user_input.equals(Database.database[user_index].getPinCode())) {
            System.out.println("\tCorrect PIN. Now, enter new PIN: ");
            user_input = os.next();

            Database.database[user_index].setPinCode(user_input);
            System.out.println("New PIN was set.");
        }

        else
            System.out.println(">>>>> ERROR: Incorrect PIN. Try again later");
    }

    public static void cashInMoney(Scanner os, int user_index) {
        System.out.print(">>>>> CASH IN\nEnter necessary sum: ");
        int user_input = os.nextInt();
        Database.database[user_index].debitBalance(user_input);
        System.out.println(">>>>> SUCCESS\n" + user_input + " was cashed in into your balance.");
    }

    public static void viewAccountData(int user_index) {
        System.out.println(Database.database[user_index].accountData());
    }

    public static void convertAccountType(Scanner os, int user_index) {
        String user_input;
        System.out.println(">>>>> CHANGE TYPE\nYour account type will be changed into National Bank Account. Are you sure?(\nPRESS [1] TO CONTINUE\nPRESS [2] TO EXIT):");
        user_input = os.next();

        if (user_input.equals("1")) {
            String[] user_data = Database.database[user_index].accountData().split(" ");
            NationalBankAccount newAccount = new NationalBankAccount((user_data[0] + " " + user_data[1]), (Integer.parseInt(user_data[2])), user_data[3], user_data[4], (Boolean.parseBoolean(user_data[5])), (Boolean.parseBoolean(user_data[6])));

            Database.database[user_index] = newAccount;

            System.out.println(">>>>> SUCCESS: Account was successfully converted to National Bank Account. You will be redirected to National Bank ATM menu...");

            NationalBankATM nationalBankATM = new NationalBankATM(user_index);
            nationalBankATM.main(null);
        }
    }
}
