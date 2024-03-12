package Bank.Bank.Entities;

import Bank.Bank.DTO.AccountDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Accounts")
public class Account {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Id
    private long id;

    @Column(name = "balance")
    private double balance;

    @Column(name = "user_id")
    private long userId;

    public Account() {
        this.id = 0;
        this.balance = 0;
        this.userId = 0;
    }

    public Account(long id, long balance, long userId) {
        this.id = id;
        this.balance = balance;
        this.userId = userId;
    }

    public Account(AccountDTO accountDTO) {
        this.id = accountDTO.getId();
        this.balance = accountDTO.getBalance();
        this.userId = accountDTO.getUserId();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
