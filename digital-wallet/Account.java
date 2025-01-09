import java.util.List;

public class Account {
    private int id;
    private WalletUser walletUser;
    private double balance;
    private String accountNumber;
    private Currency currency;
    private List<Transaction> transactions;

    public Account(int id, WalletUser walletUser, double balance, String accountNumber, Currency currency, List<Transaction> transactions) {
        this.id = id;
        this.walletUser = walletUser;
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.currency = currency;
        this.transactions = transactions;
    }

    public int getId() {
        return id;
    }

    public WalletUser getUser() {
        return walletUser;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Currency getCurrency() {
        return currency;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void deposit(Double amount){
        this.balance += amount;
    }

    public void withdraw(Double amount){
        if(balance<amount) {
            throw new InsufficientWalletBalanceException("Insufficient Wallet Funds for accountNumber: "+ this.accountNumber);
        }
        this.balance-=amount;
    }

    public void addTransaction(Transaction transaction){
        this.transactions.add(transaction);
    }
}
