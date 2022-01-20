public interface BankAccount {
     String getAccountNumber(); // returns number of an account
     String getPinCode(); // returns pin code
     void setPinCode(String pinCode); // change pin code
     int totalBalance(); // returns total current balance
     void creditBalance(int credit); // for withdrawal money from account
     void debitBalance(int debit); // for addable money to account
     String accountData(); // returns data of a client
     void setIsBlocked(boolean isBlocked); // to set blocked status
     void setIsConverted(boolean isConverted); // to check converted or not
     boolean getIsConverted();
}
