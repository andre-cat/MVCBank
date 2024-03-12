package Bank.Bank.Repository;

import Bank.Bank.Entities.Account;
import Bank.Bank.Entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(value="SELECT a FROM Account a WHERE a.id = :accountid", nativeQuery = false)
    public Account selectAccountById(@Param("accountid") long accountId);

    @Query(value="SELECT t FROM Transaction t WHERE t.accountId = :accountid", nativeQuery = false)
    public List<Transaction> selectAllByAccountId(@Param("accountid") long accountId);

    @Modifying
    @Query(value="UPDATE Account a SET a.balance = :accountbalance where a.id = :accountid", nativeQuery = false)
    public void updateAccountBalanceById(@Param("accountid") long accountId, @Param("accountbalance") double balance);
}
