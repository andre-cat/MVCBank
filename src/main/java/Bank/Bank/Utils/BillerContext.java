package Bank.Bank.Utils;

import Bank.Bank.Entities.Account;
import Bank.Bank.Entities.Transaction;
import org.springframework.stereotype.Component;

@Component
public class BillerContext {

    private TransactionStrategy transactionStrategy;

    public void setTransactionStrategy(TransactionStrategy transactionStrategy) {
        this.transactionStrategy = transactionStrategy;
    }

    public double executeTransaction(Transaction transaction, Account account) {
        return transactionStrategy.execute(transaction, account);
    }

    public double reverseTransaction(Transaction transaction, Account account) {
        return transactionStrategy.reverse(transaction, account);
    }
}
