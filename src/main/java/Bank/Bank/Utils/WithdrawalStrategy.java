package Bank.Bank.Utils;

import Bank.Bank.Entities.Account;
import Bank.Bank.Entities.Transaction;

public class WithdrawalStrategy implements TransactionStrategy {

    @Override
    public double execute(Transaction transaction, Account account) {
        double cost = calculateCost(transaction);
        transaction.setCost(cost);

        double newBalance = account.getBalance() - transaction.getValue() - transaction.getCost();
        return newBalance;
    }

    @Override
    public double reverse(Transaction transaction, Account account) {
        double cost = calculateCost(transaction);
        transaction.setCost(cost);

        double newBalance = account.getBalance() + transaction.getValue() + transaction.getCost();
        return newBalance;
    }

    @Override
    public double calculateCost(Transaction transaction) {
        double cost = 0;

        switch (transaction.getSubtype()) {
            case ATM:
                cost = 1;
                break;
        }

        return cost;
    }
}
