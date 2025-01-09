import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DigitalWallet {
    private static DigitalWallet instance;
    Map<String, WalletUser> users;
    Map<String, PaymentMethod> paymentMethods;
    Map<String, Account> accounts;

    DigitalWallet(){
        this.users = new HashMap<>();
        this.paymentMethods = new HashMap<>();
        this.accounts = new HashMap<>();
    }

    public static synchronized DigitalWallet getInstance(){
        if(instance == null){
            return new DigitalWallet();
        }
        return instance;
    }

    public void addUser(WalletUser user){
        this.users.put(user.getUserId(), user);
    }

    public void addPaymentMethod(PaymentMethod method){
        this.paymentMethods.put(method.getId(), method);
    }

    public void addAccount(Account account){
        this.accounts.put(account.getAccountNumber(), account);
    }

    public Map<String,WalletUser> getUsers() {
        return users;
    }

    public Map<String,PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public Map<String,Account> getAccounts() {
        return accounts;
    }

    public void transferFunds(Account sourceAccount, Account targetAccount, Double amount, Currency currency){
        Double amountToBeDeducted = CurrencyUtils.convert(amount, currency, sourceAccount.getCurrency());
        Double amountToBeDeposited = CurrencyUtils.convert(amount, currency, targetAccount.getCurrency());
        sourceAccount.withdraw(amountToBeDeducted);
        targetAccount.deposit(amountToBeDeposited);

        Transaction transaction = new Transaction(sourceAccount, targetAccount, amount, Date.from(Instant.now()), currency);
        sourceAccount.addTransaction(transaction);
        targetAccount.addTransaction(transaction);
    }
}
