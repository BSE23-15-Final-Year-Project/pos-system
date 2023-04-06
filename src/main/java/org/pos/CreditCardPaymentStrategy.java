package org.pos;

public class CreditCardPaymentStrategy implements PaymentStrategy {
    private final String cardNumber;
    private final String expirationDate;
    private final String cvv;

    public CreditCardPaymentStrategy(String cardNumber, String expirationDate, String cvv) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }

    @Override
    public String pay(double amount) {
        return "Paid shs" + amount + " with credit card " + cardNumber;
    }
}
