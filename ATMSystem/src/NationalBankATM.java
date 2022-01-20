import java.util.Scanner;

public class NationalBankATM {
    int index;

    public NationalBankATM(int index) {
        this.index = index;
    }

    public void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String user_choice;
        label:
        while (true) {
            System.out.println("""
                    >>>>> National Bank ATM
                    ENTER [1] TO CASH WITHDRAWAL
                    ENTER [2] TO VIEW BALANCE
                    ENTER [4] TO CHANGE ACCOUNT TYPE (CB ATM)
                    PRESS [3] TO EXIT:""");
            user_choice = in.next();

            switch (user_choice) {
                case "1":
                    cashWithdrawal(in, index);
                    break;
                case "2":
                    viewBalance(index);
                    break;
                case "4":
                    convertAccountType(in, index);
                    break;
                case "3":
                    break label;
                default:
                    System.out.println(">>>>> ERROR: INCORRECT INPUT");
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

    public static void convertAccountType(Scanner os, int user_index) {
        String user_input;
        System.out.println(">>>>> CHANGE TYPE\nYour account type will be changed into National Bank Account. Are you sure?(\nPRESS [1] TO CONTINUE\nPRESS [2] TO EXIT):");
        user_input = os.next();

        if (user_input.equals("1")) {
            Database.database[user_index].setIsConverted(true);

            String[] user_data = Database.database[user_index].accountData().split(" ");
            CityBankAccount newAccount = new CityBankAccount(user_data[0], user_data[1], (Integer.parseInt(user_data[2])), user_data[3], user_data[4], (Boolean.parseBoolean(user_data[5])), (Boolean.parseBoolean(user_data[6])));

            Database.database[user_index] = newAccount;

            System.out.println(">>>>> SUCCESS: Account was successfully converted to City Bank Account. You will be redirected to City Bank ATM menu...");

            CityBankATM cityBankATM = new CityBankATM(user_index);
            cityBankATM.main(null);
        }
    }
}
