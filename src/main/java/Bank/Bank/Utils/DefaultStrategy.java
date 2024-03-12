package Bank.Bank.Utils;

import Bank.Bank.Entities.Account;
import Bank.Bank.Entities.Transaction;

public class DefaultStrategy implements TransactionStrategy {

    @Override
    public double execute(Transaction transaction, Account account) {
        return 0;
    }

    @Override
    public double reverse(Transaction transaction, Account account) {
        return 0;
    }

    @Override
    public double calculateCost(Transaction transaction) {
        return 0;
    }
}
