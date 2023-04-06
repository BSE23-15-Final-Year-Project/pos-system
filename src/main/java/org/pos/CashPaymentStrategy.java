package org.pos;

public class CashPaymentStrategy implements PaymentStrategy {
    @Override
    public String pay(double amount) {
        return "Paid shs" + amount + " in cash";
    }
}


