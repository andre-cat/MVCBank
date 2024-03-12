package Bank.Bank.Utils;

import Bank.Bank.Entities.Account;
import Bank.Bank.Entities.Transaction;

// Concrete strategy
public class PurchaseStrategy implements TransactionStrategy {

    @Override
    public double execute(Transaction transaction, Account account) {
        double newBalance = account.getBalance() - transaction.getValue() - transaction.getCost();
        return newBalance;
    }

    @Override
    public double reverse(Transaction transaction, Account account) {
        double newBalance = account.getBalance() + transaction.getValue() + transaction.getCost();
        return newBalance;
    }

    @Override
    public double calculateCost(Transaction transaction) {
        double cost = 0;

        switch (transaction.getSubtype()) {
            case CARDONLINESTORE:
                cost = 0;
                break;
            case CARDPHYSICALSTORE:
                cost = 5;
                break;
        }

        return cost;
    }
}
