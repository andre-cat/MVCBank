package Bank.Bank.DAO;

import Bank.Bank.DTO.AccountDTO;
import Bank.Bank.DTO.ResponseDTO;

import java.util.List;

public interface AccountServiceDAO {

    public abstract List<AccountDTO> selectAll();

    public abstract AccountDTO selectById(long id);

    public abstract ResponseDTO insert(AccountDTO accountDTO);

    public abstract ResponseDTO update(AccountDTO accountDTO);

    public abstract ResponseDTO deleteAll();

    public abstract ResponseDTO deleteById(long id);

}
