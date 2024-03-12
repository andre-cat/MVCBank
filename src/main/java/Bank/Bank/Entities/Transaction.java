package Bank.Bank.Entities;

import Bank.Bank.DTO.TransactionDTO;
import Bank.Bank.Types.TransactionSubtype;
import Bank.Bank.Types.TransactionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;

import java.util.Date;

@Entity
@Table(name = "Transactions")
public class Transaction {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Id
    private long id;

    @Column(name = "value")
    private double value;

    @Column(name = "cost")
    private double cost;

    @Column(name = "date")
    private Date date;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TransactionType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "subtype")
    private TransactionSubtype subtype;

    @Column(name = "place_id")
    private String placeId;

    @Column(name = "account_id")
    private long accountId;

    public Transaction() {
        this.id = 0;
        this.value = 0;
        this.cost = 0;
        this.date = new Date();
        this.description = "";
        this.type = TransactionType.NONE;
        this.subtype = TransactionSubtype.NONE;
        this.placeId = "";
        this.accountId = 0;
    }

    public Transaction(long id, long value, long cost, Date date, String description, TransactionType type, TransactionSubtype subtype, String placeId, long accountId) {
        this.id = id;
        this.value = value;
        this.cost = cost;
        this.date = date;
        this.description = description;
        this.type = type;
        this.subtype = subtype;
        this.placeId = placeId;
        this.accountId = accountId;
    }

    public Transaction(TransactionDTO transactionDTO) {
        this.id = transactionDTO.getId();
        this.value = transactionDTO.getValue();
        this.cost = transactionDTO.getCost();
        this.date = transactionDTO.getDate();
        this.description = transactionDTO.getDescription();
        this.type = transactionDTO.getType();
        this.subtype = transactionDTO.getSubtype();
        this.placeId = transactionDTO.getPlaceId();
        this.accountId = transactionDTO.getAccountId();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public TransactionSubtype getSubtype() {
        return subtype;
    }

    public void setSubtype(TransactionSubtype subtype) {
        this.subtype = subtype;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }
}
