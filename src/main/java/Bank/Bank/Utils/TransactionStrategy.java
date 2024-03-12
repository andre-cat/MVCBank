package Bank.Bank.Utils;

import Bank.Bank.Entities.Account;
import Bank.Bank.Entities.Transaction;

// Strategy
public interface TransactionStrategy {

    public abstract double execute(Transaction transaction, Account account);

    public abstract double reverse(Transaction transaction, Account account);

    public abstract double calculateCost(Transaction transaction);

}
