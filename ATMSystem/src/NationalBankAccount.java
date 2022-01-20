public class NationalBankAccount implements BankAccount{
    String fullName;
    int balance;
    String accountNumber;
    String pinCode;
    boolean isBlocked;
    boolean isConverted;

    public NationalBankAccount() {
        this.fullName = "default_nba_full_name";
        this.accountNumber = "default_nba_account_number";
        this.balance = -1;
        this.pinCode = "default_nba_pin_code";
        this.isBlocked = false;
        this.isConverted = false;
    }

    public NationalBankAccount(String fullName, int balance, String accountNumber, String pinCode, boolean isBlocked, boolean isConverted) {
        this.fullName = fullName;
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.pinCode = pinCode;
        this.isBlocked = isBlocked;
        this.isConverted = isConverted;
    }

    @Override
    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public String getPinCode() {
        return pinCode;
    }

    @Override
    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    @Override
    public int totalBalance() {
        return balance;
    }

    @Override
    public void creditBalance(int credit) {
        this.balance -= credit;
    }

    @Override
    public void debitBalance(int debit) {
        this.balance += debit;
    }

    @Override
    public String accountData() {
        return (fullName + " " + balance + " " + accountNumber + " " + pinCode + " " + isBlocked + " " + isConverted);
    }

    @Override
    public void setIsBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    @Override
    public void setIsConverted(boolean isConverted) {
        this.isConverted = isConverted;
    }

    @Override
    public boolean getIsConverted() {
        return isConverted;
    }
}
