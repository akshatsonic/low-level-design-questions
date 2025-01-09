import java.util.Date;

public class Transaction {
    public Transaction(Account sourceAccount, Account targetAccount, double amount, Date timestamp, Currency currency) {
        this.sourceAccount = sourceAccount;
        this.targetAccount = targetAccount;
        this.amount = amount;
        this.timestamp = timestamp;
        this.currency = currency;
    }

    private Account sourceAccount;
    private Account targetAccount;
    private double amount;
    private Date timestamp;
    private Currency currency;

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public Account getTargetAccount() {
        return targetAccount;
    }

    public double getAmount() {
        return amount;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Currency getCurrency() {
        return currency;
    }
}
