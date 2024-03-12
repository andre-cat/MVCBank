package Bank.Bank.DAO;

import Bank.Bank.DTO.ResponseDTO;
import Bank.Bank.DTO.UserDTO;

import java.util.List;

public interface UserServiceDAO {

    public abstract List<UserDTO> selectAll();

    public abstract UserDTO selectById(long id);

    public abstract ResponseDTO insert(UserDTO userDTO);

    public abstract ResponseDTO update(UserDTO userDTO);

    public abstract ResponseDTO deleteAll();

    public abstract ResponseDTO deleteById(long id);

}
