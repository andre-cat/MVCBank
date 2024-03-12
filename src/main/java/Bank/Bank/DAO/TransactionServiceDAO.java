package Bank.Bank.DAO;

import Bank.Bank.DTO.ResponseDTO;
import Bank.Bank.DTO.TransactionDTO;

import java.util.List;

public interface TransactionServiceDAO {

    public abstract List<TransactionDTO> selectAll();

    public abstract List<TransactionDTO> selectAllByAccountId(long accountId);

    public abstract TransactionDTO selectById(long id);

    public abstract ResponseDTO insert(TransactionDTO transactionDTO);

    public abstract ResponseDTO update(TransactionDTO transactionDTO);

    public abstract ResponseDTO deleteAll();

    public abstract ResponseDTO deleteAllByAccountId(long accountId);

    public abstract ResponseDTO deleteById(long id);
}
