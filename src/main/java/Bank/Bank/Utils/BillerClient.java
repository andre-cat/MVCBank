package Bank.Bank.Utils;

import Bank.Bank.Entities.Account;
import Bank.Bank.Entities.Transaction;

public class BillerClient {

    private Account account;
    private double currentBalance;
    private BillerContext billerContext;

    public BillerClient(Account account) {
        this.account = account;
        currentBalance = this.account.getBalance();
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void insertTransaction(Transaction transaction) {
        BillerContext billerContext = new BillerContext();

        billerContext.setTransactionStrategy(getTransactionStrategy(transaction));

        currentBalance = billerContext.executeTransaction(transaction, account);
    }

    public void updateTransaction(Transaction currentTransaction, Transaction newTransaction) {
        BillerContext billerContext = new BillerContext();

        billerContext.setTransactionStrategy(getTransactionStrategy(currentTransaction));
        currentBalance = billerContext.reverseTransaction(currentTransaction, account);

        account.setBalance(currentBalance);

        billerContext.setTransactionStrategy(getTransactionStrategy(newTransaction));
        currentBalance = billerContext.executeTransaction(newTransaction, account);
    }

    public void deleteTransaction(Transaction transaction) {
        BillerContext billerContext = new BillerContext();

        billerContext.setTransactionStrategy(getTransactionStrategy(transaction));

        currentBalance = billerContext.reverseTransaction(transaction, account);
    }

    private TransactionStrategy getTransactionStrategy(Transaction transaction) {
        switch (transaction.getType()) {
            case DEPOSIT:
                return new DepositStrategy();
            case PURCHASE:
                return new PurchaseStrategy();
            case WITHDRAWAL:
                return new WithdrawalStrategy();
            default:
                return new DefaultStrategy();
        }
    }
}
