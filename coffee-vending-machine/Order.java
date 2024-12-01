import coffee.CoffeeType;

public class Order {
    Integer orderId;
    Payment payment;
    CoffeeType coffeeType;

    public CoffeeType getCoffeeType() {
        return coffeeType;
    }

    public Payment getPayment() {
        return payment;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public Order(Integer orderId, Payment payment, CoffeeType coffeeType) {
        this.orderId = orderId;
        this.payment = payment;
        this.coffeeType = coffeeType;
    }
}
