public class Database {
    public static BankAccount[] database = new BankAccount[10];
    static {
        int i;
        for (i = 0; i < database.length; i++) {
            if (i >= 0 && i < 5) {
                String data;
                data = ("Name" + i) + (" Surname" + i + " " + (20000+(i*1000)) + " KZ00000000" + i + " 010" + i);
                String[] container = data.split(" ");

                database[i] = new CityBankAccount(container[0], container[1], Integer.parseInt(container[2]), container[3], container[4], false, false);
            }

            else if (i >= 5) {
                String data;
                data = ("FullName" + i + " " + (35000+(i*1000)) + " KZ00000000" + i + " 020" + i);
                String[] container = data.split(" ");

                database[i] = new NationalBankAccount(container[0], Integer.parseInt(container[1]), container[2], container[3], false, false);
            }
        }
    }
}
