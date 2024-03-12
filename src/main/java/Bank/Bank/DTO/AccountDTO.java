package Bank.Bank.DTO;

import Bank.Bank.Entities.Account;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class AccountDTO implements Serializable {

    @JsonProperty("id")
    private long id;

    @JsonProperty("balance")
    private double balance;

    @JsonProperty("userId")
    private long userId;

    public AccountDTO() {
        this.id = 0;
        this.balance = 0;
        this.userId = 0;
    }

    public AccountDTO(long id, double balance, long userId) {
        this.id = id;
        this.balance = balance;
        this.userId = userId;
    }

    public AccountDTO(Account account) {
        id = account.getId();
        balance = account.getBalance();
        userId = account.getUserId();
    }

    public long getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public long getUserId() {
        return userId;
    }
}
