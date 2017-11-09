package edu.kit.informatik;

public final class Main {
    public static void main(final String[] args) {
        final Bank bank = new Bank(0);
        for (int i = 0; i < 10; i++) {
            bank.createAccount(i);
        }
        System.out.println(bank);
    }

    private Main() {

    }
}
