public class CityBankAccount implements BankAccount{
    String name;
    String surname;
    int balance;
    String accountNumber;
    String pinCode;
    boolean isBlocked;
    boolean isConverted;

    public CityBankAccount() {
        this.name = "default_cba_name";
        this.surname = "default_cba_surname";
        this.balance = -1;
        this.accountNumber = "default_cba_account_number";
        this.pinCode = "default_cba_pin_code";
        this.isConverted = false;
    }

    public CityBankAccount(String name, String surname, int balance, String accountNumber, String pinCode, boolean isBlocked, boolean isConverted) {
        this.name = name;
        this.surname = surname;
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
        return (name + " " + surname + " " + balance + " " + accountNumber + " " +  pinCode + " " + isBlocked + " " + isConverted);
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
