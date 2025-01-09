public abstract class PaymentMethod {
    private String id;
    private WalletUser walletUser;

    public PaymentMethod(String id, WalletUser user) {
        this.id = id;
        this.walletUser = user;
    }

    public abstract boolean processPayment(Double amount, Currency currency);

    public String getId() {
        return id;
    }

    public WalletUser getUser() {
        return walletUser;
    }
}
