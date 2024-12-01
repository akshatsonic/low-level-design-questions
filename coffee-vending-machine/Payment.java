public class Payment {
    private int userId;
    private PaymentStatus paymentStatus;
    private double amount;
    private double change;


    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public Payment(int userId, PaymentStatus paymentStatus, double amount, double change) {
        this.userId = userId;
        this.paymentStatus = paymentStatus;
        this.amount = amount;
        this.change = change;
    }

    public double getAmount() {
        return amount;
    }

    public double getChange() {
        return change;
    }
}
