package org.pos;

public interface PaymentStrategy {
    String pay(double amount);
}
