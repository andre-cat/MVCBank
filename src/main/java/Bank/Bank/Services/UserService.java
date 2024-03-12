package Bank.Bank.Services;

import Bank.Bank.DTO.ResponseDTO;
import Bank.Bank.DTO.UserDTO;
import Bank.Bank.Entities.User;
import Bank.Bank.Repository.UserRepository;
import Bank.Bank.Utils.ExceptionBuilder;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements Bank.Bank.DAO.UserServiceDAO {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> selectAll() {
        try {
            Iterable<User> users = userRepository.findAll();

            List<UserDTO> userDTOList = new ArrayList<>();

            for (User user : users) {
                UserDTO userDTO = new UserDTO(user);
                userDTOList.add(userDTO);
            }

            return userDTOList;
        } catch (EmptyResultDataAccessException e) {
            System.out.println(ExceptionBuilder.BuildSelectAllException("user"));
            return new ArrayList<UserDTO>() {{
            }};
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
            return new ArrayList<UserDTO>() {{
            }};
        }
    }

    public UserDTO selectById(long id) {
        try {
            Optional<User> user = userRepository.findById(id);
            if (user.isPresent()) {
                return new UserDTO(user.get());
            } else {
                throw new EmptyResultDataAccessException(0);
            }
        } catch (EmptyResultDataAccessException e) {
            System.out.println(ExceptionBuilder.BuildSelectByIdException("user"));
            return new UserDTO();
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e);
            return new UserDTO();
        }
    }

    public ResponseDTO insert(UserDTO userDTO) {
        try {
            if (!userRepository.existsById(userDTO.getId())) {
                User user = new User(userDTO);
                userRepository.save(user);
                return new ResponseDTO();
            } else {
                throw new EntityExistsException();
            }
        } catch (EntityExistsException e) {
            return new ResponseDTO(true, ExceptionBuilder.BuildInsertException("user"));
        } catch (Exception e) {
            return new ResponseDTO(true, "EXCEPTION: " + e);
        }
    }

    public ResponseDTO update(UserDTO userDTO) {
        try {
            if (userRepository.existsById(userDTO.getId())) {
                userRepository.save(new User(userDTO));
            } else {
                throw new EmptyResultDataAccessException(0);
            }
            return new ResponseDTO();
        } catch (EmptyResultDataAccessException e) {
            return new ResponseDTO(true, ExceptionBuilder.BuildUpdateException("user"));
        } catch (Exception e) {
            return new ResponseDTO(true, "EXCEPTION: " + e);
        }
    }

    public ResponseDTO deleteAll() {
        try {
            userRepository.deleteAll();
        } catch (Exception e) {
            return new ResponseDTO(false, e.getMessage());
        }
        return new ResponseDTO();
    }

    public ResponseDTO deleteById(long id) {
        try {
            if (userRepository.existsById(id)) {
                userRepository.deleteById(id);
            } else {
                throw new EmptyResultDataAccessException(0);
            }
            return new ResponseDTO();
        } catch (EmptyResultDataAccessException e) {
            return new ResponseDTO(true, ExceptionBuilder.BuildDeleteException("user"));
        } catch (Exception e) {
            return new ResponseDTO(true, "EXCEPTION: " + e);
        }
    }
}
