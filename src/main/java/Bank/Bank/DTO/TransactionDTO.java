package Bank.Bank.DTO;

import Bank.Bank.Entities.Transaction;
import Bank.Bank.Types.TransactionSubtype;
import Bank.Bank.Types.TransactionType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

public class TransactionDTO implements Serializable {

    @JsonProperty(value = "id")
    private long id;

    @JsonProperty("value")
    private double value;

    @JsonProperty("cost")
    private double cost;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm", timezone = "UTC")
    @JsonProperty("date")
    private Date date;

    @JsonProperty("description")
    private String description;

    @JsonProperty("type")
    private TransactionType type;

    @JsonProperty("subtype")
    private TransactionSubtype subtype;

    @JsonProperty("placeId")
    private String placeId;

    @JsonProperty("accountId")
    private long accountId;

    public TransactionDTO() {
        this.value = 0;
        this.cost = 0;
        this.date = new Date();
        this.description = "";
        this.type = TransactionType.NONE;
        this.subtype = TransactionSubtype.NONE;
        this.placeId = "";
        this.accountId = 0;
    }

    public TransactionDTO(long id, long value, long cost, Date date, String description, TransactionType type, TransactionSubtype subtype, String placeId, long accountId) {
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

    public TransactionDTO(Transaction transaction) {
        id = transaction.getId();
        value = transaction.getValue();
        cost = transaction.getCost();
        date = transaction.getDate();
        description = transaction.getDescription();
        type = transaction.getType();
        subtype = transaction.getSubtype();
        placeId = transaction.getPlaceId();
        accountId = transaction.getAccountId();
    }

    public long getId() {
        return id;
    }

    public double getValue() {
        return value;
    }

    public double getCost() {
        return cost;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public TransactionType getType() {
        return type;
    }

    public TransactionSubtype getSubtype() {
        return subtype;
    }

    public String getPlaceId() {
        return placeId;
    }

    public long getAccountId() {
        return accountId;
    }
}
